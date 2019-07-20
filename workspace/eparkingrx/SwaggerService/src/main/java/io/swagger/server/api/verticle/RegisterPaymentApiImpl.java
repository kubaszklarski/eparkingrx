package io.swagger.server.api.verticle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import io.swagger.server.api.model.Payment;
import io.swagger.server.api.model.RegisterPayment;
import io.swagger.server.api.model.RegisterPaymentResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class RegisterPaymentApiImpl implements RegisterPaymentApi{

	@Override
	public void registerPayment(RegisterPayment registerPayment, Handler<AsyncResult<RegisterPaymentResponse>> handler) {
		RegisterPaymentResponse response = new RegisterPaymentResponse();
		Payment payment = new Payment();
		payment.setAccountId(UUID.randomUUID().toString());
		payment.setAmount(new BigDecimal(Math.random()).multiply(new BigDecimal(1000)).setScale(2, RoundingMode.CEILING));
		payment.setPaymentId(UUID.randomUUID().toString());
		response.setPayment(payment);
		handler.handle(Future.succeededFuture(response));
	}

}
