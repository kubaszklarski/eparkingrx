package pl.eparkingrx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class ReceiverVerticle extends AbstractVerticle{
	
	final static Logger LOG = LoggerFactory.getLogger(ReceiverVerticle.class);
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		
		
		
		startFuture.complete();
		LOG.info("starting verticle " + this.getClass().getSimpleName() + " complete");
	}
	
}
