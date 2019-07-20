package io.swagger.server.api.verticle;

import io.swagger.server.api.model.CheckBalanceResponse;
import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

public final class CheckBalanceApiException extends MainApiException {
    public CheckBalanceApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final CheckBalanceApiException CheckBalance_checkBalance_400_Exception = new CheckBalanceApiException(400, "Invalid input");
    public static final CheckBalanceApiException CheckBalance_checkBalance_404_Exception = new CheckBalanceApiException(404, "Payment not found");
    public static final CheckBalanceApiException CheckBalance_checkBalance_500_Exception = new CheckBalanceApiException(500, "Internal system error");
    public static final CheckBalanceApiException CheckBalance_checkBalance_502_Exception = new CheckBalanceApiException(502, "External system fault");
    

}