package io.swagger.server.api.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import io.swagger.server.api.model.ChargeFee;
import io.swagger.server.api.model.ChargeFeeResponse;
import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

import java.util.List;
import java.util.Map;

public class ChargeFeeApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(ChargeFeeApiVerticle.class); 
    
    final static String CHARGEFEE_SERVICE_ID = "ChargeFee";
    
    final ChargeFeeApi service;

    public ChargeFeeApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.ChargeFeeApiImpl");
            service = (ChargeFeeApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("ChargeFeeApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for ChargeFee
        vertx.eventBus().<JsonObject> consumer(CHARGEFEE_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "ChargeFee";
                JsonObject chargeFeeParam = message.body().getJsonObject("ChargeFee");
                if (chargeFeeParam == null) {
                    manageError(message, new MainApiException(400, "ChargeFee is required"), serviceId);
                    return;
                }
                ChargeFee chargeFee = Json.mapper.readValue(chargeFeeParam.encode(), ChargeFee.class);
                service.chargeFee(chargeFee, result -> {
                    if (result.succeeded()) {
                    	DeliveryOptions options = new DeliveryOptions();
                    	options.addHeader("Access-Control-Allow-Origin", "*");
                    	options.addHeader("Access-Control-Allow-Methods", "*");
                    	options.addHeader("Content-Type", "application/json");
                    	options.addHeader("CUSTOM_STATUS_CODE", "201");
                        message.reply(new JsonObject(Json.encode(result.result())).encodePrettily(), options);
                    }else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "ChargeFee");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("ChargeFee", e);
                message.fail(MainApiException.INTERNAL_SERVER_ERROR.getStatusCode(), MainApiException.INTERNAL_SERVER_ERROR.getStatusMessage());
            }
        });
        
    }
    
    private void manageError(Message<JsonObject> message, Throwable cause, String serviceName) {
        int code = MainApiException.INTERNAL_SERVER_ERROR.getStatusCode();
        String statusMessage = MainApiException.INTERNAL_SERVER_ERROR.getStatusMessage();
        if (cause instanceof MainApiException) {
            code = ((MainApiException)cause).getStatusCode();
            statusMessage = ((MainApiException)cause).getStatusMessage();
        } else {
            logUnexpectedError(serviceName, cause); 
        }
            
        message.fail(code, statusMessage);
    }
    
    private void logUnexpectedError(String serviceName, Throwable cause) {
        LOGGER.error("Unexpected error in "+ serviceName, cause);
    }
}
