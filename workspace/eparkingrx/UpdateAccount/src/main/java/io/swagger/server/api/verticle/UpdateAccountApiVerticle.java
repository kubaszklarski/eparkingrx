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
import io.swagger.server.api.model.UpdateAccount;
import io.swagger.server.api.model.UpdateAccountResponse;

import java.util.List;
import java.util.Map;

public class UpdateAccountApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(UpdateAccountApiVerticle.class); 
    
    final static String UPDATEACCOUNT_SERVICE_ID = "UpdateAccount";
    
    final UpdateAccountApi service;

    public UpdateAccountApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.UpdateAccountApiImpl");
            service = (UpdateAccountApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("UpdateAccountApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for UpdateAccount
        vertx.eventBus().<JsonObject> consumer(UPDATEACCOUNT_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "UpdateAccount";
                String accountIdParam = message.body().getString("AccountId");
                if(accountIdParam == null) {
                    manageError(message, new MainApiException(400, "AccountId is required"), serviceId);
                    return;
                }
                String accountId = accountIdParam;
                JsonObject updateAccountParam = message.body().getJsonObject("UpdateAccount");
                if (updateAccountParam == null) {
                    manageError(message, new MainApiException(400, "UpdateAccount is required"), serviceId);
                    return;
                }
                UpdateAccount updateAccount = Json.mapper.readValue(updateAccountParam.encode(), UpdateAccount.class);
                service.updateAccount(accountId, updateAccount, result -> {
                    if (result.succeeded())
                        message.reply(new JsonObject(Json.encode(result.result())).encodePrettily());
                    else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "UpdateAccount");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("UpdateAccount", e);
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
