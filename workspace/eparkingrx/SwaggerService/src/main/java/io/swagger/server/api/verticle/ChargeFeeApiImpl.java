package io.swagger.server.api.verticle;

import java.math.BigDecimal;

import io.swagger.server.api.model.ChargeFee;
import io.swagger.server.api.model.ChargeFeeResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class ChargeFeeApiImpl implements ChargeFeeApi{

	@Override
	public void chargeFee(ChargeFee chargeFee, Handler<AsyncResult<ChargeFeeResponse>> handler) {
		ChargeFeeResponse response = new ChargeFeeResponse();
		response.setAmount(new BigDecimal(Math.random()));
		handler.handle(Future.succeededFuture(response));
	}

}
