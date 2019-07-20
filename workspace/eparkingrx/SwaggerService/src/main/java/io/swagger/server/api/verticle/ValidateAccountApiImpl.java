package io.swagger.server.api.verticle;

import io.swagger.server.api.model.ValidateAccount;
import io.swagger.server.api.model.ValidateAccountResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class ValidateAccountApiImpl implements ValidateAccountApi{

	@Override
	public void validateAccount(ValidateAccount validateAccount,
			Handler<AsyncResult<ValidateAccountResponse>> handler) {
		// TODO Auto-generated method stub
		
	}

}
