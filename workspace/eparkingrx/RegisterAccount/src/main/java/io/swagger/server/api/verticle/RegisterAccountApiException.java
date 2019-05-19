package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.RegisterAccount;
import io.swagger.server.api.model.RegisterAccountResponse;

public final class RegisterAccountApiException extends MainApiException {
    public RegisterAccountApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final RegisterAccountApiException RegisterAccount_registerAccount_400_Exception = new RegisterAccountApiException(400, "Invalid input");
    public static final RegisterAccountApiException RegisterAccount_registerAccount_500_Exception = new RegisterAccountApiException(500, "Internal system error");
    public static final RegisterAccountApiException RegisterAccount_registerAccount_502_Exception = new RegisterAccountApiException(502, "External system fault");
    

}