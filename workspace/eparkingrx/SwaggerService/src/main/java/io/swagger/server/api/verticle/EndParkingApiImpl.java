package io.swagger.server.api.verticle;

import io.swagger.server.api.model.EndParkingResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class EndParkingApiImpl implements EndParkingApi{

	@Override
	public void endParking(String parkingId, Handler<AsyncResult<EndParkingResponse>> handler) {
		// TODO Auto-generated method stub
		
	}

}
