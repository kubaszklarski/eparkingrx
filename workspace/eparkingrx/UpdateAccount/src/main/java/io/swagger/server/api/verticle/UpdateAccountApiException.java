package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.UpdateAccount;
import io.swagger.server.api.model.UpdateAccountResponse;

public final class UpdateAccountApiException extends MainApiException {
    public UpdateAccountApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final UpdateAccountApiException UpdateAccount_updateAccount_400_Exception = new UpdateAccountApiException(400, "Invalid input");
    public static final UpdateAccountApiException UpdateAccount_updateAccount_500_Exception = new UpdateAccountApiException(500, "Internal system error");
    public static final UpdateAccountApiException UpdateAccount_updateAccount_502_Exception = new UpdateAccountApiException(502, "External system fault");
    

}