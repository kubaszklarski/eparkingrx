package io.swagger.server.api.verticle;

import java.util.UUID;

import io.swagger.server.api.model.StartParking;
import io.swagger.server.api.model.StartParkingResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class StartParkingApiImpl implements StartParkingApi{

	@Override
	public void startParking(StartParking startParking, Handler<AsyncResult<StartParkingResponse>> handler) {
		StartParkingResponse response = new StartParkingResponse();
		response.setParkingId(UUID.randomUUID().toString());
		handler.handle(Future.succeededFuture(response));
	}

}
