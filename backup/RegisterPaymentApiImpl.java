package io.swagger.server.api.verticle;

import io.swagger.server.api.model.RegisterPayment;
import io.swagger.server.api.model.RegisterPaymentResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class RegisterPaymentApiImpl implements RegisterPaymentApi{

	@Override
	public void registerPayment(RegisterPayment registerPayment,
			Handler<AsyncResult<RegisterPaymentResponse>> handler) {
		// TODO Auto-generated method stub
		
	}

}
