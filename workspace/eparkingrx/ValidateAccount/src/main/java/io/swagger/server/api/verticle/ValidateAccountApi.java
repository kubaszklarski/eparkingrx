package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.ValidateAccount;
import io.swagger.server.api.model.ValidateAccountResponse;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface ValidateAccountApi  {
    //ValidateAccount
    void validateAccount(ValidateAccount validateAccount, Handler<AsyncResult<ValidateAccountResponse>> handler);
    
}
