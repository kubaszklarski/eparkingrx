package pl.eparkingrx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;

public class DemoHttpClient {

	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		MyHttpClient(vertx);
		
	}
	
	public static void MyHttpClient(Vertx vertx){
		
		Obj obj = new Obj();
		obj.liczba = 999;
		obj.tekst = "umceeeeauma";
		JsonObject jo = JsonObject.mapFrom(obj);
	
		WebClientOptions options = new WebClientOptions();
		options.setConnectTimeout(3000);
		options.setKeepAlive(false);
		options.setTrustAll(true);
		options.setProtocolVersion(HttpVersion.HTTP_2);
		options.setLogActivity(true);
		WebClient client = WebClient.create(vertx, options);
		
		HttpRequest<JsonObject> request =  client
			.request(HttpMethod.POST, 8080, "localhost", "/mock/text")
			.addQueryParam("paramname", "paramval")
			.putHeader("hname", "hval")
			.as(BodyCodec.jsonObject())
			.timeout(3000)
			.expect(ResponsePredicate.SC_SUCCESS);
		
		//send pojo
		request
			.sendJson(obj, ar -> {
				System.out.println("request pojo: " + JsonObject.mapFrom(obj).encodePrettily());
				if(ar.succeeded()){
					HttpResponse<JsonObject> response = ar.result();
					if(response.statusCode() == 200 && response.getHeader("content-type").equals("application/json")){
						JsonObject result = response.body();
						System.out.println("response: " + result.encodePrettily());		
					}else {
						System.out.println("failed to understand request: " + response.statusCode() + " " + response.statusMessage());
					}
				}else{
					System.out.println("failed to send request: " + ar.cause().getMessage());
					ar.cause().printStackTrace();
				}
				
			});
		
		//send json object
		request
			.sendJsonObject(jo, ar -> {
				System.out.println("request json obj: " + jo.encodePrettily());
				HttpResponse<JsonObject> response = ar.result();
				JsonObject result = response.body();
				System.out.println("response: " + result.encodePrettily());
			});

		client.close();
		
	}

}
