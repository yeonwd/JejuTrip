package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdbc.DBUtil;
import main.UI;

public class UserDAO {

	public static String getUserSeq() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		String user_seq = "";

		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

			String sql = "SELECT * FROM users WHERE user_id = '" + UI.user_id + "'";
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				user_seq = rs.getString("user_seq");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user_seq;

	} // getUserSeq()
	

	public static List<List<String>> getReview(String user_seq) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

			String sql = "SELECT * FROM review WHERE user_seq = " + user_seq + " ORDER BY review_seq";
			rs = stat.executeQuery(sql);

			List<List<String>> reviewList = new ArrayList<>();

			while (rs.next()) {

				List<String> temp = new ArrayList<>();

				temp.add(rs.getString("review_seq")); // 리뷰번호
				temp.add(rs.getString("business_seq")); // 사업자 번호
				temp.add(rs.getString("tour_area_seq")); // 관광지 번호
				temp.add(rs.getString("review_content")); // 리뷰 내용
				if (rs.getString("review_open").equals("0"))
					temp.add("비공개"); // 공개 여부
				else
					temp.add("공개");

				reviewList.add(temp);
			}

			for (int i = 0; i < reviewList.size(); i++) {

				System.out.println("번호: " + (i + 1));
				if (reviewList.get(i).get(1) != null)
					System.out.println("사업자 번호: " + reviewList.get(i).get(1));
				if (reviewList.get(i).get(2) != null)
					System.out.println("관광지 번호: " + reviewList.get(i).get(2));
				System.out.println("리뷰 내용: " + reviewList.get(i).get(3));
				System.out.println("공개 여부: " + reviewList.get(i).get(4));
				System.out.println();
			}
			return reviewList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	} // getReview
	

	public static int get_current_point(String user_seq) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		String sql = "select user_money from users where user_seq = " + user_seq;
		int current_point = 0;

		try {

			conn = DBUtil.open();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				current_point = rs.getInt("user_money");
			}

			System.out.printf(UI.alignCenter("현재 보유 포인트: %,d점\n"), current_point);
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return current_point;

	} // get_current_point
	

	
	public static List<String> get_kind() {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM question_kind ORDER BY question_kind_seq ASC";
		List<String> list = new ArrayList<String>();

		try {

			conn = DBUtil.open();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getString("Question_name"));
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	} // get_kind()

	
	public static List<List<String>> get_enquiry_record(String user_seq) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

			String sql = "SELECT * FROM question WHERE user_seq = " + user_seq + " ORDER BY question_seq DESC";
			rs = stat.executeQuery(sql);

			List<List<String>> enquiryList = new ArrayList<>();

			while (rs.next()) {

				List<String> temp = new ArrayList<>();

				temp.add(rs.getString("question_seq")); // 문의번호
				temp.add(rs.getString("question_kind_seq")); // 문의 종류 번호
				temp.add(rs.getString("question_content")); // 문의 내용

				enquiryList.add(temp);

			}
			return enquiryList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	} // get_enquiry_record
	

	public static int new_question(String user_seq, String kind, String content) {

		Connection conn = null;
		PreparedStatement pstat = null;

		try {

			conn = DBUtil.open();

			String sql = "INSERT INTO question VALUES (Question_seq.nextVal, ?, ?, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user_seq);
			pstat.setString(2, kind);
			pstat.setString(3, content);

			int result = pstat.executeUpdate();
			return result;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	} // new_question
	

	public static int edit_review(int input, List<List<String>> reviewList, String updated) {

		Connection conn = null;
		Statement stat = null;

		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();

			String sql = String.format("UPDATE review set review_content = '%s' WHERE review_seq = %s", updated,
					reviewList.get(input - 1).get(0));

			int result = stat.executeUpdate(sql);
			return result;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return 0;

	} //edit_review

	public static int delete_review(int input, List<List<String>> reviewList) {
		
		Connection conn = null;
		Statement stat = null;

		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("DELETE FROM review WHERE review_seq = %s",
					reviewList.get(input - 1).get(0));
			int result = stat.executeUpdate(sql);
			
			return result;

			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return 0;

	} //delete_review
	
	
	public static void print_reservation(String user_seq) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "SELECT * FROM reservation where user_seq = " + user_seq;
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				System.out.println();
				System.out.println("예약번호: " + rs.getString("reservation_seq"));
				System.out.println("사업자번호: " + rs.getString("business_seq"));
				System.out.println("예약시작일: " + rs.getString("reservation_begin"));
				System.out.println("예약종료일: " + rs.getString("reservation_end"));
			}



		} catch (Exception e) {

			e.printStackTrace();

		}
		
	} //reserve_record()
	
	
	public static void print_reservation_rentcar(String user_seq) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = "SELECT * FROM rentcar_reservation where user_seq = " + user_seq;
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				System.out.println();
				System.out.println("예약번호: " + rs.getString("rentcar_reservation_seq"));
				System.out.println("사업자번호: " + rs.getString("business_seq"));
				System.out.println("차종: " + rs.getString("rentcar_seq"));
				System.out.println("예약시작일: " + rs.getString("reservation_begin"));
				System.out.println("예약종료일: " + rs.getString("reservation_end"));
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	} //reserve_rentcar_recor
	
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
		         
		         String sql = String.format("select max(rentcar_reservation_seq) from rentcar_reservation");
		         
		         
		         //60명 레코드 
		         rs = stat.executeQuery(sql);
		         
		         while (rs.next()) {
		            result = String.format("%s",rs.getString("max(rentcar_reservation_seq)"));
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
				Payment_insert1(num,max, admin.AdminDAO.realprice);//결제내역테이블 인서트 메소드
				int price2 = Integer.parseInt(admin.AdminDAO.realprice);
				PointSub(price2);
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
	      
	      bs[0] = UserDAO.getUserSeq(); //현재 로그인한 유저의 유저번호  
	      
	      bs[1] = num; // 사업장 번호
	      
	      System.out.print("예약 시작일을 입력하세요 : ");
	      bs[2] = scan.nextLine();
	      
	      System.out.print("예약 종료일을 입력하세요 : ");
	      bs[3] = scan.nextLine();
	      
	      LocalDate startDate = LocalDate.parse(bs[2]);
	      LocalDate endDate = LocalDate.parse(bs[3]);
	      long days = ChronoUnit.DAYS.between(startDate, endDate);
	      int day = (int) Math.abs(days);
	      
	      admin.AdminDAO.realprice = Caculate_price(num,day);
	      
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
		      
		      
		      bs[0] = UserDAO.getUserSeq(); //현재 로그인한 유저번호
		      
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
	   public static void qwer(String seq, String seq2) {
			
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
				
				String sql = "select Rentcar_price from rentcar where Rentcar_seq="+seq2;
				
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
					Rentcar_insert1(seq, seq2);
					String max = rentcar_reservation_max();
					Payment_insert_rentcar(seq,max,price);
					int price2 = Integer.parseInt(price);
					PointSub(price2);
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
	   public static void Rentcar_insert1(String num, String num2) {
		      
		      System.out.println(UI.alignCenter("== 렌트카 예약 등록 =="));
		      
		      String[] bs = new String[5];
		      
		      Scanner scan = new Scanner(System.in);
		      
		      bs[0] = UserDAO.getUserSeq();; //현재 로그인한 유저의 유저번호  
		      
		      bs[1] = num; // 사업장 번호
		      
//		      System.out.print("예약하실 렌트카 번호를 입력하세요 : ");
		      bs[2] = num2;
		      
		      System.out.print("예약 시작일을 입력하세요 : ");
		      bs[3] = scan.nextLine();
		      
		      System.out.print("예약 종료일을 입력하세요 : ");
		      bs[4] = scan.nextLine();
		      
		      //배열문을 받고 JDBC insert 쿼리 작성
		      Rentcar_insert2(bs[0], bs[1], bs[2],bs[3],bs[4]);
		      
		   }
		   
		 //렌트카예약 테이블에 인서트
		   public static void Rentcar_insert2(String s1, String s2, String s3, String s4, String s5) {
		   
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
			      
			      
			      bs[0] = UserDAO.getUserSeq();; //현재 로그인한 유저번호
			      
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
			         
			         
			         
			               
			         rs.close();
			         stat.close();
			         conn.close();
			         System.out.println();
			      } 
			      catch (Exception e) {
			         e.printStackTrace();
			      }
		   }
		   
		 //포인트 빼기 and 마일리지 적립
		   public static void PointSub(int accommodationPrice) {
		       
		      Connection conn = null;
		       PreparedStatement pstmt = null;
		       try {
		           conn = DBUtil.open();
		           // 사용자의 현재 포인트를 조회
		           String selectSql = "SELECT User_money, User_mileage FROM Users WHERE user_id = ?";
		           pstmt = conn.prepareStatement(selectSql);
		           pstmt.setString(1, UI.user_id);
		           ResultSet rs = pstmt.executeQuery();
		           int currentPoint = 0;
		           int currentMileage = 0;
		           if (rs.next()) {
		               currentPoint = rs.getInt("User_money");
		               currentMileage = rs.getInt("User_mileage");
		           }
		           rs.close(); 

		           //숙소 가격의 1%를 마일리지로 적립
		           int mileageEarned = (int) (accommodationPrice * 0.01);
		           
		           //현재 마일리지에 마일리지 적립
		           int updatedMileage = currentMileage + mileageEarned;
		           
		           //숙소 가격을 현재 포인트에서 차감
		           int updatedPoint = currentPoint - accommodationPrice;
		           
		           //유저 테이블 업데이트
		           if (updatedPoint >= 0) {
		              String updateSql = "UPDATE Users SET User_money = ?, User_mileage = ? WHERE user_id = ?";
		              pstmt = conn.prepareStatement(updateSql);
		              pstmt.setInt(1, updatedPoint);
		              pstmt.setInt(2, updatedMileage);
		              pstmt.setString(3, UI.user_id);
		              int rowCount = pstmt.executeUpdate();
		              if (rowCount > 0) {
		                  System.out.println("포인트가 업데이트 되었습니다.");
		              } else {
		                  System.out.println("일치하는 유저가 없습니다.");
		              }
		           } else {
		              // 차감한 포인트가 0원이하면 포인트 부족 출력
		              System.out.println("포인트가 부족합니다.");
		           }

		           // 포인트를 업데이트
		           
		       } catch (SQLException e) {
		           e.printStackTrace();
		       } finally {
		           try {
		               if (pstmt != null) pstmt.close();
		               if (conn != null) conn.close();
		           } catch (SQLException e) {
		               e.printStackTrace();
		           }
		       }
		   }
		   
		   
	   //숙소 조회
	   public static void AccommodationSelect() {
	       Connection conn = null;
	       PreparedStatement pstmt = null;
	       ResultSet rs = null;
	       try {
	           conn = DBUtil.open();
	           String sql = "select * from business b inner join Accommodate a on b.business_seq = a.business_seq";
	           pstmt = conn.prepareStatement(sql);
	           rs = pstmt.executeQuery();
	           while (rs.next()) {
	              String Accommodate_seq = rs.getString("accommodate_seq");
	              String Business_seq = rs.getString("business_seq");
	               String Accommodate_kind = rs.getString("accommodate_kind");
	               String Accommodate_price = rs.getString("accommodate_price");
	               String Buisiness_address = rs.getString("buisiness_address");
	               // 출력 또는 원하는 작업 수행
	               System.out.println("숙소 번호: " + Accommodate_seq);
	               System.out.println("사업장 번호: " + Business_seq);
	               System.out.println("숙소 종류: " + Accommodate_kind);
	               System.out.println("숙소 가격: " + Accommodate_price);
	               System.out.println("숙소 주소: " + Buisiness_address);
	               System.out.println("-----------------------");
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           try {
	               if (rs != null) rs.close();
	               if (pstmt != null) pstmt.close();
	               if (conn != null) conn.close();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
	   }
	   
	 //사용자 정보 조회
	public static void User_list(String id) {
		
		System.out.println(UI.alignCenter("== 계정 관리 =="));
		System.out.println(UI.alignCenter("[나의 계정 정보]"));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select * from Users where User_id = '%s'", id);
			
			String result = "";
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				result += UI.alignCenter(String.format("[유저 번호] : %s \r\n", rs.getString("User_seq")));
				result += String.format("[이름] : %s \r\n", rs.getString("User_name"));
				result += UI.alignCenter(String.format("             [주민등록번호] : %s \r\n",rs.getString("User_ssn")));				
				result += String.format("             [아이디] : %s \r\n",rs.getString("User_id"));		
				result += UI.alignCenter(String.format("          [비밀번호] : %s \r\n",rs.getString("User_pw")));				
				result += String.format("          [닉네임] : %s \r\n",rs.getString("User_nickname"));				
				result += UI.alignCenter(String.format("    [충전금액] : %s \r\n",rs.getString("User_money")));				
				result += String.format("    [마일리지] : %s \r\n",rs.getString("User_mileage"));		
				System.out.println(result);
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
