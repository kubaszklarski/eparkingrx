package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.RegisterPayment;
import io.swagger.server.api.model.RegisterPaymentResponse;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface RegisterPaymentApi  {
    //RegisterPayment
    void registerPayment(RegisterPayment registerPayment, Handler<AsyncResult<RegisterPaymentResponse>> handler);
    
}
