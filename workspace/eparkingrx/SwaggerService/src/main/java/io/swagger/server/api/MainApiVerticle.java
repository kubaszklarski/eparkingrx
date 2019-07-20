package io.swagger.server.api;

import java.nio.charset.Charset;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.phiz71.vertx.swagger.router.OperationIdServiceIdResolver;
import com.github.phiz71.vertx.swagger.router.SwaggerRouter;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MainApiVerticle extends AbstractVerticle {
	
	//logging factory
	final static Logger LOGGER = LoggerFactory.getLogger(MainApiVerticle.class);
	
	//main method to run application
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MainApiVerticle());
	}
    
	//ports
    private static int MAIN_SERVICE_PORT = 8080;
    private static int JSON_SERVICE_PORT = 8081;
    private static int UI_SERVICE_PORT = 8082;
     
    //method called when verticle is deployed
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        //run all stuff as composition
    	Future<Void> deployVerticlesDeployment = Future.future();
        deployVerticles(deployVerticlesDeployment);
        deployVerticlesDeployment.compose(result -> {
        	Future<Void> deploySwaggerJsonDeployment = Future.future();
        	deploySwaggerJson(deploySwaggerJsonDeployment);
        	return deploySwaggerJsonDeployment;
        }).compose(result -> {
        	Future<Void> deploySwaggerUiDeployment = Future.future();
        	deploySwaggerUi(deploySwaggerUiDeployment);
        	return deploySwaggerUiDeployment;
        }).compose(result -> {
        	Future<Void> deploySwaggerRouterDeployment = Future.future();
        	deploySwaggerRouter(deploySwaggerRouterDeployment);
        	return deploySwaggerRouterDeployment;
        }).setHandler(result -> {
			if(result.succeeded()) {
				LOGGER.info("MainApiVerticle : Deployed");
				startFuture.complete();
			}else{
            	LOGGER.error("MainApiVerticle : Deployment failed");
            	result.cause().printStackTrace();
                startFuture.fail(result.cause());
			}
		});
    }
    
    //swagger listener
    public void deploySwaggerRouter(Future<Void> deploySwaggerRouterDeployment) {
    	Json.mapper.registerModule(new JavaTimeModule());
    	FileSystem vertxFileSystem = vertx.fileSystem();
    	vertxFileSystem.readFile("swagger.json", handler -> {
    		if(handler.succeeded()){
        		Router router = Router.router(vertx);
        		Swagger swagger = new SwaggerParser().parse(handler.result().toString(Charset.forName("utf-8")));
                Router swaggerRouter = SwaggerRouter.swaggerRouter(router, swagger, vertx.eventBus(), new OperationIdServiceIdResolver());
                swaggerRouter.route("/*").handler(requestHandler -> {
        			requestHandler.response().putHeader("Access-Control-Allow-Origin", "*").putHeader("Access-Control-Allow-Headers", "*").end();
        		});
                vertx.createHttpServer().requestHandler(swaggerRouter).listen(MAIN_SERVICE_PORT, listenHandler -> {
                    if (listenHandler.succeeded()) {
                    	LOGGER.info("SwaggerRouter : Deployed");
                    	deploySwaggerRouterDeployment.complete();
                    } else {
                    	LOGGER.error("SwaggerRouter : Deployment failed");
                    	listenHandler.cause().printStackTrace();
                    	deploySwaggerRouterDeployment.fail(listenHandler.cause());
                    }
                });	
    		}else{
				LOGGER.error("SwaggerRouter : Deployment failed");
				handler.cause().printStackTrace();
				deploySwaggerRouterDeployment.fail(handler.cause());
			}
    	});
    }
    
    //helper method to provide json file as http GET for swagger ui
    public void deploySwaggerJson(Future<Void> deploySwaggerJsonDeployment){
		FileSystem vertxFileSystem = vertx.fileSystem();
		vertxFileSystem.readFile("swagger.json", handler -> {
			if(handler.succeeded()){
				Router router = Router.router(vertx);
				router.route("/json").handler(requestHandler -> {
					requestHandler.response().putHeader("Access-Control-Allow-Origin", "*").end(handler.result().toJsonObject().encodePrettily());
				});
				vertx.createHttpServer().requestHandler(router).listen(JSON_SERVICE_PORT, listenHandler -> {
					if(listenHandler.succeeded()){
						LOGGER.info("SwaggerJson : Deployed");
						deploySwaggerJsonDeployment.complete();
					}else{
						LOGGER.error("SwaggerJson : Deployment failed");
						listenHandler.cause().printStackTrace();
						deploySwaggerJsonDeployment.fail(listenHandler.cause());
					}
				});
			}else{
				LOGGER.error("SwaggerJson : Deployment failed");
				handler.cause().printStackTrace();
				deploySwaggerJsonDeployment.fail(handler.cause());
			}
		});
    }
    
    //swagger ui service
    public void deploySwaggerUi(Future<Void> deploySwaggerUiDeployment){
		Router router = Router.router(vertx);
		router.route("/ui/*").handler(StaticHandler.create().setCachingEnabled(false)).handler(requestHandler -> {
			requestHandler.response().putHeader("Access-Control-Allow-Origin", "*").end();
		});
		vertx.createHttpServer().requestHandler(router).listen(UI_SERVICE_PORT, listenHandler -> {
			if(listenHandler.succeeded()){
				LOGGER.info("SwaggerUi : Deployed");
				deploySwaggerUiDeployment.complete();
			}else{
				LOGGER.error("SwaggerUi : Deployment failed");
				listenHandler.cause().printStackTrace();
				deploySwaggerUiDeployment.fail(listenHandler.cause());
			}
		});
    }
    
    //verticle deployment (to be removed when dockerize)
    public void deployVerticles(Future<Void> deployVerticlesDeployment) {
        
        vertx.deployVerticle("io.swagger.server.api.verticle.ChargeFeeApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("ChargeFeeApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("ChargeFeeApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.CheckBalanceApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("CheckBalanceApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("CheckBalanceApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.DisableAccountApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("DisableAccountApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("DisableAccountApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.EndParkingApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("EndParkingApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("EndParkingApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.RegisterAccountApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("RegisterAccountApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("RegisterAccountApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.RegisterPaymentApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("RegisterPaymentApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("RegisterPaymentApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.RetrieveAccountApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("RetrieveAccountApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("RetrieveAccountApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.SetRateApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("SetRateApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("SetRateApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.StartParkingApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("StartParkingApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("StartParkingApiVerticle : Deployment failed");
            }
        });
        
        vertx.deployVerticle("io.swagger.server.api.verticle.ValidateAccountApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("ValidateAccountApiVerticle : Deployed");
            } else {
            	deployVerticlesDeployment.fail(res.cause());
                LOGGER.error("ValidateAccountApiVerticle : Deployment failed");
            }
        });
        
        //make sure that all verticles were deployed and then complete the future
        if(!deployVerticlesDeployment.failed()) {
        	LOGGER.info("Verticles : Deployed");
        	deployVerticlesDeployment.complete();	
        }else{
        	LOGGER.info("Verticles : Deployment failed");
        }
        
    }
}