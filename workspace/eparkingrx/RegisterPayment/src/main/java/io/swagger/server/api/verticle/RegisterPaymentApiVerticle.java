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
import io.swagger.server.api.model.RegisterPayment;
import io.swagger.server.api.model.RegisterPaymentResponse;

import java.util.List;
import java.util.Map;

public class RegisterPaymentApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(RegisterPaymentApiVerticle.class); 
    
    final static String REGISTERPAYMENT_SERVICE_ID = "RegisterPayment";
    
    final RegisterPaymentApi service;

    public RegisterPaymentApiVerticle() {
        try {
            Class serviceImplClass = getClass().getClassLoader().loadClass("io.swagger.server.api.verticle.RegisterPaymentApiImpl");
            service = (RegisterPaymentApi)serviceImplClass.newInstance();
        } catch (Exception e) {
            logUnexpectedError("RegisterPaymentApiVerticle constructor", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws Exception {
        
        //Consumer for RegisterPayment
        vertx.eventBus().<JsonObject> consumer(REGISTERPAYMENT_SERVICE_ID).handler(message -> {
            try {
                // Workaround for #allParams section clearing the vendorExtensions map
                String serviceId = "RegisterPayment";
                JsonObject registerPaymentParam = message.body().getJsonObject("RegisterPayment");
                if (registerPaymentParam == null) {
                    manageError(message, new MainApiException(400, "RegisterPayment is required"), serviceId);
                    return;
                }
                RegisterPayment registerPayment = Json.mapper.readValue(registerPaymentParam.encode(), RegisterPayment.class);
                service.registerPayment(registerPayment, result -> {
                    if (result.succeeded())
                        message.reply(new JsonObject(Json.encode(result.result())).encodePrettily());
                    else {
                        Throwable cause = result.cause();
                        manageError(message, cause, "RegisterPayment");
                    }
                });
            } catch (Exception e) {
                logUnexpectedError("RegisterPayment", e);
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
