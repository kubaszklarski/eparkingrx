package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.ValidateAccount;
import io.swagger.server.api.model.ValidateAccountResponse;

public final class ValidateAccountApiException extends MainApiException {
    public ValidateAccountApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final ValidateAccountApiException ValidateAccount_validateAccount_400_Exception = new ValidateAccountApiException(400, "Invalid input");
    public static final ValidateAccountApiException ValidateAccount_validateAccount_500_Exception = new ValidateAccountApiException(500, "Internal system error");
    public static final ValidateAccountApiException ValidateAccount_validateAccount_502_Exception = new ValidateAccountApiException(502, "External system fault");
    

}