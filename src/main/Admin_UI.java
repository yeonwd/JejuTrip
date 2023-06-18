package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import business.Business_DB;
import business.main;
import jdbc.DBUtil;

public class Admin_UI {

	public static void Admin_name() {
		name(UI.admin_id);
		System.out.println(UI.alignCenter("관리자 로그인 성공!"));
		System.out.printf(UI.alignCenter("안녕하세요, %s 님!"), UI.name);
		System.out.println("               ");
		System.out.println("               ");
		System.out.println(UI.alignCenter("[제주 어때 with 관리자]"));
		System.out.println("               ");
	}
	
	public static void name(String id) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select Admin_name from admin where admin_id='%s'", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
//					System.out.println(rs.getString("Owner_name"));
				UI.name = rs.getString("Admin_name");
			}
			
			
					
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
