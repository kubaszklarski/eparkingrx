package io.swagger.server.api.verticle;

import io.swagger.server.api.model.EndParkingResponse;
import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface EndParkingApi  {
    //EndParking
    void endParking(String parkingId, Handler<AsyncResult<EndParkingResponse>> handler);
    
}
