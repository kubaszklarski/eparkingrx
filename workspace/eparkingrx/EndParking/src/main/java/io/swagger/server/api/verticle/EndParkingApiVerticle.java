package io.swagger.server.api.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import io.swagger.server.api.model.EndParkingResponse;
import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

import java.util.List;
import java.util.Map;

public class EndParkingApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(EndParkingApiVerticle.class); 
    
    final static String ENDPARKING_SERVICE_ID = "EndParking";
    
    final EndParkingApi service;

    public EndParkingApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.EndParkingApiImpl");
            service = (EndParkingApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("EndParkingApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for EndParking
        vertx.eventBus().<JsonObject> consumer(ENDPARKING_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "EndParking";
                String parkingIdParam = message.body().getString("ParkingId");
                if(parkingIdParam == null) {
                    manageError(message, new MainApiException(400, "ParkingId is required"), serviceId);
                    return;
                }
                String parkingId = parkingIdParam;
                service.endParking(parkingId, result -> {
                    if (result.succeeded())
                        message.reply(new JsonObject(Json.encode(result.result())).encodePrettily());
                    else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "EndParking");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("EndParking", e);
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
