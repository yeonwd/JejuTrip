package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdbc.DBUtil;
import main.UI;

public class User {

	public static void review_user_main() {

		boolean loop = true;
		Scanner scan = new Scanner(System.in);

		String user_seq = UserDAO.getUserSeq();

		while (loop) {

			try {

				System.out.println();
				System.out.println(UI.alignCenter("== 리뷰 관리 =="));
				System.out.println();

				// 작성 리뷰 목록 출력
				List<List<String>> reviewList = getReview(user_seq);

				System.out.println("리뷰 번호 입력 시 리뷰를 수정 또는 삭제할 수 있습니다.");
				System.out.println();
				System.out.println("0. 홈화면으로 돌아가기");
				System.out.println();

				System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");

				int sel = scan.nextInt();

				if (sel == 0) {
					// 0. 홈 화면으로 돌아가기
					System.out.println();
					System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
					loop = false;
				}

				else {
					review_update(reviewList, sel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void review_update(List<List<String>> reviewList, int input) {

		Connection conn = null;
		Statement stat = null;

		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		try {
			conn = DBUtil.open();
			stat = conn.createStatement();

			// 리뷰 번호 입력
			while (loop) {

				// 입력값 유효성 검사
				if (input > reviewList.size()) {
					System.out.println("유효하지 않은 번호입니다.");
					loop = false;
				}

				System.out.println();
				System.out.println("1. 수정");
				System.out.println("2. 삭제");
				System.out.println("-1. 뒤로가기");
				System.out.println();
				System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");

				switch (scan.nextLine()) {

				case "-1":
					// 뒤로 가기
					loop = false;
					break;

				case "1":
					// 리뷰 수정
					System.out.println();
					System.out.println(reviewList.get(input - 1).get(3));
					System.out.println("수정할 내용을 입력하세요.");
					String updated = scan.nextLine();
					String sql = String.format("UPDATE review set review_content = '%s' WHERE review_seq = %s", updated,
							reviewList.get(input - 1).get(0));

					int result = stat.executeUpdate(sql);
					
					if (result == 1) {
						System.out.println("수정 완료되었습니다.");
						System.out.println("메인으로 돌아갑니다.");
						System.out.println();
						System.out.println();
						scan.nextLine();
						loop = false;
					} else {
						System.out.println("수정 실패했습니다.");
						System.out.println("메인으로 돌아갑니다.");
						System.out.println();
						System.out.println();
						scan.nextLine();
						loop = false;
					}
					break;

				case "2":
					// 리뷰 삭제
					System.out.println("정말 삭제하시겠습니까?");
					System.out.println("1. 확인");
					System.out.println("2. 취소");
					System.out.println("🏝️ 원하시는 서비스를 입력하세요 : ");

					switch (scan.nextLine()) {

					case "1":
						sql = String.format("DELETE FROM review WHERE review_seq = %s",
								reviewList.get(input - 1).get(0));
						result = stat.executeUpdate(sql);

						if (result == 1) {
							System.out.println("삭제 완료되었습니다.");
							System.out.println("메인으로 돌아갑니다.");
							System.out.println();
							System.out.println();
							scan.nextLine();
							loop = false;
						} else {
							System.out.println("삭제 실패했습니다.");
							System.out.println("메인으로 돌아갑니다.");
							System.out.println();
							System.out.println();
							scan.nextLine();
							loop = false;
						}
						break;
					case "2":
						loop = false;
						break;
					}
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static List<List<String>> getReview(String user_seq) {

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
	}
	
	public static void point_main() {

		boolean loop = true;
		Scanner scan = new Scanner(System.in);

		String user_seq = UserDAO.getUserSeq();
		int current_point = 0;

		while (loop) {

			System.out.println();
			System.out.println(UI.alignCenter("== 포인트 관리 =="));
			System.out.println();
			current_point = get_current_point(user_seq);
			point_menu();

			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();

			if (sel.equals("1"))
				charge_point(user_seq, current_point); // 포인트 충전

			else if (sel.equals("2"))
				refund_point(user_seq, current_point); // 포인트 환불

			else if (sel.equals("3")) {
				point_record(user_seq); // 포인트 내역 조회
				UI.delay_loop();
				
			} else if (sel.equals("0")) {
				System.out.println();
				System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
				loop = false;

			} else
				System.out.println("유효하지 않은 숫자입니다.");

		}
	}

	private static void point_record(String user_seq) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		String sql = "select * from charge_record where user_seq = " + user_seq;

		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			System.out.println("구분            금액                  일시");
			String temp = "%s          %,d원         %s\n";

			while (rs.next()) {
				String type = "";
				int price = rs.getInt("charge_amount");
				if (price > 0)
					type = "충전";
				else
					type = "환불";
				String date = rs.getString("charge_date");
				System.out.printf(temp, type, price, date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int get_current_point(String user_seq) {

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

	}

	private static void charge_point(String user_seq, int current_point) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		Scanner scan = new Scanner(System.in);

		boolean loop = true;

		while (loop) {

			try {

				conn = DBUtil.open();
				stat = conn.createStatement();
				System.out.println();

				System.out.println("충전하실 금액을 입력하세요 : ");
				String input = scan.nextLine();

				System.out.printf("%,d원을 충전하시겠습니까?\n", Integer.parseInt(input));
				System.out.println("1. 확인");
				System.out.println("2. 취소");

				switch (scan.nextLine()) {
				case "1":
					// 충전 내역 insert
					String sql = String.format(
							"INSERT INTO charge_record values (Charge_record_seq.nextVal, %s, %s, SYSDATE)", user_seq,
							input);
					stat.executeQuery(sql);

					// user_money update
					int updated_point = current_point + Integer.parseInt(input);
					sql = String.format("UPDATE users SET user_money = %d WHERE user_seq = %s", updated_point,
							user_seq);
					stat.executeQuery(sql);

					System.out.println("충전이 완료되었습니다.");
					loop = false;
					break;

				case "2":
					boolean b = Util.reconfirm();
					if(b=true) loop=false;
					break;
				}

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

	}

	private static void refund_point(String user_seq, int current_point) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		Scanner scan = new Scanner(System.in);

		boolean loop = true;

		while (loop) {

			try {

				conn = DBUtil.open();
				stat = conn.createStatement();
				System.out.println();

				System.out.println("환불하실 금액을 입력하세요 : ");
				String input = scan.nextLine();

				System.out.printf("%,d원을 환불하시겠습니까?\n", Integer.parseInt(input));
				System.out.println("1. 확인");
				System.out.println("2. 취소");

				switch (scan.nextLine()) {
				case "1":

					//유효성 검증
					int temp = Integer.parseInt(input);
					if (temp > current_point) {
						System.out.println("환불 금액이 올바르지 않습니다.");
						loop = false;
					}

					//환불 내역 insert
					String sql = String.format(
							"INSERT INTO charge_record values (Charge_record_seq.nextVal, %s, -%s, SYSDATE)", user_seq,
							input);
					stat.executeQuery(sql);

					//user_money update

					int updated_point = current_point - temp;
					sql = String.format("UPDATE users SET user_money = %d WHERE user_seq = %s", updated_point,
							user_seq);
					stat.executeQuery(sql);

					System.out.println("환불이 완료되었습니다.");
					loop = false;
					break;

				case "2":
					loop = false;
					break;
				}

			} catch (Exception e) {

				e.printStackTrace();

			}

		}
	}

	private static void point_menu() {
		System.out.println();
		System.out.println(UI.alignCenter("1. 포인트 충전"));
		System.out.println(UI.alignCenter("2. 포인트 환불"));
		System.out.println(UI.alignCenter("3. 포인트 내역 조회"));
		System.out.println();
		System.out.println(UI.alignCenter("0. 이전 페이지"));
		System.out.println();
		System.out.println();
	}
	
	public static void reserve_user_main() {

		boolean loop = true;
		Scanner scan = new Scanner(System.in);

		String user_seq = UserDAO.getUserSeq();

		while (loop) {

			ru_menu();

			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();

			if (sel.equals("1"))
				reservation(user_seq); // 일반 예약 조회

			else if (sel.equals("2"))
				rentcar(user_seq); // 렌트카 예약 조회

			else if (sel.equals("0")) {
				System.out.println();
				System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
				loop = false;

			} else
				System.out.println("유효하지 않은 숫자입니다.");

		} // while(menu)

	}

	private static void ru_menu() {
		System.out.println();
		System.out.println();
		System.out.println(UI.alignCenter("== 내 예약 조회 =="));
		System.out.println();
		System.out.println(UI.alignCenter("1. 일반 예약 조회 "));
		System.out.println(UI.alignCenter("2. 렌트카 예약 조회"));
		System.out.println();
		System.out.println(UI.alignCenter("0. 이전 페이지"));
		System.out.println();
		System.out.println();
	}

	public static void reservation(String user_seq) {

		boolean loop = true;
		Scanner scan = new Scanner(System.in);

		while (loop) {

			UserDAO.print_reservation(user_seq);

			System.out.println();
			System.out.println("0. 홈화면으로 돌아가기");
			System.out.println();

			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");

			if (scan.nextLine().equals("0"))
				loop = false;
		}
	}

	public static void rentcar(String user_seq) {

		boolean loop = true;
		Scanner scan = new Scanner(System.in);

		while (loop) {

			UserDAO.print_reservation_rentcar(user_seq);

			System.out.println();
			System.out.println("0. 홈화면으로 돌아가기");
			System.out.println();

			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");

			if (scan.nextLine().equals("0"))
				loop = false;

		}

	}
	
	public static void question_user_main() {

		Scanner scan = new Scanner(System.in);
		String user_seq = UserDAO.getUserSeq();

		boolean loop = true;

		while (loop) {

			System.out.println();
			System.out.println(UI.alignCenter("== 관리자에게 문의하기 =="));
			System.out.println();

			question_menu();
			System.out.println();
			System.out.println("0. 홈화면으로 돌아가기");
			System.out.println();

			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();

			if (sel.equals("1"))
				enquiry(user_seq); // 문의하기

			else if (sel.equals("2")) {
				enquiry_record(user_seq); // 문의 내역 보기
				UI.delay_loop();

			} else if (sel.equals("0")) {
				System.out.println();
				System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
				loop = false;

			} else
				System.out.println("유효하지 않은 숫자입니다.");
		}
	} // question_user_main()

	private static void question_menu() {
		System.out.println();
		System.out.println(UI.alignCenter("1. 문의하기"));
		System.out.println(UI.alignCenter("2. 문의 내역 보기"));
		System.out.println();
		System.out.println(UI.alignCenter("0. 이전 페이지"));
		System.out.println();
		System.out.println();
	} // question_menu()

	// 문의 종류 불러오기
	private static List<String> get_kind() {

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

	private static void enquiry(String user_seq) {

		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;

		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {

			List<String> list = new ArrayList<String>();
			list = get_kind();

			if (list == null) {
				System.out.println("오류가 발생했습니다. 다시 시도해 주세요.");
				System.out.println("❗️ 이전 페이지로 돌아갑니다 ❗️");
				loop = false;
			}

			for (int i = 0; i < list.size(); i++)
				System.out.printf("%d. %s\n", i + 1, list.get(i));

			System.out.println();
			System.out.println("0. 홈화면으로 돌아가기");
			System.out.println();
			System.out.println("번호를 입력하세요.");

			String kind = scan.nextLine();

			if (kind.equals("0")) {
				boolean b = Util.reconfirm();
				if (b = true)
					loop = false;
			}

			System.out.println("문의 내용을 입력하세요.");
			String content = scan.nextLine();

			System.out.println();
			System.out.println("문의를 등록하시겠습니까?");
			System.out.println("1. 확인 2. 취소");

			String submit = scan.nextLine();

			if (submit.equals("1")) {

				try {

					conn = DBUtil.open();

					String sql = "INSERT INTO question VALUES (Question_seq.nextVal, ?, ?, ?)";
					pstat = conn.prepareStatement(sql);
					pstat.setString(1, user_seq);
					pstat.setString(2, kind);
					pstat.setString(3, content);
					
					int result = pstat.executeUpdate();
					
					if(result==1) {
						System.out.println("문의 등록이 완료되었습니다.");
						System.out.println("메인으로 돌아갑니다.");
						System.out.println();
						System.out.println();
						scan.nextLine();
						loop = false;
					} else {
						System.out.println("문의 등록이 실패했습니다.");
						System.out.println("메인으로 돌아갑니다.");
						System.out.println();
						System.out.println();
						scan.nextLine();
						loop = false;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				boolean b = Util.reconfirm();
				if (b = true)
					loop = false;
			}
		}
	} // enquiry

	private static void enquiry_record(String user_seq) {

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

			List<String> list = get_kind();

			for (int i = 0; i < enquiryList.size(); i++) {

				System.out.println();
				System.out.println("번호: " + (i + 1));
				int t = Integer.parseInt(enquiryList.get(i).get(1));
				System.out.println("문의 종류: " + list.get(t - 1));
				System.out.println("문의 내용: " + enquiryList.get(i).get(2));
				System.out.println();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	} // enquiry_r

	
	//계정
	public static void Account() {
		
		user.UserDAO.User_list(UI.user_id);
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			System.out.println();
			System.out.println("               ");
			System.out.println(UI.alignCenter("[제주 어때]"));
			System.out.println("               ");
			System.out.println(UI.alignCenter("1. 닉네임 변경"));
			System.out.println(UI.alignCenter("2. 비밀번호 변경"));
			System.out.println(UI.alignCenter("0. 종료하기"));
			System.out.println("         ");
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				System.out.print("변경할 닉네임을 입력하세요: ");
				String nickname = scan.nextLine();
				
				//scan으로 변경할 nickname을 입력
				changeNickname(UI.user_id, nickname);
				loop = false;
						
			} else if(sel.equals("2")) {
				
				System.out.print("변경할 비밀번호를 입력하세요: ");
				String pw = scan.nextLine();
				
				//scan으로 변경할 pw을 입력
				changePw(UI.user_id, pw);
				loop = false;
							
			} else if(sel.equals("0")) {
				
				//0. 로그아웃
				
				System.out.println();
				System.out.println("❗️ 로그아웃 ❗️");
				loop = false;
				
			} 
		}
		
	}


	//닉네임 업데이트
	public static void changeNickname(String user_id, String user_nickname) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "UPDATE Users SET user_nickname = ? WHERE user_id = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, user_nickname);
	        pstmt.setString(2, user_id);
	        int rowCount = pstmt.executeUpdate();
	        if (rowCount > 0) {
	            System.out.println("닉네임이 변경 되었습니다.");
	        } else {
	            System.out.println("일치하는 레코드가 없습니다.");
	        }
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

	//비밀번호 업데이트
	public static void changePw(String user_id, String user_pw) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "UPDATE Users SET user_pw = ? WHERE user_id = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, user_pw);
	        pstmt.setString(2, user_id);
	        int rowCount = pstmt.executeUpdate();
	        if (rowCount > 0) {
	            System.out.println("비밀번호가 변경 되었습니다.");
	        } else {
	            System.out.println("일치하는 레코드가 없습니다.");
	        }
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
	
	//숙소 조회 및 예약
	   public static void Accommodation() {
	      
	      System.out.println("숙소 조회 및 예약");
	      
	      System.out.println(UI.user_id);
	      
	      boolean loop = true;
	      Scanner scan = new Scanner(System.in);
	      
	      while (loop) {
	         
	         System.out.println();
	         System.out.println("               ");
	         System.out.println(UI.alignCenter("[제주 어때]"));
	         System.out.println("               ");
	         System.out.println(UI.alignCenter("1. 숙소 조회"));
	         System.out.println(UI.alignCenter("2. 숙소 예약"));
	         System.out.println(UI.alignCenter("0. 종료하기"));
	         System.out.println("         ");
	         
	         System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
	         String sel = scan.nextLine();
	         
	         if(sel.equals("1")) {
	            
	            System.out.println("숙소를 조회합니다.");
	            
	            //숙소 select문 출력
	            UserDAO.AccommodationSelect();
	            UI.delay_loop();
	            
	                  
	         } else if(sel.equals("2")) {
	            
	            System.out.println("예약할 숙소의 번호를 입력하세요 : ");
	            String AccommodationSelect_seq = scan.nextLine();

	            //사용
	            UserDAO.asdf(AccommodationSelect_seq);
	      
	            
	            
	                     
	         } else if(sel.equals("0")) {
	            
	            //0. 로그아웃
	            
	            System.out.println();
	            System.out.println("❗️ 메인으로 돌아갑니다 ❗️");
	            loop = false;
	            
	         } 
	      }
	      
	   }
	   
	   
	 //렌터카/주유소/전기차 충전소 조회 및 예약
	   public static void Car() {
	      
	      boolean loop = true;
	      Scanner scan = new Scanner(System.in);
	      
	      while (loop) {
	         
	         System.out.println();
	         System.out.println("               ");
	         System.out.println(UI.alignCenter("[렌터카/주유소/전기차 충전소 조회 및 예약]"));
	         System.out.println("               ");
	         System.out.println(UI.alignCenter("1. 렌터카 조회 및 예약"));
	         System.out.println(UI.alignCenter("2. 주유소 조회"));
	         System.out.println(UI.alignCenter("3. 전기차 충전소 조회"));
	         System.out.println(UI.alignCenter("0. 종료하기"));
	         System.out.println("         ");
	         
	         System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
	         String sel = scan.nextLine();
	         
	         if(sel.equals("1")) {
	            
	            RentCar();
	                  
	         } else if(sel.equals("2")) {
	            
	            System.out.println(UI.alignCenter("= 주유소 목록 ="));
	            
	            reservation.OilBank.OilBank_select();
	            UI.delay_loop();
	            
	         } else if(sel.equals("3")) {
	            
	            System.out.println(UI.alignCenter("= 전기차 충전소 목록 ="));
	            
	            reservation.Electronic.Electronic_select();
	            UI.delay_loop();
	            
	         } else if(sel.equals("0")) {
	            
	            //0. 이전으로
	            
	            System.out.println();
	            System.out.println("❗️ 이전으로 ❗️");
	            loop = false;
	            
	         } 
	      }
	      
	   }
	   
	   //렌터카 조회 및 예약
	   public static void RentCar() {
	      
	      boolean loop = true;
	      Scanner scan = new Scanner(System.in);
	      
	      while (loop) {
	         
	         System.out.println();
	         System.out.println("               ");
	         System.out.println(UI.alignCenter("[제주 어때]"));
	         System.out.println("               ");
	         System.out.println(UI.alignCenter("1. 렌트카 조회"));
	         System.out.println(UI.alignCenter("2. 렌트카 예약"));
	         System.out.println(UI.alignCenter("0. 종료하기"));
	         System.out.println("         ");
	         
	         System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
	         String sel = scan.nextLine();
	         
	         if(sel.equals("1")) {
	            
	            System.out.println("= 렌트카 조회 =");
	            
	            reservation.RentCar.RentCar_select();
	                  
	         } else if(sel.equals("2")) {
	        	
	        	System.out.print("예약하실 렌트카 사업장 번호를 입력하세요 : ");
	        	
	        	String seq = scan.nextLine();
	        	
	        	System.out.print("예약하실 렌트카 번호를 입력하세요 : ");
	        	
	        	String seq2 = scan.nextLine();
	        	 
	            user.UserDAO.qwer(seq, seq2);
	            
	            System.out.println("예약이 완료되었습니다.");
	            
	                     
	         } else if(sel.equals("0")) {
	            
	            //0. 이전으로
	            
	            System.out.println();
	            System.out.println("❗️ 이전으로 ❗️");
	            loop = false;
	            
	         } 
	      }
	      
	   }
		
		
	
}
