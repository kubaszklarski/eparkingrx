package io.swagger.server.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.camel.Body;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.phiz71.vertx.swagger.router.OperationIdServiceIdResolver;
import com.github.phiz71.vertx.swagger.router.SwaggerRouter;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.swagger.server.api.model.RegisterAccount;
import io.vertx.camel.CamelBridge;
import io.vertx.camel.CamelBridgeOptions;
import io.vertx.camel.OutboundMapping;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class MainApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(MainApiVerticle.class);

    private int serverPort = 8080;
    protected Router router;

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
        router = Router.router(vertx);
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Json.mapper.registerModule(new JavaTimeModule());
        FileSystem vertxFileSystem = vertx.fileSystem();
        vertxFileSystem.readFile("swagger.json", readFile -> {
            if (readFile.succeeded()) {
                Swagger swagger = new SwaggerParser().parse(readFile.result().toString(Charset.forName("utf-8")));
                Router swaggerRouter = SwaggerRouter.swaggerRouter(router, swagger, vertx.eventBus(), new OperationIdServiceIdResolver());

                deployVerticles(startFuture);
                
				deployCamel(startFuture);

                vertx.createHttpServer()
                    .requestHandler(swaggerRouter) 
                    .listen(serverPort, h -> {
                        if (h.succeeded()) {
                            startFuture.complete();
                        } else {
                            startFuture.fail(h.cause());
                        }
                    });
            } else {
            	startFuture.fail(readFile.cause());
            }
        });        		        
    }
      
    public void deployVerticles(Future<Void> startFuture) {
        
        vertx.deployVerticle("io.swagger.server.api.verticle.RegisterAccountApiVerticle", res -> {
            if (res.succeeded()) {
                LOGGER.info("RegisterAccountApiVerticle : Deployed");
            } else {
                startFuture.fail(res.cause());
                LOGGER.error("RegisterAccountApiVerticle : Deployment failed");
            }
        });
        
    }
    
    
    
    public void deployCamel(Future<Void> startFuture) {
    	
    	Processor RegisterAccountProcessor = new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				JsonObject jo = exchange.getIn().getBody(JsonObject.class);
				RegisterAccount registerAccount = Json.mapper.readValue(jo.encode(), RegisterAccount.class);
				LOGGER.info("procesor " + registerAccount.getVehicle().getRegistrationNumber());
				exchange.getIn().setBody(registerAccount);
			}
		};
    	
    	CamelContext camel = new DefaultCamelContext();
    	CamelBridgeOptions bridgeOptions = new CamelBridgeOptions(camel)
    		.addOutboundMapping(OutboundMapping.fromVertx("RegisterAccountConsumer").toCamel("direct:RegisterAccountConsumer")
    	);
    	CamelBridge.create(vertx, bridgeOptions).start(res -> {
    		if (res.succeeded()) {
    			LOGGER.info("CamelBridge : Deployed");
			} else {
				LOGGER.error("CamelBridge : Deployment failed");
			}
    	});
    	
    	RouteBuilder rb = new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				
			}
		};
		
    	try {
			camel.addRoutes(new RouteBuilder(){
				@Override
				public void configure() throws Exception {
					from("direct:RegisterAccountConsumer")
					.routeId("RegisterAccount")
					.log("log z camela")
					.process(RegisterAccountProcessor)
					.log("costam dziala")
					.transform(simple("dupa odpowiedz"))
					.end();
				}
			});
			camel.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("sss")
					.log("dupa")
					.end();
				}
			});
			camel.start();
			LOGGER.info("CamelBridge : Started");
		} catch (Exception e) {
			LOGGER.info("CamelBridge : Start failed");
			e.printStackTrace();
		}

    }
    
}