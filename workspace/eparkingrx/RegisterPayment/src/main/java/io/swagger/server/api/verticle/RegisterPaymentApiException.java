package io.swagger.server.api.verticle;

import io.swagger.server.api.model.Fault;
import io.swagger.server.api.MainApiException;
import io.swagger.server.api.model.RegisterPayment;
import io.swagger.server.api.model.RegisterPaymentResponse;

public final class RegisterPaymentApiException extends MainApiException {
    public RegisterPaymentApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final RegisterPaymentApiException RegisterPayment_registerPayment_400_Exception = new RegisterPaymentApiException(400, "Invalid input");
    public static final RegisterPaymentApiException RegisterPayment_registerPayment_500_Exception = new RegisterPaymentApiException(500, "Internal system error");
    public static final RegisterPaymentApiException RegisterPayment_registerPayment_502_Exception = new RegisterPaymentApiException(502, "External system fault");
    

}