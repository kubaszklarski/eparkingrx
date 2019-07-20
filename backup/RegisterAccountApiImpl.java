package io.swagger.server.api.verticle;

import io.swagger.server.api.model.RegisterAccount;
import io.swagger.server.api.model.RegisterAccountResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class RegisterAccountApiImpl implements RegisterAccountApi{

	@Override
	public void registerAccount(RegisterAccount registerAccount,
			Handler<AsyncResult<RegisterAccountResponse>> handler) {
		// TODO Auto-generated method stub
		
	}

}
