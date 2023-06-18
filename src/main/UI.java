package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import board.Bookmark;
import board.Scheduler;
import business.Business_DB;
import business.Business_manage;
import service.MainDAO;

public class UI {
	
	public static String admin_id = "";
	public static String owner_id = "";
	public static String user_id = "";
	public static String name = "";

		
		public static void mainMenu() {
			
//			try {
//				
//				String asciiArt1 = FigletFont.convertOneLine("Hello World");
//				System.out.println(asciiArt1);
//				
//			} catch (Exception e) {
//				
//				e.printStackTrace();
//				
//			}
			main_image();
			System.out.println();
			System.out.println("               ");
			System.out.println(alignCenter("[제주 어때]"));
			System.out.println("               ");
			System.out.println(alignCenter("1. 로그인"));
			System.out.println(alignCenter("2. 회원가입"));
			System.out.println(alignCenter("3. 아이디/비밀번호 찾기"));
			System.out.println(alignCenter("0. 종료하기"));
			System.out.println("         ");
			

	}
//--------------------------------------------------------------------		
		
		//1. 로그인 루프
		public static void login_UI() {
					
			boolean loop = true;
			Scanner scan = new Scanner(System.in);
			
			//로그인이 실패 하면 복귀
			System.out.println();
			System.out.println("               ");
			System.out.println(alignCenter("[제주 어때]"));
			System.out.println("               ");
			System.out.println(alignCenter("1. 관리자 로그인"));
			System.out.println(alignCenter("2. 사업자 로그인"));
			System.out.println(alignCenter("3. 유저 로그인"));
			System.out.println(alignCenter("0. 종료하기"));
			System.out.println("         ");

			
			//로그인이 성공 하면 로그인 루프
			while (loop) {
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
				
				if(sel.equals("1")) {
					// 아이디, 비밀번호 입력
					System.out.print("🏝️ 아이디를 입력하세요 : ");
					String id = scan.nextLine();
					System.out.print("🏝️ 비밀번호를 입력하세요 : ");
					String pw = scan.nextLine();
					
					//1. 관리자 로그인 루프
					if (service.MainDAO.Admin_login(id, pw)) {
				        System.out.println("로그인 성공!");
				        ResultSet rs = service.MainDAO.getUser(id, pw);
				        admin_loop();
				        loop = false;
				    } else {
				        System.out.println("로그인 실패!");
				        login_UI();
				        loop = false;
				    }
					
				} else if(sel.equals("2")) {
					// 아이디, 비밀번호 입력
					System.out.print("🏝️ 아이디를 입력하세요 : ");
					String id = scan.nextLine();
					System.out.print("🏝️ 비밀번호를 입력하세요 : ");
					String pw = scan.nextLine();
					
					//2. 사업자 로그인 루프
					if (service.MainDAO.Owner_login(id, pw)) {
				        System.out.println("로그인 성공!");
				        ResultSet rs = service.MainDAO.getUser(id, pw);
				        owner_loop();
				        loop = false;
				    } else {
				        System.out.println("로그인 실패!");
				        login_UI();
				        loop = false;
				    }
					
				} else if(sel.equals("3")) {
					// 아이디, 비밀번호 입력
					System.out.print("🏝️ 아이디를 입력하세요 : ");
					String id = scan.nextLine();
					System.out.print("🏝️ 비밀번호를 입력하세요 : ");
					String pw = scan.nextLine();
					
					//3. 사용자 로그인 루프
					if (service.MainDAO.Users_login(id, pw)) {
				        System.out.println("로그인 성공!");
				        ResultSet rs = service.MainDAO.getUser(id, pw);
				        user_id = id;
				        user_loop();
				        loop = false;
				    } else {
				        System.out.println("로그인 실패!");
				        login_UI();
				        loop = false;
				    }
					
				} else if(sel.equals("0")) {
					
					//0. 프로그램 종료
					
					System.out.println();
					System.out.println("❗️ 프로그램을 종료합니다 ❗️");
					loop = false;
					
				}
				
			}
			
		}
	
	//2. 회원가입 루프
	public static void regist_UI() {
		//scanner를 이용해서 사용한테 배열로, 정보들을 담고, JDBC를 통해 insert문으로 회원을 가입 시킴.
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		String name = "";
		String id = "";
		String pw = "";
		String ssn = "";
		String nickname = "";
		
		//
		System.out.println();
		System.out.println("               ");
		System.out.println(main.UI.alignCenter("[제주 어때]"));
		System.out.println("               ");
		System.out.println(main.UI.alignCenter("1. 사업자 회원가입"));
		System.out.println(main.UI.alignCenter("2. 유저 회원가입"));
		System.out.println(main.UI.alignCenter("0. 종료하기"));
		System.out.println("         ");
		
		//로그인이 성공 하면 로그인 루프
		while (loop) {
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 사업자 회원가입
				System.out.print("🏝️ 이름을 입력하세요 : ");
				name = scan.nextLine();
				System.out.print("🏝️ 아이디를 입력하세요 : ");
				id = scan.nextLine();
				System.out.print("🏝️ 비밀번호를 입력하세요: ");
				pw = scan.nextLine();	
				
				MainDAO.sign_up_Owner(name, id, pw);
				
				System.out.println("🏝️ 회원가입이 완료되었습니다. : ");
				
				loop = false;
				
			} else if(sel.equals("2")) {
				
				//2. 유저 회원가입
				System.out.print("🏝️ 이름을 입력하세요 : ");
				name = scan.nextLine();
				System.out.print("🏝️ 주민번호를 입력하세요 : ");
				ssn = scan.nextLine();
				System.out.print("🏝️ 아이디를 입력하세요 : ");
				id = scan.nextLine();
				System.out.print("🏝️ 비밀번호를 입력하세요 : ");
				pw = scan.nextLine();
				System.out.print("🏝️ 닉네임을 등록해주세요 : ");
				nickname = scan.nextLine();
				
				MainDAO.sign_up_Users(name, ssn, id, pw, nickname);
				
				System.out.println("🏝️ 회원가입이 완료되었습니다. : ");
				
				loop = false;
				
				
			} else if(sel.equals("0")) {
				
				//0. 프로그램 종료
				
				System.out.println();
				System.out.println("❗️ 프로그램을 종료합니다 ❗️");
				loop = false;
				
			}
			
		}
	}
	
	//3. 아이디/비밀번호 찾기 루프
	//3. 아이디/비밀번호 찾기 루프
	public static void find_idpw_UI() {
		
		//아이디/비밀번호 찾기
		System.out.println();
		System.out.println("               ");
		System.out.println(main.UI.alignCenter("[제주 어때]"));
		System.out.println("               ");
		System.out.println(main.UI.alignCenter("1. 사업자 아이디/비밀번호 찾기"));
		System.out.println(main.UI.alignCenter("2. 유저 아이디/비밀번호 찾기"));
		System.out.println(main.UI.alignCenter("0. 종료하기"));
		System.out.println("         ");
		//scanner를 이용해서 사용자한테 아이디 or 비밀번호를 무엇을 찾을 건지 묻고, 해당 정보에 대한 아이디 및 비밀번호를 찾기
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		
		while (loop) {
			
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 사업자 아이디/비밀번호 찾기
				service.Loop.business_find_UI();
				loop = false;
				
			} else if(sel.equals("2")) {
				
				//2. 유저 아이디/비밀번호 찾기
				service.Loop.user_find_UI();
				loop = false;
				
			} else if(sel.equals("0")) {
				
				//0. 프로그램 종료
				
				System.out.println();
				System.out.println("❗️ 프로그램을 종료합니다 ❗️");
				loop = false;
				
			}
			
		}
	}
	
//--------------------------------------------------------------------
	
	//1. 관리자 로그인 루프
	public static void admin_loop() {
		
		Admin_UI.Admin_name();
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			//1. UI 메뉴창 따로 출력해야함.
			System.out.println(alignCenter("1. 사업장 관리"));
			System.out.println(alignCenter("2. 여행정보 관리"));
			System.out.println(alignCenter("3. 예약 관리"));
			System.out.println(alignCenter("4. 포인트 관리"));
			System.out.println(alignCenter("5. 리뷰 관리"));
			System.out.println(alignCenter("6. 공지사항 관리"));
			System.out.println(alignCenter("7. 문의 관리"));
			System.out.println(alignCenter("8. 계정 관리"));
			System.out.println(alignCenter("0. 로그아웃"));
			
			//2. 서비스를 입력 받기
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			//3. 입력 받은 서비스에 대한 메소드 호출문
			if(sel.equals("1")) {
				
				//1. 사업장 관리
				admin.Admin.manage_business();
						
			} else if(sel.equals("2")) {
				
				//2. 여행정보 관리
				admin.Admin.manage_trip();
							
			} else if(sel.equals("3")) {
				
				//3. 예약 관리
				admin.Admin.manage_reservation();
							
			} else if(sel.equals("4")) {
				
				//4. 포인트 관리
				admin.Admin.manage_point();
								
			} else if(sel.equals("5")) {
				
				//5. 리뷰 관리
				admin.Admin.manage_review();
							
			} else if(sel.equals("6")) {
				
				//6. 공지사항 관리
				admin.Admin.manage_notice();
							
			} else if(sel.equals("7")) {
				
				//7. 문의 관리
				admin.Admin.manage_qna();
			} else if(sel.equals("8")) {
				
				//8. 계정 관리
				admin.Admin.manage_account();
								
			} else if(sel.equals("0")) {
				
				//0. 로그아웃
				
				System.out.println();
				System.out.println("❗️ 로그아웃 ❗️");
				loop = false;
				
			} 
			
		}//관리자 while 루프문
	}
	
	//2. 사업자 로그인 루프
	public static void owner_loop() {
		
//		System.out.println("사업자 로그인 루프");
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			//1. UI 메뉴창 따로 출력해야함.
			Owner_UI.Owner_name();
			Owner_UI.Owner_menu();
			
			//2. 서비스를 입력 받기
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			//3. 입력 받은 서비스에 대한 메소드 호출문
			if(sel.equals("1")) {
				
				//1. 사업장 관리
				Business_manage.manage_loop();
						
			} else if(sel.equals("2")) {
				
				//2. 리뷰 관리
				Business_DB.review_list(UI.owner_id);
				Business_manage.delay_loop();
							
			} else if(sel.equals("3")) {
				
				//3. 예약 관리
				Business_DB.reservation_list(UI.owner_id);
				Business_manage.delay_loop();
							
			} else if(sel.equals("4")) {
				
				//4. 계정 관리
				Business_DB.business_account(UI.owner_id);
				Business_manage.delay_loop();
								
			} else if(sel.equals("5")) {
				
				//5. 공지사항 조회
				Business_manage.notice_loop();
							
			} else if(sel.equals("0")) {
				
				//0. 로그아웃
				
				System.out.println();
				System.out.println("❗️ 로그아웃 ❗️");
				loop = false;
				
			} 
			
		}//사업자 while 루프문
	}
	
	//3. 사용자 로그인 루프
	public static void user_loop() {
		System.out.println("사용자 로그인 루프");
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			//1. UI 메뉴창 따로 출력해야함.
			service.Loop.user_main();
			//2. 서비스를 입력 받기
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			//3. 입력 받은 서비스에 대한 메소드 호출문
			if(sel.equals("1")) {
				
				//1. 맛집/카페 조회 및 예약
				business.Food_cafe.food_cafe_main();
				
			} else if(sel.equals("2")) {
				
				//2. 관광지/액티비티 조회 및 예약
				business.Tourarea_activity.tourarea_activity_main();
							
			} else if(sel.equals("3")) {
				
				//3. 숙소 조회 및 예약
				user.User.Accommodation();
				
			} else if(sel.equals("4")) {
				
				//4. 렌터카/주유소/전기차 충전소 조회 및 예약
				user.User.Car();
				
								
			} else if(sel.equals("5")) {
				
				//5. 투어 조회
				trip.tour.Tour_loop();
							
			} else if(sel.equals("6")) {
				
				//6. 내 즐겨찾기 목록 관리
				Bookmark.Bookmark_loop();
							
			} else if(sel.equals("7")) {
				
				//7. 여행 스케줄러 조회
				Scheduler.Scheduler_loop();
								
			} else if(sel.equals("8")) {
				
				//8. 내 예약 관리
				user.User.reserve_user_main();
				
			} else if(sel.equals("9")) {
				
				//9. 리뷰 관리
				user.User.review_user_main();
				
			} else if(sel.equals("10")) {
				
				//10. 내 포인트 관리
				user.User.point_main();
				
			} else if(sel.equals("11")) {
				
				//11. 공지사항 조회
				Business_manage.notice_loop();		
				
			} else if(sel.equals("12")) {
				
				//12. 관리자에게 문의 하기
				user.User.question_user_main();
				
			} else if(sel.equals("13")) {
				
				//13. 계정 관리
				user.User.Account();
				
			} else if(sel.equals("0")) {
				
				//0. 로그아웃
				
				System.out.println();
				System.out.println("❗️ 로그아웃 ❗️");
				loop = false;
				
			} 
			
		}//관리자 while 루프문
	}

//-------------------------------------------------------------------------------\
	public static void main_image() {
		System.out.println("    ___  _______         ___  ___  ___          ___  ___  _________  _________  ________  _______      \r\n"
				+ "   |\\  \\|\\  ___ \\       |\\  \\|\\  \\|\\  \\        |\\  \\|\\  \\|\\___   ___\\\\___   ___\\\\   __  \\|\\  ___ \\     \r\n"
				+ "   \\ \\  \\ \\   __/|      \\ \\  \\ \\  \\\\\\  \\       \\ \\  \\\\\\  \\|___ \\  \\_\\|___ \\  \\_\\ \\  \\|\\  \\ \\   __/|    \r\n"
				+ " __ \\ \\  \\ \\  \\_|/__  __ \\ \\  \\ \\  \\\\\\  \\       \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\ \\ \\   __  \\ \\  \\_|/__  \r\n"
				+ "|\\  \\\\_\\  \\ \\  \\_|\\ \\|\\  \\\\_\\  \\ \\  \\\\\\  \\       \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_|\\ \\ \r\n"
				+ "\\ \\________\\ \\_______\\ \\________\\ \\_______\\       \\ \\_______\\   \\ \\__\\     \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\\r\n"
				+ " \\|________|\\|_______|\\|________|\\|_______|        \\|_______|    \\|__|      \\|__|  \\|__|\\|__|\\|_______|\r\n"
				+ "");
	}
	
	public final static String LINE=" \\|________|\\|_______|\\|________|\\|_______|        \\|_______|    \\|__|      \\|__|  \\|__|\\|__|\\|_______|";
	
	public static String alignCenter(String str) {
		// 다른 기능 센터 정렬
		
		int calSpace=0;
		for(int i=0; i<str.length(); i++) {
			calSpace++;
			if(str.charAt(i) == '\n')  
				break;
		}
		
		String space = " ".repeat(LINE.length()/2-calSpace/2);
		
		String result = space;
		for(int i=0; i<str.length(); i++) {
			result += str.charAt(i)+"";
			if(str.charAt(i) == '\n')  
				result +=space;
		}
		return result;
	}
	
	public static void delay_loop() {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
	
		while (loop) {
		
		System.out.print("️️️0. 이전 화면으로 돌아가기 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("0")) {
			
			//0. 프로그램 종료
			
			System.out.println();
			System.out.println("❗️ 이전 화면으로 돌아갑니다. ❗️");
			loop = false;
			
		} 
		}
		
	}
	
	  public static void centerAlignText(String text) {
	      String space = " ".repeat(LINE.length()/2-text.length()/2);
	      System.out.println(space + text);
	  }
}
