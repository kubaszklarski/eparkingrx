package pl.eparkingrx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class ServerVerticle extends AbstractVerticle{

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new ServerVerticle());
	}

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		
		
		startFuture.complete();
	}


}
