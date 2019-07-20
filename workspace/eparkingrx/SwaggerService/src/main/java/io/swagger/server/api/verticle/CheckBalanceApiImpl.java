package io.swagger.server.api.verticle;

import java.math.BigDecimal;
import java.math.RoundingMode;

import io.swagger.server.api.model.CheckBalanceResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class CheckBalanceApiImpl implements CheckBalanceApi{

	@Override
	public void checkBalance(String accountId, Handler<AsyncResult<CheckBalanceResponse>> handler) {
		CheckBalanceResponse response = new CheckBalanceResponse();
		response.setAmount(new BigDecimal(Math.random()).multiply(new BigDecimal(1000)).setScale(2, RoundingMode.CEILING));
		handler.handle(Future.succeededFuture(response));
	}

}
