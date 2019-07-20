package pl.eparkingrx.ChargeFee.connector;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;

public class WebClientConnectorVerticle extends AbstractVerticle{
	
	private final Logger logger = LoggerFactory.getLogger(WebClientConnectorVerticle.class);
	private final static String verticle = "WebClientConnectorVerticle"; 
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		
		EventBus eventbus = vertx.eventBus();
		MessageConsumer<JsonObject> consumer = eventbus.consumer(verticle);
		
		WebClientOptions options = new WebClientOptions();
		options.setConnectTimeout(3000);
		options.setKeepAlive(false);
		options.setTrustAll(true);
		options.setProtocolVersion(HttpVersion.HTTP_2);
		options.setLogActivity(true);
		WebClient client = WebClient.create(vertx, options);
		
		consumer.handler(message -> {

			JsonObject HttpBodyRequest = message.body();
			
			HttpRequest<JsonObject> request = client
				.request(
					HttpMethod.valueOf(message.headers().get("HttpVerb")),
					Integer.parseInt(message.headers().get("HttpPort")),
					message.headers().get("HttpHost"),
					message.headers().get("HttpUri")
				)
				.addQueryParam("paramname", "paramval")
				.putHeader("hname", "hval")
				.as(BodyCodec.jsonObject())
				.timeout(3000);
				//.expect(ResponsePredicate.SC_SUCCESS)

			request
				.sendJsonObject(HttpBodyRequest, ar -> {
					logger.info("HttpBodyRequest: " + HttpBodyRequest.encodePrettily());
					if(ar.succeeded()) {
						HttpResponse<JsonObject> response = ar.result();
						JsonObject HttpBodyResponse = response.body();
						logger.info("HttpBodyResponse: " + HttpBodyResponse.encodePrettily());						
					}else{
						//logger.info(ar.result());
						logger.info(ar.result().statusMessage());
						logger.info(ar.result().statusCode());
						logger.info(ar.cause());
						
					}

				});
			
		});

		//make sure that EventBus registration reach all nodes in the cluster
		consumer.completionHandler(res -> {
			if(res.succeeded()){
				logger.info("start " + verticle + " EventBus");
			}else {
				logger.info("start " + verticle + " EventBus failed");
			}
		});
		
		//complete the deloyment
		startFuture.complete();
		logger.info("start " + verticle);
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		logger.info("stop " + verticle);
		stopFuture.complete();
	}
	
	public void MyHttpClient() {

		
	}
	
}