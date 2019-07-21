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

import java.util.List;
import java.util.Map;

public class DisableAccountApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(DisableAccountApiVerticle.class); 
    
    final static String DISABLEACCOUNT_SERVICE_ID = "DisableAccount";
    
    final DisableAccountApi service;

    public DisableAccountApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.DisableAccountApiImpl");
            service = (DisableAccountApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("DisableAccountApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for DisableAccount
        vertx.eventBus().<JsonObject> consumer(DISABLEACCOUNT_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "DisableAccount";
                String accountIdParam = message.body().getString("AccountId");
                if(accountIdParam == null) {
                    manageError(message, new MainApiException(400, "AccountId is required"), serviceId);
                    return;
                }
                String accountId = accountIdParam;
                service.disableAccount(accountId, result -> {
                    if (result.succeeded()) {
                    	DeliveryOptions options = new DeliveryOptions();
                    	options.addHeader("Access-Control-Allow-Origin", "*");
                    	options.addHeader("Access-Control-Allow-Methods", "*");
                    	options.addHeader("CUSTOM_STATUS_CODE", "204");
                        message.reply(null, options);
                    }else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "DisableAccount");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("DisableAccount", e);
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
