package pl.eparkingrx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class ClientVerticle extends AbstractVerticle{
	
	final static Logger LOG = LoggerFactory.getLogger(ClientVerticle.class);
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.post("/client").handler(this::routeClient);
		vertx.createHttpServer().requestHandler(router).listen(8080, listenHandler ->{
			if(listenHandler.succeeded()){
				startFuture.complete();
			}else{
				startFuture.fail(listenHandler.cause());
			}
		});
	}
	
	private void routeClient(RoutingContext routingContext){
		String request = routingContext.getBodyAsString();		
		final EventBus eventBus = vertx.eventBus();
		eventBus.publish("ClusterAddress", request);
		LOG.info("ClientVerticle: sent request " + request);
		String response = "hello " + request;
		LOG.info("ClientVerticle: Current Thread Id " + Thread.currentThread().getId() + " Is Clustered " + vertx.isClustered());
		routingContext.response().end(response);
	}


}
