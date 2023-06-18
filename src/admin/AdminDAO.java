package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import jdbc.DBUtil;
import main.UI;

public class AdminDAO {
	
	public static String realprice;

	//---- 계정 관리
	//	Search_users_list(1); //유저 계정 관리 > 매개변수에 유저번호 입력 
	//	Search_owner_list(1); //사업자 계정 검색 > 매개변수에 사업자번호 입력
	//	Users_list(); //유저 계정 전체 조회
	//	Owner_list(); //사업자 계정 전체 조회
	//	
	//	//---- 문의 관리
	//	Search_question_reply_list(1); // 문의처리 검색 > 매개변수에 문의 처리 번호 입력 25
	//	Search_question_list(1); //  문의사항 검색 > 매개변수에 유저번호 입력 24
	//	Search_question_kind_list(1); // 문의사항 종류별 조회 > 매개변수에 종류 번호 입력 23
	//	Question_list(); // 전체 문의사항 조회 22
	//	
	//	//---- 공지사항 관리
	//	Insert_notice(); // 새 공지사항 등록 21
	//	Notice_list(); // 전체 공지사항 조회 20
	//	
	//	//---- 리뷰 관리
	//	Search_review_list(1); // 리뷰 검색 > 매개변수에 유저 번호 입력 19
	//	Review_list(); // 전체 리뷰목록 조회 18
	//	
	//	//---- 포인트 관리
	//	Search_point_list(1); // 포인트 충전 정보 검색 > 매개 변수에 유저 번호 입력 17
	//	Point_list(); // 전체 포인트 충전 내역 16
	//	
	//	
	//	//---- 예약 관리
	//	Reservation_list(1); // 예약 검색 > 매개변수에 유저번호 입력 15
	//	Rentcar_reservation_list(); // 렌트카 예약 관리 14
	//	Activity_reservation_list(); // 액티비티 예약 관리 13
	//	Accommodate_reservation_list(); // 숙소 예약 관리 12
	//	Reservation_list(); // 전체 예약 조회 11
	//	
	////	----여행 정보 관리
	//	m10(); // 여행 정보 검색 10
	//	New_info(); // 새정보 등록 9 
	//	Tour_list(); // 투어 목록 8
	//	Electronic_charge_list(); // 전기차 충전소 목록 7
	//	Oil_bank_list(); // 주유소 목록 6	
	//	Tour_area_list(); // 관광지 목록 5
	//
	//	
	//	//---사업장 관리
	//	Search_advertisement(1); // 광고 찾기 매개변수에 광고 번호 입력 4
	//	Search_business(7); // 사업장 찾기 매개변수에 사업장 번호 입력 3
	//	Advertisement_list(); //전체 광고 목록 2
	//	Business_list(); //전체 사업장 목록 1
	//	
	//
	//	// ------------ update 문
	//	Update_business(); // 사업장 수정 100
	//	Update_advertisement(); // 광고 수정 101
	//	
	//	Update_tour_area(); // 관광지 수정 102
	//	Update_oil_bank(); // 주유소 수정 103
	//	Update_electronic_charge(); // 전기차 충전소 수정 104
	//	Update_tour(); // 투어 수정 105
	//	
	//	Update_reservation(); // 전체 예약 수정 106
	//	Update_rentcar_reservation(); // 렌트카 예약 수정 107
	//	
	//	Update_user_point(); // 유저 포인트 수정   user_seq, user_name, user_money, user_mileage 108
	//	
	//	Update_review(); // 리뷰목록 수정 109
	//	
	//	Update_notice(); // 공지사항 수정 110
	//	
	//	Update_question(); // 문의사항 수정 111
	//	Update_question_reply(); // 문의답변 수정 112
	//	
	//	Update_Owner(); // 사업자 계정 수정 113
	//	Update_Users(); // 유저 계정 수정 114
		
		
		//삭제 삭제 삭제 삭제 삭제
	//	admin_business_delete();
	//	admin_advertisement_delete();
	//	admin_Tour_area_delete();
	//	admin_Oil_bank_delete();
	//	admin_Electronic_delete();
	//	admin_Tour_delete();
	//	admin_reservation_delete();
	//	admin_Rentcar_Reservation_delete();
	//	admin_Charge_record_delete();
	//	admin_Review_delete();
	//	admin_Notice_delete();
	//	admin_Question_delete();
	//	admin_Users_delete();
	//	admin_Owner_delete();
	
		
		
		
		
		//asdf("131"); //숙소예약 매개변수에 사업장번호 입력
		//qwer("189"); // 린트카 예약 매개변수에 사업장번호 입력
	
	
	
	//유저기능----------------------------------------------------------------------------
	
	// 예약하면 포인트 업테이트
	//포인트 빼기 and 마일리지 적립
	
	public static String Caculate_price(String business_seq, int day) {
	      
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
	         
	         int realprice = Integer.parseInt(price) * day;
	         price = Integer.toString(realprice);
	         
	         
	                  
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
	   
	   
	   
	   //렌트카 예약 테이블 마지막 받아오기
	   public static String rentcar_reservation_max() {
		      
		      Connection conn = null;
		      Statement stat = null;
		      ResultSet rs = null;
		      
		      String result = "";
		      
		      try {
		         
		         conn = DBUtil.open();
		         stat = conn.createStatement();
		         
		         String sql = String.format("select max(reservation_seq) from rentcar_reservation");
		         
		         
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
	   
	
	//예약 테이블 마지막 받아오기
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
	
	
	
	//숙소 예약
	public static void asdf(String num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		Scanner scan = new Scanner(System.in);
		String price = "";
		int day = 0;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("");
			System.out.println("========================================================================");
			
			String sql = "select Accommodate_price from accommodate where Business_seq="+num;
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				
				
				System.out.printf("선택하신 숙소의 가격은 %s 원 입니다 ^^", rs.getString("accommodate_price"));
				price = rs.getString("accommodate_price");
			}
	
			rs.close();
			stat.close();
			conn.close();
			//System.out.printf("선택하신 숙소의 가격은 %s원 입니다",price);
			System.out.print("결제하시겠습니까? y/n : ");
			String sel = scan.nextLine();
			
			
			
			if (sel.equals("y")) {
				// 예약,결제내역 테이블 인서트 메소드 && 유저테이블 충전금액 업데이트 메소드호출
				Reservation_insert1(num); //예약테이블 인서트 메소드
				String max = Reservation_max();
				Payment_insert1(num,max, realprice);//결제내역테이블 인서트 메소드
				int price2 = Integer.parseInt(realprice);
				//PointSub(price2);
			}
			else if (sel.equals("n")) {
				//이전 화면
			}
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//예약 테이블에 인서트
	   public static void Reservation_insert1(String num) {
	      
	      System.out.println(UI.alignCenter("== 새 스케줄러 등록 =="));
	      
	      String[] bs = new String[4];
	      
	      Scanner scan = new Scanner(System.in);
	      
	      bs[0] = "31"; //현재 로그인한 유저의 유저번호  
	      
	      bs[1] = num; // 사업장 번호
	      
	      System.out.print("예약 시작일을 입력하세요 : ");
	      bs[2] = scan.nextLine();
	      
	      System.out.print("예약 종료일을 입력하세요 : ");
	      bs[3] = scan.nextLine();
	      
	      LocalDate startDate = LocalDate.parse(bs[2]);
	      LocalDate endDate = LocalDate.parse(bs[3]);
	      long days = ChronoUnit.DAYS.between(startDate, endDate);
	      int day = (int) Math.abs(days);
	      
	      realprice = Caculate_price(num,day);
	      
	      //return Math.abs(days); // 절대값으로 반환
	      
	      //배열문을 받고 JDBC insert 쿼리 작성
	      Reservation_insert2(bs[0], bs[1], bs[2],bs[3]);
	      
	   }
	   
	 //예약 테이블에 인서트
	   public static void Reservation_insert2(String s1, String s2, String s3, String s4) {
	   
	      System.out.println(UI.alignCenter("== 예약 등록 =="));
	      System.out.println();
	      
	      Connection conn = null;
	      Statement stat = null;
	      ResultSet rs = null;
	      
	      try {
	         
	         conn = DBUtil.open();
	         stat = conn.createStatement();
	         
	         String sql = String.format("insert into Reservation values (reservation_seq.nextVal, %s, %s, '%s', '%s')", s1, s2, s3,s4);
	         
	         
	         //60명 레코드 
	         rs = stat.executeQuery(sql);
	         
	         System.out.println("삽입이 완료 되었습니다.");
	         
	         
	               
	         rs.close();
	         stat.close();
	         conn.close();
	         System.out.println();
	      } 
	      catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	   
	   // 결제내역 테이블에 인서트
	   public static void Payment_insert1(String num, String max, String price) {
	
		      String[] bs = new String[7];
		      
		      Scanner scan = new Scanner(System.in);
		      
		      
		      bs[0] = "31"; //현재 로그인한 유저번호
		      
		      bs[1] = max; //예약번호를 어떻게 받아오지;;;
		      
		      bs[2] = "null"; // 렌트카 예약번호 null
		      
		      bs[3] = num; //선택한 숙소의 사업장 번호
		      
		      bs[4] = price; //결제금액
		      
		      bs[5] = "sysdate"; //결제날짜
	  
		      int mileage = (int) (Integer.parseInt(price) * 0.01);		      
		      String mg = Integer.toString(mileage);
		      
		      bs[6] = mg; //적립 마일리지
		      
		      //배열문을 받고 JDBC insert 쿼리 작성
		      Payment_insert2(bs[0], bs[1], bs[2],bs[3],bs[4],bs[5],bs[6]);
		      
		   }
	   
	// 결제내역 테이블에 인서트
	   public static void Payment_insert2(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
		   
		      System.out.println(UI.alignCenter("== 결제내역 등록 =="));
		      System.out.println();
		      
		      Connection conn = null;
		      Statement stat = null;
		      ResultSet rs = null;
		      
		      try {
		         
		         conn = DBUtil.open();
		         stat = conn.createStatement();
		         
		         String sql = String.format("insert into Payment values (Payment_seq.nextVal, %s, %s, %s, %s, %s, %s, %s)", s1, s2, s3,s4,s5,s6,s7);
		         
		         
		         //60명 레코드 
		         rs = stat.executeQuery(sql);
		         
		         System.out.println("삽입이 완료 되었습니다.");
		         
		         
		               
		         rs.close();
		         stat.close();
		         conn.close();
		         System.out.println();
		      } 
		      catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
	   
	   
	   
	   //렌트카
	   public static void qwer(String num) {
			
			Connection conn = null;
			Statement stat = null;
			ResultSet rs = null;
			
			Scanner scan = new Scanner(System.in);
			String price = "";
			
			try {
				
				conn = DBUtil.open();
				stat = conn.createStatement();
				
				System.out.println("========================================================================");
				System.out.println("");
				System.out.println("========================================================================");
				
				String sql = "select Rentcar_price from rentcar where Business_seq="+num;
				
				//60명 레코드 
				rs = stat.executeQuery(sql);
				
				while (rs.next()) {
					
					
					System.out.printf("선택하신 렌트카 가격은 %s 원 입니다 ^^", rs.getString("rentcar_price"));
					price = rs.getString("rentcar_price");
				}
	
				rs.close();
				stat.close();
				conn.close();
				//System.out.printf("선택하신 숙소의 가격은 %s원 입니다",price);
				System.out.print("결제하시겠습니까? y/n : ");
				String sel = scan.nextLine();
				
				if (sel.equals("y")) {
					// 렌트카예약,결제내역 테이블 인서트 메소드
					Rentcar_insert1(num);
					String max = rentcar_reservation_max();
					Payment_insert_rentcar(num,max,price);
					
				}
				else if (sel.equals("n")) {
					//이전 화면
				}
				
				
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	   
	   
	   //렌트카 예약 테이블에 인서트
	   public static void Rentcar_insert1(String num) {
		      
		      System.out.println(UI.alignCenter("== 렌트카 예약 등록 =="));
		      
		      String[] bs = new String[4];
		      
		      Scanner scan = new Scanner(System.in);
		      
		      bs[0] = "31"; //현재 로그인한 유저의 유저번호  
		      
		      bs[1] = num; // 사업장 번호
		      
		      System.out.print("예약하실 렌트카 번호를 입력하세요 : ");
		      bs[2] = scan.nextLine();
		      
		      System.out.print("예약 시작일을 입력하세요 : ");
		      bs[3] = scan.nextLine();
		      
		      System.out.print("예약 종료일을 입력하세요 : ");
		      bs[4] = scan.nextLine();
		      
		      //배열문을 받고 JDBC insert 쿼리 작성
		      Rentcar_insert2(bs[0], bs[1], bs[2],bs[3],bs[4]);
		      
		   }
		   
		 //렌트카예약 테이블에 인서트
		   public static void Rentcar_insert2(String s1, String s2, String s3, String s4, String s5) {
		   
		      System.out.println(UI.alignCenter("== 렌트카 예약 등록 =="));
		      System.out.println();
		      
		      Connection conn = null;
		      Statement stat = null;
		      ResultSet rs = null;
		      
		      try {
		         
		         conn = DBUtil.open();
		         stat = conn.createStatement();
		         
		         String sql = String.format("insert into Rentcar_reservation values (Rentcar_reservation_seq.nextVal, %s, %s, %s, '%s', '%s')", s1, s2, s3,s4,s5);
		         
		         
		         //60명 레코드 
		         rs = stat.executeQuery(sql);
		         
		         System.out.println("삽입이 완료 되었습니다.");
		         
		         
		               
		         rs.close();
		         stat.close();
		         conn.close();
		         System.out.println();
		      } 
		      catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
		   
		// 결제내역 테이블에 렌트카 인서트
		   public static void Payment_insert_rentcar(String num, String max, String price) {
	
			      String[] bs = new String[7];
			      
			      Scanner scan = new Scanner(System.in);
			      
			      
			      bs[0] = "31"; //현재 로그인한 유저번호
			      
			      bs[1] = "null"; //예약번호 null
			      
			      bs[2] = max; // 렌트카 예약번호 
			      
			      bs[3] = num; //선택한 렌트카의 사업장 번호
			      
			      bs[4] = price; //결제금액
			      
			      bs[5] = "sysdate"; //결제날짜
	      
			      int mileage = (int) (Integer.parseInt(price) * 0.01);		      
			      String mg = Integer.toString(mileage);
			      
			      bs[6] = mg; //적립 마일리지
			      
			      //배열문을 받고 JDBC insert 쿼리 작성
			      Payment_insert_rentcar2(bs[0], bs[1], bs[2],bs[3],bs[4],bs[5],bs[6]);
			      
			   }
		   
		// 결제내역 테이블에 인서트
		   public static void Payment_insert_rentcar2(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
			   
			      System.out.println(UI.alignCenter("== 렌트카 결제내역 등록 =="));
			      System.out.println();
			      
			      Connection conn = null;
			      Statement stat = null;
			      ResultSet rs = null;
			      
			      try {
			         
			         conn = DBUtil.open();
			         stat = conn.createStatement();
			         
			         String sql = String.format("insert into Payment values (Payment_seq.nextVal, %s, %s, %s, %s, %s, %s, %s)", s1, s2, s3,s4,s5,s6,s7);
			         
			         
			         //60명 레코드 
			         rs = stat.executeQuery(sql);
			         
			         System.out.println("삽입이 완료 되었습니다.");
			         
			         
			               
			         rs.close();
			         stat.close();
			         conn.close();
			         System.out.println();
			      } 
			      catch (Exception e) {
			         e.printStackTrace();
			      }
			   }
	
	//----------------------------------------------------------------------------	
	
	public static void Update_Users() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 유저 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 유저 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 유저 이름 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 유저 주민등록번호 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 유저 아이디 입력 : ");
		String txt4 = scan.nextLine();
		System.out.print("🏝 유저 비밀번호 입력 : ");
		String txt5 = scan.nextLine();
		System.out.print("🏝 유저 닉네임 입력 : ");
		String txt6 = scan.nextLine();
		System.out.print("🏝 유저 충전금액 입력 : ");
		String txt7 = scan.nextLine();
		System.out.print("🏝 유저 마일리지 입력 : ");
		String txt8 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Users set User_seq = %s, User_name = '%s', User_ssn='%s', User_id='%s', User_pw='%s', User_nickname='%s', User_money=%s, User_mileage=%s where User_seq=%s", txt1, txt2, txt3, txt4,txt5, txt6, txt7, txt8, seq);
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_Owner() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 사업자 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 사업자 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 사업자 이름 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 사업자 아이디 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 사업자 비밀번호 입력 : ");
		String txt4 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Owner set Owner_seq = %s, Owner_name = '%s', Owner_id='%s', Owner_pw='%s' where Owner_seq=%s", txt1, txt2, txt3, txt4, seq);
			

			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_question_reply() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 문의처리 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 문의처리 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 문의 번호 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 관리자 번호 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 처리내용 입력 : ");
		String txt4 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Question_reply set Question_reply_seq = %s, Question_seq = %s, Admin_seq=%s, Question_reply_content='%s' where Question_reply_seq=%s", txt1, txt2, txt3, txt4, seq);
			

			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_question() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 문의사항 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 문의 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 유저 번호 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 문의종류 번호 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 신고 내용 입력 : ");
		String txt4 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Question set Question_seq = %s, User_seq = %s, Question_kind_seq=%s, Question_content='%s' where Question_seq=%s", txt1, txt2, txt3, txt4, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_notice() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 공지사항 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 공지사항 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 관리자 번호 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 공지사항 제목 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 공지사항 내용 입력 : ");
		String txt4 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Notice set Notice_seq = %s, Admin_seq = %s, Notice_title='%s', Notice_content='%s' where Notice_seq=%s", txt1, txt2, txt3, txt4, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_review() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 리뷰 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 리뷰 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 유저 번호 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 사업장 번호 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 관광지 번호 입력 : ");
		String txt4 = scan.nextLine();
		System.out.print("🏝 리뷰내용 입력 : ");
		String txt5 = scan.nextLine();
		System.out.print("🏝 공개여부 입력 : ");
		String txt6 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Review set Review_seq = %s, User_seq = %s, Business_seq=%s, Tour_area_seq=%s, Review_content='%s', Review_open=%s where Review_seq=%s", txt1, txt2, txt3, txt4, txt5, txt6, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_user_point() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		// user_seq, user_name, user_money, user_mileage
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 유저 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 유저 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 유저 이름 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 유저 충전금액 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 유저 마일리지 입력 : ");
		String txt4 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Users set User_seq = %s, User_name = '%s', User_money=%s, User_mileage=%s where User_seq = %s", txt1, txt2, txt3, txt4, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_rentcar_reservation() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 렌트카 예약번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 렌트카 예약 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 유저 번호 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 사업장 번호 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 렌트카 번호 입력 : ");
		String txt4 = scan.nextLine();
		System.out.print("🏝 예약 시작일 입력 : ");
		String txt5 = scan.nextLine();
		System.out.print("🏝 예약 종료일 입력 : ");
		String txt6 = scan.nextLine();
		
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Rentcar_reservation set Rentcar_reservation_seq = %s, User_seq = %s, Business_seq=%s, Rentcar_seq=%s, Reservation_begin='%s', Reservation_end='%s' where Rentcar_reservation_seq = %s", txt1, txt2, txt3, txt4, txt5, txt6, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_reservation() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 예약번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 예약 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 유저 번호 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 사업장 번호 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 예약 시작일 입력 : ");
		String txt4 = scan.nextLine();
		System.out.print("🏝 예약 종료일 입력 : ");
		String txt5 = scan.nextLine();
		
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Reservation set Reservation_seq = %s, User_seq = %s, Business_seq=%s, Reservation_begin='%s', Reservation_end='%s' where Reservation_seq = %s", txt1, txt2, txt3, txt4, txt5, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_tour() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 투어 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 투어 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 투어명 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 시작날짜 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 종료날짜 입력 : ");
		String txt4 = scan.nextLine();
		System.out.print("🏝 투어일정 입력 : ");
		String txt5 = scan.nextLine();
		
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Tour set Tour_seq = %s, Tour_name = '%s', Tour_startdate='%s', Tour_enddate='%s', Tour_content='%s' where Tour_seq = %s", txt1, txt2, txt3, txt4, txt5, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_electronic_charge() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 전기차충전소 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 충전소 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 충전소 이름 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 충전소 주소 입력 : ");
		String txt3 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Electronic_charge set Electronic_charge_seq = %s, Electronic_charge_name = '%s', Electronic_charge_address='%s' where Electronic_charge_seq = %s", txt1, txt2, txt3, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_oil_bank() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 주유소 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 주유소 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 주유소 이름 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 주유소 주소 입력 : ");
		String txt3 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Oil_bank set Oil_bank_seq = %s, Oil_bank_name = '%s', Oil_bank_address='%s' where Oil_bank_seq = %s", txt1, txt2, txt3, seq);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_tour_area() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 관광지 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 관광지 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 관광지 명 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 관광지 주소 입력 : ");
		String txt3 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Tour_area set Tour_area_seq = %s, Tour_area_name = '%s', Tour_area_address='%s' where Tour_area_seq = %s", txt1, txt2, txt3, seq);
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Update_advertisement() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 광고 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 광고 번호 입력 : ");
		String Ad_seq = scan.nextLine();
		System.out.print("🏝 사업장 번호 입력 : ");
		String Business_seq = scan.nextLine();
		System.out.print("🏝 사업자 번호 입력 : ");
		String Owner_seq = scan.nextLine();
		System.out.print("🏝 광고 내용 입력 : ");
		String Ad_content = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Advertisement set Ad_seq = %s, Business_seq = %s, Owner_seq=%s, Ad_content='%s' where Ad_seq = %s", Ad_seq, Business_seq, Owner_seq, Ad_content, seq);
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Update_business() {
		
		System.out.println(UI.alignCenter("== 수정 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 수정을 원하는 사업장 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.print("🏝 사업장 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 사업자 번호 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 사업장 종류 입력 : ");
		String txt3 = scan.nextLine();
		System.out.print("🏝 상호명 입력 : ");
		String txt4 = scan.nextLine();
		System.out.print("🏝 사업장 주소 입력 : ");
		String txt5 = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("update Business set Business_seq = %s, Owner_seq = %s, Business_kind='%s', Business_name='%s', Buisiness_address='%s' where Business_seq = %s", txt1,txt2,txt3,txt4,txt5, seq);
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("수정이 완료되었습니다.");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void Search_users_list(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[유저 번호]\t[이름]\t\t[주민등록번호]\t\t[아이디]\t\t[비밀번호]\t\t\t[닉네임]\t[충전금액]\t[마일리지]");
			System.out.println("========================================================================");
			
			String sql = "select * from Users where user_seq="+num;
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t%s\t\t%s\t%s\t%s\n"
						, rs.getString("User_seq")
						, rs.getString("User_name")
						, rs.getString("User_ssn")
						, rs.getString("User_id")
						, rs.getString("User_pw")
						, rs.getString("User_nickname")
						, rs.getString("User_money")
						, rs.getString("User_mileage"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Search_owner_list(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[사업자 번호]\t[이름]\t\t[아이디]\t\t[비밀번호]");
			System.out.println("========================================================================");
			
			String sql = "select * from Owner where Owner_seq="+num;
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Owner_seq")
						, rs.getString("Owner_name")
						, rs.getString("Owner_id")
						, rs.getString("Owner_pw"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Users_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[유저 번호]\t[이름]\t\t[주민등록번호]\t\t[아이디]\t\t[비밀번호]\t\t\t[닉네임]\t[충전금액]\t[마일리지]");
			System.out.println("========================================================================");
			
			String sql = "select * from Users";
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t%s\t\t%s\t%s\t%s\n"
						, rs.getString("User_seq")
						, rs.getString("User_name")
						, rs.getString("User_ssn")
						, rs.getString("User_id")
						, rs.getString("User_pw")
						, rs.getString("User_nickname")
						, rs.getString("User_money")
						, rs.getString("User_mileage"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Owner_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[사업자 번호]\t[이름]\t  [아이디]\t\t[비밀번호]");
			System.out.println("========================================================================");
			
			String sql = "select * from Owner";
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Owner_seq")
						, rs.getString("Owner_name")
						, rs.getString("Owner_id")
						, rs.getString("Owner_pw"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Search_question_reply_list(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[문의 번호]\t[문의 내용]\t\t[문의종류 번호]\t   [유저 번호][문의처리 번호]\t[문의처리 내용]\t\t[관리자 번호] ");
			System.out.println("========================================================================");
			
			String sql = "select q.Question_seq, Question_content, Question_kind_seq, User_seq, Question_reply_seq, Question_reply_content, Admin_seq from Question_reply qr inner join question q on qr.question_seq = q.question_seq where q.question_seq="+num;
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t%s\t%s\t%s\n"
						, rs.getString("Question_seq")
						, rs.getString("Question_content")
						, rs.getString("Question_kind_seq")
						, rs.getString("User_seq")
						, rs.getString("Question_reply_seq")
						, rs.getString("Question_reply_content")
						, rs.getString("Admin_seq"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Search_question_list(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[문의 번호]\t[유저 번호]\t[문의종류 번호]\t\t[신고 내용]");
			System.out.println("========================================================================");
			
			String sql = "select * from question where user_seq="+num;
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Question_seq")
						, rs.getString("User_seq")
						, rs.getString("Question_kind_seq")
						, rs.getString("Question_content"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Search_question_kind_list(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[문의 번호]\t[유저 번호]\t[문의종류 번호]\t\t[신고 내용]");
			System.out.println("========================================================================");
			
			String sql = "select * from question where Question_kind_seq="+num;
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Question_seq")
						, rs.getString("User_seq")
						, rs.getString("Question_kind_seq")
						, rs.getString("Question_content"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Question_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[문의 번호]\t[유저 번호]\t[문의종류 번호]\t\t[신고 내용]");
			System.out.println("========================================================================");
			
			String sql = "select * from question";
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Question_seq")
						, rs.getString("User_seq")
						, rs.getString("Question_kind_seq")
						, rs.getString("Question_content"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Insert_notice() {
		
		System.out.println(UI.alignCenter("== 공지사항 등록 =="));
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("🏝 관리자 번호 입력 : ");
		String txt1 = scan.nextLine();
		System.out.print("🏝 공지사항 제목 입력 : ");
		String txt2 = scan.nextLine();
		System.out.print("🏝 공지사항 내용 입력 : ");
		String txt3 = scan.nextLine();
		
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement pstat = null;
		
		try {
						
			conn = DBUtil.open();
			
			//비교 포인트!!
			//Statement
			String sql = String.format("insert into Notice values(Notice_seq.nextVal, %s,'%s','%s')",txt1,txt2,txt3);
			
			
			
			stat = conn.createStatement();
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("등록이 완료되었습니다");
			
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public static void Notice_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[공지사항 번호]\t[관리자 번호]\t\t[공지사항 제목]\t\t[공지사항 내용]");
			System.out.println("========================================================================");
			
			String sql = "select * from notice";
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Notice_seq")
						, rs.getString("Admin_seq")
						, rs.getString("Notice_title")
						, rs.getString("Notice_content"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Search_review_list(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[리뷰번호]\t\t[유저]\t\t[사업장 번호]\t[관광지 번호]\t\t[리뷰내용]\t\t\t\t[공개여부]");
			System.out.println("========================================================================");
			
			String sql = "select * from review where user_seq="+num;
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("review_seq")
						, rs.getString("user_seq")
						, rs.getString("business_seq")
						, rs.getString("Tour_area_seq")
						, rs.getString("Review_content")
						, rs.getString("Review_open"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Review_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[리뷰번호]\t\t[유저]\t\t[사업장 번호]\t[관광지 번호]\t\t[리뷰내용]\t\t\t\t[공개여부]");
			System.out.println("========================================================================");
			
			String sql = "select * from review";
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("review_seq")
						, rs.getString("user_seq")
						, rs.getString("business_seq")
						, rs.getString("Tour_area_seq")
						, rs.getString("Review_content")
						, rs.getString("Review_open"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Search_point_list(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[유저번호]\t\t[이름]\t\t[충전금액]\t\t[마일리지]");
			System.out.println("========================================================================");
			
			String sql = "select user_seq, user_name, user_money, user_mileage from users where user_seq="+num+"order by user_seq";
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("user_seq")
						, rs.getString("user_name")
						, rs.getString("user_money")
						, rs.getString("user_mileage"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Point_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[유저번호]\t\t[이름]\t\t[충전금액]\t\t[마일리지]");
			System.out.println("========================================================================");
			
			String sql = "select user_seq, user_name, user_money, user_mileage from users order by user_seq";
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("user_seq")
						, rs.getString("user_name")
						, rs.getString("user_money")
						, rs.getString("user_mileage"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Reservation_list(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[예약 번호]\t[유저번호]\t\t[사업장번호]\t\t[예약시작일]\t\t[예약종료일]");
			System.out.println("========================================================================");
			
			String sql = "select * from reservation where user_seq="+num;
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Reservation_seq")
						, rs.getString("User_seq")
						, rs.getString("Business_seq")
						, rs.getString("Reservation_begin")
						, rs.getString("Reservation_end"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Rentcar_reservation_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[렌트카 예약번호]\t[유저번호]\t  [사업장 번호]\t[렌트카 번호]\t\t[예약 시작일]\t\t [예약종료일]");
			System.out.println("========================================================================");
			
			String sql = "select * from Rentcar_reservation";
			
	
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t%s\t\t%s\n"
						, rs.getString("Rentcar_reservation_seq")
						, rs.getString("user_seq")
						, rs.getString("business_seq")
						, rs.getString("Rentcar_seq")
						, rs.getString("Reservation_begin")
						, rs.getString("Reservation_end"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Activity_reservation_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[예약 번호]\t[유저번호]\t\t[액티비티명]\t\t\t[액티비티 주소]\t\t\t\t\t [액티비티 가격]");
			System.out.println("========================================================================");
			
			String sql = "select r.Reservation_seq, r.user_seq, b.business_name, b.buisiness_address, a.activity_price from Reservation r inner join Activity a on a.business_seq = r.business_seq inner join Business b on b.Business_seq = a.business_seq order by r.reservation_seq";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t%s\n"
						, rs.getString("Reservation_seq")
						, rs.getString("user_seq")
						, rs.getString("business_name")
						, rs.getString("buisiness_address")
						, rs.getString("activity_price"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void Accommodate_reservation_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[예약 번호]\t[유저번호]\t\t[숙소명]\t\t\t[숙소주소]\t\t\t\t\t  [숙소종류] [숙소가격]");
			System.out.println("========================================================================");
			
			String sql = "select r.Reservation_seq, r.user_seq, b.business_name, b.buisiness_address, a.accommodate_kind, a.accommodate_price from Reservation r inner join Accommodate a on a.business_seq = r.business_seq inner join Business b on b.Business_seq = a.business_seq order by r.reservation_seq";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t%s\t%s\n"
						, rs.getString("Reservation_seq")
						, rs.getString("user_seq")
						, rs.getString("business_name")
						, rs.getString("buisiness_address")
						, rs.getString("accommodate_kind")
						, rs.getString("accommodate_price"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Reservation_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[예약 번호]\t[유저번호]\t\t[사업장번호]\t\t[예약시작일]\t\t[예약종료일]");
			System.out.println("========================================================================");
			
			String sql = "select * from Reservation";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Reservation_seq")
						, rs.getString("User_seq")
						, rs.getString("Business_seq")
						, rs.getString("Reservation_begin")
						, rs.getString("Reservation_end"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m10() {
		// TODO Auto-generated method stub
		
	}
	
	public static void New_info() {
		
		
		
	}
	
	public static void Tour_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[투어 번호]\t   [투어명]\t\t\t\t[시작날짜]\t\t\t\t[종료날짜]\t\t\t[투어일정]");
			System.out.println("========================================================================");
			
			String sql = "select * from Tour";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\n"
						, rs.getString("Tour_seq")
						, rs.getString("Tour_name")
						, rs.getString("Tour_startdate")
						, rs.getString("Tour_enddate")
						, rs.getString("Tour_content"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Electronic_charge_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[관광지 번호]\t    [관광지명]\t[관광지 주소]");
			System.out.println("========================================================================");
			
			String sql = "select * from Electronic_charge";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t%s\t\n"
						, rs.getString("Electronic_charge_seq")
						, rs.getString("Electronic_charge_name")
						, rs.getString("Electronic_charge_address"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Oil_bank_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[주유소 번호]\t    [주유소 이름]\t[주유소 주소]");
			System.out.println("========================================================================");
			
			String sql = "select * from Oil_bank";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t%s\t\n"
						, rs.getString("Oil_bank_seq")
						, rs.getString("Oil_bank_name")
						, rs.getString("Oil_bank_address"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Tour_area_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[관광지 번호]\t    [관광지명]\t[관광지 주소]");
			System.out.println("========================================================================");
			
			String sql = "select * from Tour_area";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t%s\t\n"
						, rs.getString("Tour_area_seq")
						, rs.getString("Tour_area_name")
						, rs.getString("Tour_area_address"));
						
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Search_advertisement(int num) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("========================================================================");
			System.out.println("[광고번호]\t    [사업장 번호]\t[사업자 번호]\t[광고내용]");
			System.out.println("========================================================================");
			
			String sql = "select * from advertisement where Ad_seq="+num;
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t%5s\t\t%s\n"
						, rs.getString("Ad_seq")
						, rs.getString("Business_seq")
						, rs.getString("Owner_seq")
						, rs.getString("Ad_content"));
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Search_business(int num) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("=========================================================");
			System.out.println("[사업장 번호][사업자 번호][사업장 종류][상호명][사업장 주소]");
			System.out.println("=========================================================");
			
			String sql = "select * from business where business_seq ="+num ;
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				System.out.printf("%s\t%s\t%s\t%s%s\n"
						, rs.getString("Business_seq")
						, rs.getString("Owner_seq")
						, rs.getString("Business_kind")
						, rs.getString("Business_name")
						, rs.getString("Buisiness_address"));
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Advertisement_list() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			System.out.println("=========================================================");
			System.out.println("[광고번호]\t    [사업장 번호]\t[사업자 번호]\t[광고내용]");
			System.out.println("=========================================================");
			
			String sql = "select * from advertisement";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			
			
			while (rs.next()) {
				
				
				
				System.out.printf("%s\t\t%s\t%5s\t\t%s\n"
						, rs.getString("Ad_seq")
						, rs.getString("Business_seq")
						, rs.getString("Owner_seq")
						, rs.getString("Ad_content"));
			}
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void Business_list() {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "select * from Business";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			System.out.println("=========================================================");
			System.out.println("[사업장 번호]    [사업자 번호]\t[사업장 종류]\t[상호명] [사업장 주소]");
			System.out.println("=========================================================");
			
			
			while (rs.next()) {
				
				System.out.printf("%s\t%s\t%s\t%s%s\n"
						, rs.getString("Business_seq")
						, rs.getString("Owner_seq")
						, rs.getString("Business_kind")
						, rs.getString("Business_name")
						, rs.getString("Buisiness_address"));
			}
			
	
	
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	//--------------------------삭제 삭제 삭제 삭제 삭제 삭제 삭제 -----------------------------
	
	//1. 사업장 컬럼 삭제
	   public static void admin_business_delete() {
		   
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 사업장 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_advertisement_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 광고 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_Tour_area_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 관광지 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_Oil_bank_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 주유소 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_Electronic_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 충전소 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_Tour_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 투어 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_reservation_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 예약 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_Rentcar_Reservation_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 렌트카 번호 입력 : ");
	      String num = scan.nextLine();
	      
	      Connection conn = null;
	      Statement stat = null;
	      ResultSet rs = null;
	      
	      try {
	         
	         conn = DBUtil.open();
	         stat = conn.createStatement();
	         
	         String sql = String.format("delete from Rentcar_Reservation where rentcar_reservation_seq = %s", num);
	         
	         
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
	   public static void admin_Charge_record_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 충전내역 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_Review_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 리뷰 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   
	   
	   
	   //11. 공지사항 컬럼 삭제
	   public static void admin_Notice_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 공지사항 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_Question_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 문의 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   public static void admin_Users_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 유저 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	   
	   
	   //13. 사업자 탈퇴
	   public static void admin_Owner_delete() {
	      
	      System.out.println(UI.alignCenter("== 삭제 =="));
	      
	      Scanner scan = new Scanner(System.in);
	      System.out.print("🏝️ 삭제를 원하는 사업자 번호 입력 : ");
	      String num = scan.nextLine();
	      
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
	      
	   }//admin_Owner_del
	   
}
	
