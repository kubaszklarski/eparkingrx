package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.SetRate;
import io.swagger.server.api.model.SetRateResponse;

public final class SetRateApiException extends MainApiException {
    public SetRateApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final SetRateApiException SetRate_setRate_400_Exception = new SetRateApiException(400, "Invalid input");
    public static final SetRateApiException SetRate_setRate_500_Exception = new SetRateApiException(500, "Internal system error");
    public static final SetRateApiException SetRate_setRate_502_Exception = new SetRateApiException(502, "External system fault");
    

}