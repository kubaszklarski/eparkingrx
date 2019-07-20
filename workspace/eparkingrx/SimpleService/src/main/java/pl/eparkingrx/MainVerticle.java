package pl.eparkingrx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MainVerticle extends AbstractVerticle{
	
	final static Logger LOG = LoggerFactory.getLogger(MainVerticle.class);
	
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MainVerticle());
	}
	
	@Override
	public void start() throws Exception {
		
		LOG.info("eparkingrx start");
		
		Future<String> WebClientVerticleDeployment = Future.future();
		vertx.deployVerticle(new WebClientVerticle(), WebClientVerticleDeployment);
		
		WebClientVerticleDeployment.compose(result -> {
			Future<String> ReceiverVerticleDeployment = Future.future();
			vertx.deployVerticle(new ReceiverVerticle(), ReceiverVerticleDeployment);
			return ReceiverVerticleDeployment;
		}).compose(result -> {
			Future<String> CallerVerticleDeployment = Future.future();
			vertx.deployVerticle(new CallerVerticle(), CallerVerticleDeployment);
			return CallerVerticleDeployment;			
		}).compose(result -> {
			Future<String> HttpServerVerticleDeployment = Future.future();
			vertx.deployVerticle(new HttpServerVerticle(), HttpServerVerticleDeployment);
			return HttpServerVerticleDeployment;			
		}).setHandler(ar -> {
			if(ar.succeeded()) {
				LOG.info("starting verticle " + this.getClass().getSimpleName() + " complete");
			}else{
				LOG.info("starting verticle " + this.getClass().getSimpleName() + " failed");
				ar.cause().printStackTrace();
			}
		});
		
		LOG.info("eparkingrx start complete");
		
	}
	
	@Override
	public void stop() throws Exception {
		
		LOG.info("eparkingrx stop");
		
		//undeploy verticles
		
		
	}
	
}
