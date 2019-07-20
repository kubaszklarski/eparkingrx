package io.swagger.server.api.verticle;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class DisableAccountApiImpl implements DisableAccountApi{

	@Override
	public void disableAccount(String accountId, Handler<AsyncResult<Void>> handler) {
		handler.handle(Future.succeededFuture());
	}

}
