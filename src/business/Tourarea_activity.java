package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import jdbc.DBUtil;
import main.UI;
import user.UserDAO;

public class Tourarea_activity {

	public static void tourarea_activity_main() {
		
		//***액티비티 결제내역 업데이트 해야됨

		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			System.out.println();
			System.out.println();
			System.out.println(UI.alignCenter("== 관광지/액티비티 조회 및 예약 =="));
			System.out.println();
			System.out.println(UI.alignCenter("1. 관광지 조회"));
			System.out.println(UI.alignCenter("2. 액티비티 조회 및 예약"));
			System.out.println();
			System.out.println(UI.alignCenter("0. 이전 페이지"));
			System.out.println();
			System.out.println();
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 관광지 조회
				tourarea();
				
			} else if(sel.equals("2")) {
				
				//2. 액티비티 조회
				activity();
				
			} else if(sel.equals("0")) {
				
				//0. 홈 화면으로 돌아가기
				
				System.out.println();
				System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
				loop = false;
				
			} 
			
		}//while(menu)
		
		
	}



	
	private static void tourarea() {
		//관광지 정보 출력 > select
		

		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			System.out.println();
			System.out.println();
			System.out.println(UI.alignCenter("== 관광지 조회 =="));
			System.out.println();
			System.out.println();
	      
		      Connection conn = null;
		      Statement stat = null;
		      ResultSet rs = null;
		      
		      try {
		         
		         conn = DBUtil.open();
		         stat = conn.createStatement();
		         
		         
		         String sql = String.format("select * from Tour_area");
		         
		         
		         rs = stat.executeQuery(sql);
		         
		         while (rs.next()) {
		        	 System.out.printf("[번호] : %s \r\n", rs.getString("Tour_area_seq"));
		            System.out.printf("[관광지명] : %s \r\n", rs.getString("Tour_area_name"));
		            System.out.printf("[주소] : %s \r\n", rs.getString("Tour_area_address"));
		            System.out.println();
		         }
		         
		         
		               
		         rs.close();
		         stat.close();
		         conn.close();
		         
		      } 
		      catch (Exception e) {
		         e.printStackTrace();
		      }
			
	        System.out.println();
	        System.out.println();
	        System.out.println(UI.alignCenter("== 관광지 조회 =="));
	        System.out.println(UI.alignCenter("1. 리뷰 조회"));
	        System.out.println();
	        System.out.println(UI.alignCenter("0. 이전 페이지"));
	        System.out.println();
	        System.out.println();
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				//리뷰조회
				tour_review();
				
			} else if(sel.equals("0")) {
				
				//0. 이전으로 돌아가기
				System.out.println();
				System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
				loop = false;
				
			} 
			
		}//while(menu)
		
		
	}
	
	private static void tour_review() {


		boolean loop = true;
	    Scanner scan = new Scanner(System.in);
	    
	    while (loop) {
		
			System.out.println();
			System.out.println();
			System.out.println(UI.alignCenter("== 리뷰 조회 =="));
			System.out.println();
			System.out.println();
		      
		      Connection conn = null;
		      Statement stat = null;
		      ResultSet rs = null;
		      
		      try {
		    	  
		    	  System.out.print("🏝️ 리뷰를 조회할 관광지명을 입력하세요 : ");
		    	  scan = new Scanner(System.in);
		    	  String sel = scan.nextLine();
		    	  System.out.println();
		         
		         conn = DBUtil.open();
		         stat = conn.createStatement();
		         
		         
		         String sql = String.format("SELECT "
		         		+ "    r.review_seq, "
		         		+ "    t.tour_area_name, "
		         		+ "    r.review_content, "
		         		+ "    r.review_open "
		         		+ "FROM review r "
		         		+ "        INNER JOIN tour_area t ON t.tour_area_seq = r.tour_area_seq "
		         		+ "                WHERE t.tour_area_name = '%s'", sel);
		         
		         
		         rs = stat.executeQuery(sql);
		         
		         while (rs.next()) {
		            System.out.printf("[상호명] : %s \r\n", rs.getString("Tour_area_name"));
		            System.out.println();
		            System.out.printf("[리뷰] : %s \r\n", rs.getString("review_content"));
		            System.out.println();
		         }
		         
		         
		               
		         rs.close();
		         stat.close();
		         conn.close();
		         
		      } 
		      catch (Exception e) {
		         e.printStackTrace();
		      }
	      
				
				System.out.println();
				System.out.println();
				System.out.println(UI.alignCenter("== 리뷰 조회 =="));
				System.out.println();
				System.out.println(UI.alignCenter("0. 이전 페이지"));
				System.out.println();
				System.out.println();
				
				System.out.print("🔎 원하시는 서비스를 입력하세요 : ");
				String sel = scan.nextLine();
				
				if(sel.equals("0")) {
					// 0. 이전 페이지로 돌아가기
					System.out.println();
					System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
					loop = false;
				} 
				
	    }//while
		
	}




	private static void activity() {
		//1번 예약
		//액티비티 정보 출력

		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			System.out.println();
			System.out.println();
			System.out.println(UI.alignCenter("== 액티비티 조회 =="));
			System.out.println();
			System.out.println();
	      
		      Connection conn = null;
		      Statement stat = null;
		      ResultSet rs = null;
		      
		      try {
		         
		         conn = DBUtil.open();
		         stat = conn.createStatement();
		         
		         
		         String sql = String.format("select * from Business where Business_kind = '액티비티'");
		         
		         
		         rs = stat.executeQuery(sql);
		         
		         while (rs.next()) {
		        	 System.out.printf("[번호] : %s \r\n", rs.getString("Business_seq"));
		            System.out.printf("[상호명] : %s \r\n", rs.getString("Business_name"));
		            System.out.printf("[주소] : %s \r\n", rs.getString("BUISINESS_Address"));
		            System.out.println();
		         }
		         
		         
		               
		         rs.close();
		         stat.close();
		         conn.close();
		         
		      } 
		      catch (Exception e) {
		         e.printStackTrace();
		      }
			
	        System.out.println();
	        System.out.println();
	        System.out.println(UI.alignCenter("== 액티비티 조회 =="));
	        System.out.println(UI.alignCenter("1. 예약하기"));
	        System.out.println();
	        System.out.println(UI.alignCenter("0. 이전 페이지"));
	        System.out.println();
	        System.out.println();
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				//예약하기
				activity_reservation();
				
			} else if(sel.equals("0")) {
				
				//0. 이전으로 돌아가기
				System.out.println();
				System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
				loop = false;
				
			} 
			
		}//while(menu)
		
		
		
	}
	

	public static ResultSet activity_reservation() {
	    boolean loop = true;
	    Scanner scan = new Scanner(System.in);
	    ResultSet rs = null;
	    
	    while (loop) {
	        System.out.println();
	        System.out.println();
	        System.out.println(UI.alignCenter("== 액티비티 예약 =="));
	        System.out.println();
	        System.out.println();
	        
	        
	        //유저번호 받아오는걸로 바꿔야됨!!
	        Scanner sc = new Scanner(System.in);
	        
//			System.out.println("유저번호 입력 : ");
			String user_seq= UserDAO.getUserSeq();
			
			System.out.println("사업장번호 입력 : ");
			String business_seq= sc.nextLine();
			System.out.println("예약 시작일 입력 : ");
			String begin_date = sc.nextLine();
			System.out.println("예약 종료일 입력 : ");
			String end_date= sc.nextLine();
	          
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        
	        try {
	            conn = DBUtil.open();
	            String sql = "INSERT INTO Reservation VALUES (Res_seq.nextVal, ?, ?, ?, ?)";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, user_seq);
	            pstmt.setString(2, business_seq);
	            pstmt.setString(3, begin_date);
	            pstmt.setString(4, end_date);
	            rs = pstmt.executeQuery();
	            
	            System.out.println();
	            System.out.println();
	            System.out.println(UI.alignCenter("== 예약이 완료되었습니다. =="));
	            System.out.println();
	            System.out.println(UI.alignCenter("0. 이전 페이지"));
	            System.out.println();
	            System.out.println();
	            
	            System.out.print("🔎 원하시는 서비스를 입력하세요 : ");
	            String sel = scan.nextLine();
	            
	            if(sel.equals("0")) {
	                System.out.println();
	                System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
	                loop = false;
	            } 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return rs;
	}
		
	
	
	
	
	
}
