package io.swagger.server.api.verticle;

import io.swagger.server.api.model.EndParkingResponse;
import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

public final class EndParkingApiException extends MainApiException {
    public EndParkingApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final EndParkingApiException EndParking_endParking_400_Exception = new EndParkingApiException(400, "Invalid input");
    public static final EndParkingApiException EndParking_endParking_500_Exception = new EndParkingApiException(500, "Internal system error");
    public static final EndParkingApiException EndParking_endParking_502_Exception = new EndParkingApiException(502, "External system fault");
    

}