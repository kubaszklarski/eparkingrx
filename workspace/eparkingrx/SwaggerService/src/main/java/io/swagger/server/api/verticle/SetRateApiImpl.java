package io.swagger.server.api.verticle;

import java.math.BigDecimal;
import java.math.RoundingMode;

import io.swagger.server.api.model.Rate;
import io.swagger.server.api.model.SetRate;
import io.swagger.server.api.model.SetRateResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class SetRateApiImpl implements SetRateApi{

	@Override
	public void setRate(SetRate setRate, Handler<AsyncResult<SetRateResponse>> handler) {
		SetRateResponse response = new SetRateResponse();
		Rate rate = new Rate();
		rate.setRateAmount(new BigDecimal(Math.random()).multiply(new BigDecimal(1000)).setScale(2, RoundingMode.CEILING));
		response.setRate(rate);
		handler.handle(Future.succeededFuture(response));
	}

}
