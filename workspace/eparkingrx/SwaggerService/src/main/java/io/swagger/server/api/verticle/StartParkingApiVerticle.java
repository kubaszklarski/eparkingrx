package io.swagger.server.api.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.StartParking;
import io.swagger.server.api.model.StartParkingResponse;

import java.util.List;
import java.util.Map;

public class StartParkingApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(StartParkingApiVerticle.class); 
    
    final static String STARTPARKING_SERVICE_ID = "StartParking";
    
    final StartParkingApi service;

    public StartParkingApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.StartParkingApiImpl");
            service = (StartParkingApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("StartParkingApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for StartParking
        vertx.eventBus().<JsonObject> consumer(STARTPARKING_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "StartParking";
                JsonObject startParkingParam = message.body().getJsonObject("StartParking");
                if (startParkingParam == null) {
                    manageError(message, new MainApiException(400, "StartParking is required"), serviceId);
                    return;
                }
                StartParking startParking = Json.mapper.readValue(startParkingParam.encode(), StartParking.class);
                service.startParking(startParking, result -> {
                    if (result.succeeded())
                        message.reply(new JsonObject(Json.encode(result.result())).encodePrettily());
                    else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "StartParking");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("StartParking", e);
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
