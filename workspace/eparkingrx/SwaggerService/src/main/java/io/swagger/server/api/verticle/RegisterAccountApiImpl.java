package io.swagger.server.api.verticle;

import java.util.UUID;

import io.swagger.server.api.model.RegisterAccount;
import io.swagger.server.api.model.RegisterAccountResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class RegisterAccountApiImpl implements RegisterAccountApi{

	@Override
	public void registerAccount(RegisterAccount registerAccount, Handler<AsyncResult<RegisterAccountResponse>> handler) {
		RegisterAccountResponse response = new RegisterAccountResponse();
		response.setAccountId(UUID.randomUUID().toString());
		handler.handle(Future.succeededFuture(response));
	}

}
