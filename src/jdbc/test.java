package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class test {

	public static void main(String[] args) {
		
		Connection conn = DBUtil.open();
		
		try {
			System.out.println(conn.isClosed());
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
