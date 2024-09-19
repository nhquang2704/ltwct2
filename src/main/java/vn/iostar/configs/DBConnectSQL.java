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
	
	
	public Connection getConnection() throws Exception {
			String url = "jbdc:sqlserver://"+serverName+":"+portNumber + "\\" + instance +";databaseName="+dbName;
			
			if(instance == null || instance.trim().isEmpty())
				
				url = "jdbc:sqlserver://" + serverName + ":" +portNumber + ";database=" + dbName;
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			return DriverManager.getConnection(url, userID,password);
	}
			
	
	//Test ctrinh. Kisch phai chuot chon run as ->java application   
	   public static void main(String[] args) {
		   try {
			   new DBConnectMySQL();
			   System.out.print(new DBConnectSQL().getConnection());
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	
}


