package pl.eparkingrx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class Starter {

	final static Logger LOG = LoggerFactory.getLogger(ClientVerticle.class);
	
	public static void main(String[] args) {

		ClusterManager manager = new HazelcastClusterManager();
		VertxOptions options = new VertxOptions().setClusterManager(manager);

		String vertx_cluster_public_host = "192.168.1.190";
		int vertx_cluster_public_port = 4001;
		String vertx_cluster_local_host = "172.17.0.2";
		int vertx_cluster_local_port = 4000;
		options.getEventBusOptions().setHost(vertx_cluster_local_host);
		options.getEventBusOptions().setPort(vertx_cluster_local_port);
		options.getEventBusOptions().setClusterPublicHost(vertx_cluster_public_host);
		options.getEventBusOptions().setClusterPublicPort(vertx_cluster_public_port);
		
		Vertx.clusteredVertx(options, resultHandler -> {
			if(resultHandler.succeeded()){
				Vertx vertx = resultHandler.result();
				vertx.deployVerticle(new ClientVerticle(), completionHandler -> {
					if(completionHandler.succeeded()){
						LOG.info("ClientVerticle deploy success");
					}else{
						LOG.error("ClientVerticle deploy fail");
						completionHandler.cause().printStackTrace();
					}
				});
			}else{
				LOG.error("ClientVerticle deploy fail");
				resultHandler.cause().printStackTrace();
			}
		});
	}

}
