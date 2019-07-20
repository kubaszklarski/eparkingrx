package io.swagger.server.api.verticle;

import java.util.ArrayList;
import java.util.List;

import io.swagger.server.api.model.ValidateAccount;
import io.swagger.server.api.model.ValidateAccountResponse;
import io.swagger.server.api.model.ValidationResult;
import io.swagger.server.api.model.ValidationResult.ResultEnum;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class ValidateAccountApiImpl implements ValidateAccountApi{

	@Override
	public void validateAccount(ValidateAccount validateAccount, Handler<AsyncResult<ValidateAccountResponse>> handler) {
		ValidateAccountResponse response = new ValidateAccountResponse();
		ValidationResult validationResult = new ValidationResult();
		validationResult.setResult(ResultEnum.INVALID);
		List<String> messages = new ArrayList<String>();
		messages.add("Client.Pesel value invalid. Validation result: length greater then 11");
		messages.add("Vehicle.RegistrationNumber value invalid. Validation result: does not match pattern [A-Z][A-Z][0-9]{5}");
		validationResult.setMessages(messages);
		response.setValidationResult(validationResult);
		handler.handle(Future.succeededFuture(response));
	}

}
