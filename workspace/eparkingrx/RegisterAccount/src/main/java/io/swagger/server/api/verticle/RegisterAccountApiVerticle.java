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
import io.swagger.server.api.model.RegisterAccount;
import io.swagger.server.api.model.RegisterAccountResponse;

import java.util.List;
import java.util.Map;

public class RegisterAccountApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(RegisterAccountApiVerticle.class); 
    
    final static String REGISTERACCOUNT_SERVICE_ID = "RegisterAccount";
    
    final RegisterAccountApi service;

    public RegisterAccountApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.RegisterAccountApiImpl");
            service = (RegisterAccountApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("RegisterAccountApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for RegisterAccount
        vertx.eventBus().<JsonObject> consumer(REGISTERACCOUNT_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "RegisterAccount";
                JsonObject registerAccountParam = message.body().getJsonObject("RegisterAccount");
                if (registerAccountParam == null) {
                    manageError(message, new MainApiException(400, "RegisterAccount is required"), serviceId);
                    return;
                }
                RegisterAccount registerAccount = Json.mapper.readValue(registerAccountParam.encode(), RegisterAccount.class);
                service.registerAccount(registerAccount, result -> {
                    if (result.succeeded())
                        message.reply(new JsonObject(Json.encode(result.result())).encodePrettily());
                    else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "RegisterAccount");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("RegisterAccount", e);
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
