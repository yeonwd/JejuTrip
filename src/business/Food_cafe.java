package business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import jdbc.DBUtil;
import main.UI;

public class Food_cafe {
	
	public static String search;
	
	public static void food_cafe_main() {
		
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			System.out.println();
			System.out.println();
			System.out.println(UI.alignCenter("== 맛집/카페 조회 =="));
			System.out.println();
			System.out.println(UI.alignCenter("1. 맛집 조회"));
			System.out.println(UI.alignCenter("2. 카페 조회"));
			System.out.println();
			System.out.println(UI.alignCenter("0. 이전 페이지"));
			System.out.println();
			System.out.println();
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 맛집 조회 (가게 정보&리뷰)
				food_list();
				
			} else if(sel.equals("2")) {
				//2. 카페 조회
				cafe_list();
				
			} else if(sel.equals("0")) {
				//0. 홈 화면으로 돌아가기
				
				System.out.println();
				System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
				loop = false;
				
			} else {
	            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
	        }
			
		}//while(menu)
		
	}
	
	
	public static void food_list() {
		
	    boolean loop = true;
	    Scanner scan = new Scanner(System.in);
	    
	    while (loop) {

			System.out.println();
			System.out.println();
			System.out.println(UI.alignCenter("== 맛집 조회 =="));
			System.out.println();
			System.out.println();
	      
		      Connection conn = null;
		      Statement stat = null;
		      ResultSet rs = null;
		      
		      try {
		         
		         conn = DBUtil.open();
		         stat = conn.createStatement();
		         
		         
		         String sql = String.format("select * from Business where Business_kind = '맛집'");
		         
		         
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
	        System.out.println(UI.alignCenter("== 맛집 검색 =="));
	        System.out.println(UI.alignCenter("1. 리뷰 조회"));
	        System.out.println();
	        System.out.println(UI.alignCenter("0. 이전 페이지"));
	        System.out.println();
	        System.out.println();
	        
	        System.out.print("🔎 원하시는 서비스를 입력하세요 : ");
	        String sel = scan.nextLine();
	        
	        if(sel.equals("1")) {
	            // 1. 상호명으로 검색
	            food_search();
	        } else if(sel.equals("0")) {
	            // 0. 이전 페이지로 돌아가기
	            System.out.println();
	            System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
	            loop = false;
	        } else {
	            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
	        }
			
	    }//while(menu)
			
	}
	
	
	public static void food_search() {
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
	            System.out.print("🏝️ 검색할 가게의 상호명을 입력하세요 : ");
	            String sel = scan.nextLine();
	            String search = sel;
	            System.out.println();
	             
	            conn = DBUtil.open();
	            stat = conn.createStatement();
	            
	            String sql = String.format("select * from Business where business_kind = '맛집' and Business_name = '%s'", sel);
	            
	            rs = stat.executeQuery(sql);
	            
	            while (rs.next()) {
	                System.out.printf("[상호명] : %s \r\n", rs.getString("Business_name"));
	                System.out.printf("[주소] : %s \r\n", rs.getString("BUISINESS_Address"));
	                System.out.println();
	            }
	            
	            rs.close();
	            stat.close();
	            conn.close();
	            
	            food_review_name(search);
	            
	        } catch (Exception e) {
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
	        
	        if (sel.equals("0")) {
	            // 0. 이전 페이지로 돌아가기
	            System.out.println();
	            System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
	            loop = false;
	        }
	    }
	}

	public static void food_review_name(String businessName) {
	    boolean loop = true;
	    Scanner scan = new Scanner(System.in);
	    
	    while (loop) {
	        Connection conn = null;
	        Statement stat = null;
	        ResultSet rs = null;

	        try {
	            conn = DBUtil.open();
	            stat = conn.createStatement();

	            String sql = String.format("select r.user_seq, r.review_content, p.review_reply_detail from review r inner join review_reply p on r.review_seq = p.review_seq inner join business b on b.business_seq = r.business_seq where b.business_name = '%s'", businessName);
	            rs = stat.executeQuery(sql);

	            while (rs.next()) {
	                System.out.printf("[리뷰] : %s \r\n", rs.getString("review_content"));
	                System.out.printf("[리뷰 답글] : %s \r\n", rs.getString("review_reply_detail"));
	                System.out.println();
	            }

	            rs.close();
	            stat.close();
	            conn.close();
	        } catch (Exception e) {
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

	        if (sel.equals("0")) {
	            // 0. 이전 페이지로 돌아가기
	            System.out.println();
	            System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
	            loop = false;
	        }
	    }
	}
	
	public static void cafe_list() {
		
	    boolean loop = true;
	    Scanner scan = new Scanner(System.in);
	    
	    while (loop) {

			System.out.println();
			System.out.println();
			System.out.println(UI.alignCenter("== 카페 조회 =="));
			System.out.println();
			System.out.println();
		      
		      Connection conn = null;
		      Statement stat = null;
		      ResultSet rs = null;
		      
		      try {
		         
		         conn = DBUtil.open();
		         stat = conn.createStatement();
		         
		         
		         String sql = String.format("select * from Business where Business_kind = '카페'");
		         
		         
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
			System.out.println(UI.alignCenter("== 카페 검색 =="));
			System.out.println();
			System.out.println(UI.alignCenter("1. 리뷰 조회"));
			System.out.println();
			System.out.println(UI.alignCenter("0. 이전 페이지"));
			System.out.println();
			System.out.println();
			
			System.out.print("🔎 원하시는 서비스를 입력하세요 : ");
	        String sel = scan.nextLine();
	        
	        if(sel.equals("1")) {
	            // 1. 상호명으로 검색
	            cafe_search();
	        } else if(sel.equals("0")) {
	            // 0. 이전 페이지로 돌아가기
	            System.out.println();
	            System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
	            loop = false;
	        } else {
	            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
	        }
			
	    }//while(menu)
			
		
	}
	
	public static void cafe_search() {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {

		System.out.println();
		System.out.println();
		System.out.println(UI.alignCenter("== 카페 상호명 검색 =="));
		System.out.println();
		System.out.println();
	      
	      Connection conn = null;
	      Statement stat = null;
	      ResultSet rs = null;
	      
	      try {
	    	  
	    	  System.out.print("🏝️ 검색할 가게의 상호명을 입력하세요 : ");
	    	  scan = new Scanner(System.in);
	    	  String sel = scan.nextLine();
	    	  System.out.println();
	         
	         conn = DBUtil.open();
	         stat = conn.createStatement();
	         
	         String sql = String.format("select * from Business where business_kind = '카페' and Business_name = '%s'", sel);
	         
	         
	         rs = stat.executeQuery(sql);
	         
	         while (rs.next()) {
	            System.out.printf("[상호명] : %s \r\n", rs.getString("Business_name"));
	            System.out.printf("[주소] : %s \r\n", rs.getString("BUISINESS_Address"));
	            System.out.println();
	         }
	         
	               
	         rs.close();
	         stat.close();
	         conn.close();
	         
	         cafe_review_name(sel);
	         
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
	        
	        if (sel.equals("0")) {
	            // 0. 이전 페이지로 돌아가기
	            System.out.println();
	            System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
	            loop = false;
	        }
	      
	}//while(menu)
			
	}
	
	
	public static void cafe_review_name(String businessName) {
		
		boolean loop = true;
	    Scanner scan = new Scanner(System.in);
	    
	    while (loop) {
	        Connection conn = null;
	        Statement stat = null;
	        ResultSet rs = null;

	        try {
	            conn = DBUtil.open();
	            stat = conn.createStatement();

	            String sql = String.format("select r.user_seq, r.review_content, p.review_reply_detail from review r inner join review_reply p on r.review_seq = p.review_seq inner join business b on b.business_seq = r.business_seq where b.business_name = '%s'", businessName);
	            rs = stat.executeQuery(sql);

	            while (rs.next()) {
	                System.out.printf("[리뷰] : %s \r\n", rs.getString("review_content"));
	                System.out.printf("[리뷰 답글] : %s \r\n", rs.getString("review_reply_detail"));
	                System.out.println();
	            }

	            rs.close();
	            stat.close();
	            conn.close();
	        } catch (Exception e) {
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

	        if (sel.equals("0")) {
	            // 0. 이전 페이지로 돌아가기
	            System.out.println();
	            System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
	            loop = false;
	        }
	    }
	}



}
