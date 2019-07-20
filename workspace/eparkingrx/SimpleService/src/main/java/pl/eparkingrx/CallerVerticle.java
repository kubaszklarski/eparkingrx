package pl.eparkingrx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class CallerVerticle extends AbstractVerticle{
	
	final static Logger LOG = LoggerFactory.getLogger(CallerVerticle.class);
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		vertx.eventBus().consumer("RetrieveAccount", handler -> {
			LOG.info("mam cos " + handler.body());
			handler.reply(handler.body());
		});
		startFuture.complete();
		LOG.info("starting verticle " + this.getClass().getSimpleName() + " complete");
	}
	
}
