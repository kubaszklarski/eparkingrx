package io.swagger.server.api.verticle;

import io.swagger.server.api.model.CheckBalanceResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class CheckBalanceApiImpl implements CheckBalanceApi{

	@Override
	public void checkBalance(String accountId, Handler<AsyncResult<CheckBalanceResponse>> handler) {
		// TODO Auto-generated method stub
		
	}

}
