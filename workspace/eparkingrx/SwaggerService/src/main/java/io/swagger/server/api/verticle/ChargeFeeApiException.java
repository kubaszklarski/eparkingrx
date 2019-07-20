package io.swagger.server.api.verticle;

import io.swagger.server.api.model.ChargeFee;
import io.swagger.server.api.model.ChargeFeeResponse;
import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

public final class ChargeFeeApiException extends MainApiException {
    public ChargeFeeApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final ChargeFeeApiException ChargeFee_chargeFee_400_Exception = new ChargeFeeApiException(400, "Invalid input");
    public static final ChargeFeeApiException ChargeFee_chargeFee_500_Exception = new ChargeFeeApiException(500, "Internal system error");
    public static final ChargeFeeApiException ChargeFee_chargeFee_502_Exception = new ChargeFeeApiException(502, "External system fault");
    

}