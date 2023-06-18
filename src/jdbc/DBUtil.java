package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	public static Connection open() {
	      
	      String url = "jdbc:oracle:thin:@121.128.163.170:1521:xe";
	      String id = "jeju";
	      String pw = "java1234";
	      
	      try {
	         
	         Connection conn = null;
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection(url, id, pw);
	         
	         return conn;
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return null;
	      
	   }
	   
	   public static Connection open(String server, String id, String pw) {
	      
	      String url = "jdbc:oracle:thin:@" + server + ":1521:xe";
	      
	      try {
	         
	         Connection conn = null;
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection(url, id, pw);
	         
	         return conn;
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return null;
	      
	   }
	
}
