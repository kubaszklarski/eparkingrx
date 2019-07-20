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
import io.swagger.server.api.model.RetrieveAccountResponse;

import java.util.List;
import java.util.Map;

public class RetrieveAccountApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(RetrieveAccountApiVerticle.class); 
    
    final static String RETRIEVEACCOUNT_SERVICE_ID = "RetrieveAccount";
    
    final RetrieveAccountApi service;

    public RetrieveAccountApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.RetrieveAccountApiImpl");
            service = (RetrieveAccountApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("RetrieveAccountApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for RetrieveAccount
        vertx.eventBus().<JsonObject> consumer(RETRIEVEACCOUNT_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "RetrieveAccount";
                String accountIdParam = message.body().getString("AccountId");
                if(accountIdParam == null) {
                    manageError(message, new MainApiException(400, "AccountId is required"), serviceId);
                    return;
                }
                String accountId = accountIdParam;
                service.retrieveAccount(accountId, result -> {
                    if (result.succeeded())
                        message.reply(new JsonObject(Json.encode(result.result())).encodePrettily());
                    else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "RetrieveAccount");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("RetrieveAccount", e);
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
