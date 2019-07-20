package pl.eparkingrx;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.asyncsql.MySQLClient;

public class DemoSqlClient {

	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		
		MySharedPool(vertx);
		//MyNamedPool(vertx);
		//MyNonSharedPool(vertx);
		
	}
	
	public static void MySharedPool(Vertx vertx){
		JsonObject mySQLClientConfig = new JsonObject()
				.put("host", "localhost")
				.put("port", 3306)
				.put("username", "root")
				.put("password", "Miko2019")
				.put("database", "account");
		SQLClient mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig);
		mySQLClient.getConnection(res -> {
			if(res.succeeded()) {
				SQLConnection connection = res.result();
				String sql = "select idAccounts, FirstName, Production from account.accounts limit 3;";
				connection.query(sql, res2 -> {
					if(res2.succeeded()){
						System.out.println("result: " + res2.result().toJson().encodePrettily());
					}else {
						System.out.println("failed to execute sql");
					}
				});
			}else{
				System.out.println("failed to get connection");
			}
		});
		mySQLClient.close();
	}
	
	public static void MyNamedPool(Vertx vertx) {
		JsonObject mySQLClientConfig = new JsonObject()
				.put("host", "localhost")
				.put("port", 3306)
				.put("username", "root")
				.put("password", "Miko2019")
				.put("database", "account");
		SQLClient mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig, "MyAccountClientSharedPool");
		mySQLClient.getConnection(res -> {
			if(res.succeeded()) {
				SQLConnection connection = res.result();
				String sql = "select idAccounts, FirstName, Production from account.accounts limit 3;";
				connection.query(sql, res2 -> {
					if(res2.succeeded()){
						System.out.println("result: " + res2.result().toJson().encodePrettily());
					}else {
						System.out.println("failed to execute sql");
					}
				});
			}else{
				System.out.println("failed to get connection");
			}
		});
		mySQLClient.close();
	}
	
	public static void MyNonSharedPool(Vertx vertx) {
		JsonObject mySQLClientConfig = new JsonObject()
				.put("host", "localhost")
				.put("port", 3306)
				.put("username", "root")
				.put("password", "Miko2019")
				.put("database", "account");
		SQLClient mySQLClient = MySQLClient.createNonShared(vertx, mySQLClientConfig);
		mySQLClient.getConnection(res -> {
			if(res.succeeded()) {
				SQLConnection connection = res.result();
				String sql = "select idAccounts, FirstName, Production from account.accounts limit 3;";
				connection.query(sql, res2 -> {
					if(res2.succeeded()){
						System.out.println("result: " + res2.result().toJson().encodePrettily());
					}else {
						System.out.println("failed to execute sql");
					}
				});
			}else{
				System.out.println("failed to get connection");
			}
		});
		mySQLClient.close();
	}
	

}
