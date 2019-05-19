package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.StartParking;
import io.swagger.server.api.model.StartParkingResponse;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface StartParkingApi  {
    //StartParking
    void startParking(StartParking startParking, Handler<AsyncResult<StartParkingResponse>> handler);
    
}
