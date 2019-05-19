package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.RetrieveAccountResponse;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface RetrieveAccountApi  {
    //RetrieveAccount
    void retrieveAccount(String accountId, Handler<AsyncResult<RetrieveAccountResponse>> handler);
    
}
