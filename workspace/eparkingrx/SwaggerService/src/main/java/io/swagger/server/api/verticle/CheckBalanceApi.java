package io.swagger.server.api.verticle;

import io.swagger.server.api.model.CheckBalanceResponse;
import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface CheckBalanceApi  {
    //CheckBalance
    void checkBalance(String accountId, Handler<AsyncResult<CheckBalanceResponse>> handler);
    
}
