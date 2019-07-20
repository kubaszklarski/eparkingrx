package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.RegisterAccount;
import io.swagger.server.api.model.RegisterAccountResponse;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface RegisterAccountApi  {
    //RegisterAccount
    void registerAccount(RegisterAccount registerAccount, Handler<AsyncResult<RegisterAccountResponse>> handler);
    
}
