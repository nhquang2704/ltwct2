package vn.iostar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {


	   private static String USERNAME = "root";
	   private static String PASSWORD = "@quang123";
	   private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	   private static String URL = "jdbc:mysql://localhost:3306/ltwct2";

	   public static Connection getDatabaseConnection() throws Exception{
	    
	       try {
	    	   Class.forName(DRIVER);
			return DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	   }
	
	//Test ctrinh. Kisch phai chuot chon run as ->java application   
	   public static void main(String[] args) {
		   try {
			   new DBConnectMySQL();
			   System.out.print(DBConnectMySQL.getDatabaseConnection());
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
}
