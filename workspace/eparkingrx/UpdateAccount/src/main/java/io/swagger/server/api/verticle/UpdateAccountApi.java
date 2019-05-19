package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.UpdateAccount;
import io.swagger.server.api.model.UpdateAccountResponse;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface UpdateAccountApi  {
    //UpdateAccount
    void updateAccount(String accountId, UpdateAccount updateAccount, Handler<AsyncResult<UpdateAccountResponse>> handler);
    
}
