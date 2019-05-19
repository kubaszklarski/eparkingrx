package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.SetRate;
import io.swagger.server.api.model.SetRateResponse;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;
import java.util.Map;

public interface SetRateApi  {
    //SetRate
    void setRate(SetRate setRate, Handler<AsyncResult<SetRateResponse>> handler);
    
}
