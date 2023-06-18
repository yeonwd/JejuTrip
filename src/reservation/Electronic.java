package reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jdbc.DBUtil;
import main.UI;

public class Electronic {

	public static void Electronic_select() {
		
		System.out.println(UI.alignCenter("== 전기차 충전소 조회 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select * from Electronic_charge");
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("충전소 번소 : %s \r\n",rs.getString("Electronic_charge_seq"));
			    System.out.printf("충전소 이름 : %s \r\n",rs.getString("Electronic_charge_name"));
			    System.out.printf("충전소 주소 : %s \r\n",rs.getString("Electronic_charge_address"));
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
	
	
	public static String Reservation_max() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		String result = "";
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select max(reservation_seq) from reservation");
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				result = String.format("%s",rs.getString("max(reservation_seq)"));
			}
						
			rs.close();
			stat.close();
			conn.close();
			
			
			return result;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static String Caculate_price(String business_seq) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		String price = "";
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select Accommodate_price from Accommodate where business_seq = '%s'", business_seq);
			
			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				price = String.format("%s",rs.getString("Accommodate_price"));
				
			}
						
			rs.close();
			stat.close();
			conn.close();
			
			
			return price;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
