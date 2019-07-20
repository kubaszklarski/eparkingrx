package io.swagger.server.api.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.SetRate;
import io.swagger.server.api.model.SetRateResponse;

import java.util.List;
import java.util.Map;

public class SetRateApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(SetRateApiVerticle.class); 
    
    final static String SETRATE_SERVICE_ID = "SetRate";
    
    final SetRateApi service;

    public SetRateApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.SetRateApiImpl");
            service = (SetRateApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("SetRateApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for SetRate
        vertx.eventBus().<JsonObject> consumer(SETRATE_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "SetRate";
                JsonObject setRateParam = message.body().getJsonObject("SetRate");
                if (setRateParam == null) {
                    manageError(message, new MainApiException(400, "SetRate is required"), serviceId);
                    return;
                }
                SetRate setRate = Json.mapper.readValue(setRateParam.encode(), SetRate.class);
                service.setRate(setRate, result -> {
                    if (result.succeeded()) {
                    	DeliveryOptions options = new DeliveryOptions();
                    	options.addHeader("Access-Control-Allow-Origin", "*");
                    	options.addHeader("Content-Type", "application/json");
                    	options.addHeader("CUSTOM_STATUS_CODE", "200");
                    	message.reply(new JsonObject(Json.encode(result.result())).encodePrettily(), options);
                    }else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "SetRate");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("SetRate", e);
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
