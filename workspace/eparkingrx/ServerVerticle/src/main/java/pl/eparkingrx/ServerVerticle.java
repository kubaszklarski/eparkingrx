package pl.eparkingrx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class ServerVerticle extends AbstractVerticle{
	
	final static Logger LOG = LoggerFactory.getLogger(ServerVerticle.class);
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		final EventBus eventBus = vertx.eventBus();
		eventBus.consumer("ClusterAddress", handler -> {
			LOG.info("ServerVerticle: receive request " + handler.body());
			LOG.info("ServerVerticle: Current Thread Id " + Thread.currentThread().getId() + " Is Clustered " + vertx.isClustered());
		});
		startFuture.complete();
	}

}
