package vn.iostar.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQL {
	
	private final String serverName = "MSI";
	private final String dbName = "ltwct2";
	private final String portNumber = "1433";
	private final String instance ="";
	private final String userID = "sa";
	private final String password = "@quang123";
	
	
	public Connection getConnection() throws ClassNotFoundException {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jbdc:sqlserver://"+serverName+":"+portNumber + "\\" + instance +";databaseName="+dbName;
			if(instance == null || instance.trim().isEmpty())
				
				url = "jdbc:sqlserver://" + serverName + ":" +portNumber + ";database=" + dbName;
			conn = DriverManager.getConnection(url,userID, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
			
			
			return conn;
	}
			
	
	
	
}


