package io.swagger.server.api.verticle;

import java.math.BigDecimal;

import io.swagger.server.api.model.CheckBalanceResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class CheckBalanceApiImpl implements CheckBalanceApi{

	@Override
	public void checkBalance(String accountId, Handler<AsyncResult<CheckBalanceResponse>> handler) {
		CheckBalanceResponse response = new CheckBalanceResponse();
		response.setAmount(new BigDecimal(Math.random()));
		handler.handle(Future.succeededFuture(response));
	}

}
