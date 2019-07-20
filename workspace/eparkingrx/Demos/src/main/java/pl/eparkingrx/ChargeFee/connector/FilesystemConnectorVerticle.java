package pl.eparkingrx.ChargeFee.connector;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class FilesystemConnectorVerticle extends AbstractVerticle{
	
	private final Logger logger = LoggerFactory.getLogger(FilesystemConnectorVerticle.class);
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		logger.info("start FilesystemConnectorVerticle");
		startFuture.complete();
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		logger.info("stop FilesystemConnectorVerticle");
		stopFuture.complete();
	}
	
}
