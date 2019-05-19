package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.StartParking;
import io.swagger.server.api.model.StartParkingResponse;

public final class StartParkingApiException extends MainApiException {
    public StartParkingApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final StartParkingApiException StartParking_startParking_400_Exception = new StartParkingApiException(400, "Invalid input");
    public static final StartParkingApiException StartParking_startParking_500_Exception = new StartParkingApiException(500, "Internal system error");
    public static final StartParkingApiException StartParking_startParking_502_Exception = new StartParkingApiException(502, "External system fault");
    

}