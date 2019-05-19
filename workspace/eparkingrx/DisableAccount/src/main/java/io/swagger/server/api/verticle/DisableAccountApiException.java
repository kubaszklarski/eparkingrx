package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;

public final class DisableAccountApiException extends MainApiException {
    public DisableAccountApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final DisableAccountApiException DisableAccount_disableAccount_400_Exception = new DisableAccountApiException(400, "Invalid input");
    public static final DisableAccountApiException DisableAccount_disableAccount_500_Exception = new DisableAccountApiException(500, "Internal system error");
    public static final DisableAccountApiException DisableAccount_disableAccount_502_Exception = new DisableAccountApiException(502, "External system fault");
    

}