package pl.eparkingrx;

import java.nio.charset.Charset;

import com.github.phiz71.vertx.swagger.router.OperationIdServiceIdResolver;
import com.github.phiz71.vertx.swagger.router.SwaggerRouter;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.file.FileSystem;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class HttpServerVerticle extends AbstractVerticle{
	
	final static Logger LOG = LoggerFactory.getLogger(HttpServerVerticle.class);
	
	public void setupRouter(String swaggerFile, Handler<AsyncResult<Router>> setupRouter){
		FileSystem vertxFileSystem = vertx.fileSystem();
		vertxFileSystem.readFile(swaggerFile, read -> {
			LOG.info("loading swagger from file " + swaggerFile);
			if(read.succeeded()){
				Swagger swagger = new SwaggerParser().parse(read.result().toString(Charset.forName("utf-8")));
				LOG.info("loading swagger " + swagger.getInfo().getTitle());
				Router router = SwaggerRouter.swaggerRouter(Router.router(vertx), swagger, vertx.eventBus(), new OperationIdServiceIdResolver());
				setupRouter.handle(Future.succeededFuture(router));
				LOG.info("loading swagger complete");
			}else{
				setupRouter.handle(Future.failedFuture(read.cause()));
				LOG.info("loading swagger failed");
			}
		});		
	}
	
	public void setupListener(Router router, int port, Handler<AsyncResult<Void>> setupListener){
		vertx.createHttpServer().requestHandler(router).listen(port, listenHandler -> {
			LOG.info("creating http listener on port " + port);
			if(listenHandler.succeeded()){
				setupListener.handle(Future.succeededFuture());
				LOG.info("creating http listener complete");
			}else{
				setupListener.handle(Future.failedFuture(listenHandler.cause()));
				LOG.info("creating http listener failed");
			}
		});
	}
	
	public void setupSwagger(String swaggerFile) {
		Router router = Router.router(vertx);
		FileSystem vertxFileSystem = vertx.fileSystem();
		vertxFileSystem.readFile(swaggerFile, read -> {
			if(read.succeeded()){
				router.route("/json").handler(requestHandler -> {
					requestHandler.response()
						.setStatusCode(200)
						.putHeader("Access-Control-Allow-Origin", "*")
						.putHeader("Access-Control-Allow-Methods", "GET")
						.end(read.result().toJsonObject().encodePrettily());
				});

			}
		});
		vertx.createHttpServer().requestHandler(router).listen(8081);
	}
	
	public void setupSwaggerUi(){
		Router router = Router.router(vertx);
		router.get("/swagger").handler(StaticHandler.create());
		vertx.createHttpServer().requestHandler(router).listen(8082);
	} 
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		
		setupSwagger("swagger.json");
		
		setupSwaggerUi();
		
		setupRouter("swagger.json", setupRouter -> {
			if(setupRouter.succeeded()){
				Router router = setupRouter.result();
				setupListener(router, 8080, setupListener -> {
					if(setupListener.succeeded()){
						startFuture.complete();
						LOG.info("starting verticle " + this.getClass().getSimpleName() + " complete");
					}else{
						startFuture.fail(setupListener.cause());
						LOG.info("starting verticle " + this.getClass().getSimpleName() + " failed");
					}
				});
			}else{
				startFuture.fail(setupRouter.cause());
				LOG.info("starting verticle " + this.getClass().getSimpleName() + " failed");
			}
		});
		
	}
		
}
