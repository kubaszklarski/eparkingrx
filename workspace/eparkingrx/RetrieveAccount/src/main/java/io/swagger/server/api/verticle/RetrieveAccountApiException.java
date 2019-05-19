package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.RetrieveAccountResponse;

public final class RetrieveAccountApiException extends MainApiException {
    public RetrieveAccountApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final RetrieveAccountApiException RetrieveAccount_retrieveAccount_400_Exception = new RetrieveAccountApiException(400, "Invalid input");
    public static final RetrieveAccountApiException RetrieveAccount_retrieveAccount_404_Exception = new RetrieveAccountApiException(404, "Account not found");
    public static final RetrieveAccountApiException RetrieveAccount_retrieveAccount_500_Exception = new RetrieveAccountApiException(500, "Internal system error");
    public static final RetrieveAccountApiException RetrieveAccount_retrieveAccount_502_Exception = new RetrieveAccountApiException(502, "External system fault");
    

}