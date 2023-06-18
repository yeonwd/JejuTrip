package reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jdbc.DBUtil;
import main.UI;

public class OilBank {

	public static void OilBank_select() {
		
		System.out.println(UI.alignCenter("== 주유소 조회 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select * from Oil_bank");
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("주유소 번호 : %s \r\n",rs.getString("Oil_bank_seq"));
			    System.out.printf("주유소 이름 : %s \r\n",rs.getString("Oil_bank_name"));
			    System.out.printf("주유소 주소 : %s \r\n",rs.getString("Oil_bank_address"));
			    System.out.println();
			}
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
