package io.swagger.server.api.verticle;

import java.math.BigDecimal;
import java.math.RoundingMode;

import io.swagger.server.api.model.EndParkingResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class EndParkingApiImpl implements EndParkingApi{

	@Override
	public void endParking(String parkingId, Handler<AsyncResult<EndParkingResponse>> handler) {
		EndParkingResponse response = new EndParkingResponse();
		response.setAmount(new BigDecimal(Math.random()).multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING));
		handler.handle(Future.succeededFuture(response));
	}

}
