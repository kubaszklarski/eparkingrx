package io.swagger.server.api.verticle;

import java.util.UUID;

import io.swagger.server.api.model.RegisterAccount;
import io.swagger.server.api.model.RegisterAccountResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class RegisterAccountApiImpl implements RegisterAccountApi{
	
	final static Logger LOGGER = LoggerFactory.getLogger(RegisterAccountApiImpl.class);
	
	@Override
	public void registerAccount(RegisterAccount registerAccount, Handler<AsyncResult<RegisterAccountResponse>> handler) {
		LOGGER.info(registerAccount.getVehicle().getProductionDate());
		RegisterAccountResponse response = new RegisterAccountResponse();
		response.setAccountId(UUID.randomUUID().toString());
		handler.handle(Future.succeededFuture(response));
	}

}
