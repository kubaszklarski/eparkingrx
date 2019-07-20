package test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class TestVerticle extends AbstractVerticle{
	
	public static void main(String[] args) {
		Vertx.vertx().deployVerticle(new TestVerticle());
	}
	
	@Override
	public void start() throws Exception {
		
		FileSystem vertxFileSystem = vertx.fileSystem();
		vertxFileSystem.readFile("swagger.json", handler -> {
			Router jsonRouter = Router.router(vertx);
			jsonRouter.route("/json").handler(requestHandler -> {
				requestHandler
				.response()
				.setStatusCode(200)
				.putHeader("Access-Control-Allow-Origin", "*")
				.putHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE")
				.end(handler.result().toJsonObject().encodePrettily());
			});
			vertx.createHttpServer().requestHandler(jsonRouter).listen(8087);
		});
		
		Router uiRouter = Router.router(vertx);
		uiRouter.route("/swagger/*").handler(StaticHandler.create().setCachingEnabled(false));
		vertx.createHttpServer().requestHandler(uiRouter).listen(8089);

	}

}
