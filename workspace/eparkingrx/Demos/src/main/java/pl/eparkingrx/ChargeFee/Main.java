package pl.eparkingrx.ChargeFee;

import java.util.concurrent.TimeUnit;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.eparkingrx.ChargeFee.connector.CompositeConnectorVerticle;
import pl.eparkingrx.ChargeFee.connector.DatabaseConnectorVerticle;
import pl.eparkingrx.ChargeFee.connector.FilesystemConnectorVerticle;
import pl.eparkingrx.ChargeFee.connector.WebClientConnectorVerticle;

public class Main {
	
	private final static Logger logger = LoggerFactory.getLogger(CompositeConnectorVerticle.class);
	
	public static void main(String[] args) throws Exception {
		
		Vertx vertx = Vertx.vertx();		
		
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
			  logger.info("deployment succeded");
		  } else {
			  logger.info("deployment failed");
			  vertx.close();
		  }
		});
		
		TimeUnit.SECONDS.sleep(2);
		
		JsonObject req = new JsonObject();
		req.put("aaa", "bbb");
		DeliveryOptions options = new DeliveryOptions();
		options.addHeader("HttpVerb", "POST");
		options.addHeader("HttpHost", "localhost");
		options.addHeader("HttpPort", "8080");
		options.addHeader("HttpUri", "/mock/text");
		
		EventBus eventbus = vertx.eventBus().send("WebClientConnectorVerticle", req, options);
		
	}

}
