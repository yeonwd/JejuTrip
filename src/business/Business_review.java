package business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import jdbc.DBUtil;
import main.UI;

public class Business_review {
	
	//1. 사업장 컬럼 삭제
	public static void admin_business_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from business where Business_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_business_delete
	
	
	//2. 광고 컬럼 삭제
	public static void admin_advertisement_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Advertisement where Ad_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_advertisement_delete
	
	
	//3. 관광지 컬럼 삭제
	public static void admin_Tour_area_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Tour_area where Tour_area_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Tour_area_delete
	
	
	//4. 주유소 컬럼 삭제
	public static void admin_Oil_bank_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Oil_bank where Oil_bank_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Oil_bank_delete
	
	
	//5. 전기차 충전소 컬럼 삭제
	public static void admin_Electronic_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Electronic_charge where Electronic_charge_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Electronic_delete
	
	
	//6. 투어 컬럼 삭제
	public static void admin_Tour_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Tour where Tour_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Tour_delete
	
	
	//7. 예약 컬럼 삭제
	public static void admin_reservation_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Reservation where reservation_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_reservation_delete
	
	
	//8. 렌트카 예약 컬럼 삭제
	public static void admin_Rentcar_Reservation_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Rentcar_Reservation where rentcar_reservation_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Rentcar_Reservation_delete
	
	
	//9. 충전내역 컬럼 삭제
	public static void admin_Charge_record_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Charge_record where Charge_record_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Charge_record_delete
	
	
	//10. 리뷰 컬럼 삭제
	public static void admin_Review_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Review where review_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Review_delete
	
	
	
	//11. 리뷰 컬럼 삭제
	public static void admin_Notice_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Notice where Notice_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Notice_delete
	
	
	
	//12. 문의 컬럼 삭제
	public static void admin_Question_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Question where question_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Question_delete
	
	
	//13. 유저 탈퇴
	public static void admin_Users_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Users where user_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Users_delete
	
	
	//13. 유저 탈퇴
	public static void admin_Owner_delete(String num) {
		
		System.out.println(UI.alignCenter("== 삭제 =="));
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Owner where owner_seq = '%s'", num);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
						
			rs.close();
			stat.close();
			conn.close();
			System.out.println("삭제가 완료 되었습니다.");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}//admin_Owner_delete
	
	
	
	
	
	
	
	
	


}
	

