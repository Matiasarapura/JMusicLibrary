package algoII.tp.dbUtil;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class DbConnectionSingleton {

	private static  DbConnectionSingleton dbSingleton;
	private static Connection connection = null;
	private static final String dbName = "jdbc:mysql://localhost:3306/mkyongcom";
	private static final String dbUser = "root";
	private static final String dbPassword = "";
	
	private DbConnectionSingleton(){};
	
	public static DbConnectionSingleton getInstance(){
		
		if (dbSingleton == null){
			dbSingleton = new DbConnectionSingleton();
		}
		return dbSingleton;
		
	}
	
	public Connection getConnection(){
		if(connection==null){
			try {
				connection =  DriverManager.getConnection(dbName, dbUser,dbPassword);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Connection Failed");
				e.printStackTrace();
			}
		}
		return connection;
	}
}
