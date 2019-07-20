package io.swagger.server.api.verticle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

import io.swagger.server.api.model.Account;
import io.swagger.server.api.model.Client;
import io.swagger.server.api.model.RetrieveAccountResponse;
import io.swagger.server.api.model.Vehicle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class RetrieveAccountApiImpl implements RetrieveAccountApi{

	@Override
	public void retrieveAccount(String accountId, Handler<AsyncResult<RetrieveAccountResponse>> handler) {
		RetrieveAccountResponse response = new RetrieveAccountResponse();
		Account account = new Account();
		Client client = new Client();
		client.setFirstName("Adam");
		client.setLastName("Adamczyk");
		client.setPesel("85040200456");
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("Opel");
		vehicle.setModel("Astra");
		vehicle.setProductionDate(LocalDate.now());
		vehicle.setRegistrationNumber("WF98561");
		vehicle.setVinNumber("5TFUX4EN6BX006064");
		account.setAccountId(UUID.randomUUID().toString());
		account.setAmount(new BigDecimal(Math.random()).multiply(new BigDecimal(1000)).setScale(2, RoundingMode.CEILING));
		account.setClient(client);
		account.setVehicle(vehicle);
		response.setAccount(account);
		handler.handle(Future.succeededFuture(response));
	}

}
