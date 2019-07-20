package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface DisableAccountApi  {
    //DisableAccount
    void disableAccount(String accountId, Handler<AsyncResult<Void>> handler);
    
}
