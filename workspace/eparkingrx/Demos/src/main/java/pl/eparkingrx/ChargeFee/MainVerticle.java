package pl.eparkingrx.ChargeFee;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.eparkingrx.ChargeFee.connector.CompositeConnectorVerticle;
import pl.eparkingrx.ChargeFee.connector.DatabaseConnectorVerticle;
import pl.eparkingrx.ChargeFee.connector.FilesystemConnectorVerticle;
import pl.eparkingrx.ChargeFee.connector.WebClientConnectorVerticle;

public class MainVerticle extends AbstractVerticle{
	
	private final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
	
	@Override
	public void start() throws Exception {
		
		Future<String> FileVerticleDeployment = Future.future();
		Future<String> DatabaseClientVerticleDeployment = Future.future();
		Future<String> WebClientVerticleDeployment = Future.future();
		Future<String> CamelClientVerticleDeployment = Future.future();
		
		vertx.deployVerticle(new FilesystemConnectorVerticle(), FileVerticleDeployment);
		
		FileVerticleDeployment.compose(id -> {
			vertx.deployVerticle(new DatabaseConnectorVerticle(), DatabaseClientVerticleDeployment);
			return DatabaseClientVerticleDeployment;				
		}).compose(id -> {
			vertx.deployVerticle(new WebClientConnectorVerticle(), WebClientVerticleDeployment);
			return WebClientVerticleDeployment;
		}).compose(id -> {
			vertx.deployVerticle(new CompositeConnectorVerticle(), CamelClientVerticleDeployment);
			return CamelClientVerticleDeployment;
		}).setHandler(ar -> {
		  if (ar.succeeded()) {
			  logger.info("Deployment succeded");
		  } else {
			  logger.info("Deployment failed");
			  vertx.close();
		  }
		});
		
	}
	
}
