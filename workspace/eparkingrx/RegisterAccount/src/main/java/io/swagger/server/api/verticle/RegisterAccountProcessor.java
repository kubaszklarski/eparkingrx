package io.swagger.server.api.verticle;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import io.swagger.server.api.model.RegisterAccount;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class RegisterAccountProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		JsonObject jo = exchange.getIn().getBody(JsonObject.class);
		RegisterAccount registerAccount = Json.mapper.readValue(jo.encode(), RegisterAccount.class);		
		exchange.getIn().setBody(registerAccount);
	}

}
