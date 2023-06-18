package reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jdbc.DBUtil;
import main.UI;

public class RentCar {

	//렌트카 조회
	public static void RentCar_select() {
		
		System.out.println(UI.alignCenter("== 렌트카 조회 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select r.Rentcar_seq, b.business_name, r.Car_kind, r.Rentcar_price \r\n"
										+ "from Rentcar r\r\n"
										+ "inner join Business b\r\n"
										+ "on b.business_seq = r.business_seq");
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("렌트카 번호 : %s \r\n",rs.getString("Rentcar_seq"));
			    System.out.printf("사업장 이름 : %s \r\n",rs.getString("business_name"));
			    System.out.printf("차종 : %s \r\n",rs.getString("Car_kind"));
			    System.out.printf("렌트카 : %s \r\n", rs.getString("Rentcar_price"));
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
