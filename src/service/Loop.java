package service;

import java.util.Scanner;

import main.UI;

public class Loop {

	public static void business_find_UI() {
		//사업자 아이디/비밀번호 찾기
		System.out.println();
		System.out.println("               ");
		System.out.println(main.UI.alignCenter("[제주 어때]"));
		System.out.println("               ");
		System.out.println(main.UI.alignCenter("1. 아이디 찾기"));
		System.out.println(main.UI.alignCenter("2. 비밀번호 찾기"));
		System.out.println(main.UI.alignCenter("0. 종료하기"));
		System.out.println("         ");
		//
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		String name = "";
		String id = "";
		
		while (loop) {
			
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 사업자 아이디/비밀번호 찾기
				System.out.print("🏝️ 이름을 입력하세요 : ");
				name = scan.nextLine();
				
				MainDAO.getownerId(name);
				
				System.out.println("회원님의 아이디는 : " + MainDAO.getownerId(name) + "입니다.");
				
				
				loop = false;
	
				
			} else if(sel.equals("2")) {
				
				//2. 사업자 비밀번호 찾기
				System.out.print("🏝️ 이름을 입력하세요 : ");
				name = scan.nextLine();
				System.out.print("🏝️ 아이디를 입력하세요 : ");
				id = scan.nextLine();
				
				MainDAO.getownerPw(name, id);
				
				System.out.println("회원님의 비밀번호는 : " + MainDAO.getownerPw(name, id) + "입니다.");
				
				
				loop = false;
				
				
				
				
			} else if(sel.equals("0")) {
				
				//0. 프로그램 종료
				
				System.out.println();
				System.out.println("❗️ 프로그램을 종료합니다 ❗️");
				loop = false;
				
			}
			
		}
	}

	public static void user_find_UI() {
		//유저 아이디/비밀번호 찾기
		System.out.println();
		System.out.println("               ");
		System.out.println(main.UI.alignCenter("[제주 어때]"));
		System.out.println("               ");
		System.out.println(main.UI.alignCenter("1. 아이디 찾기"));
		System.out.println(main.UI.alignCenter("2. 비밀번호 찾기"));
		System.out.println(main.UI.alignCenter("0. 종료하기"));
		System.out.println("         ");
		//
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		String name = "";
		String id = "";
		String ssn = "";
		
		while (loop) {
			
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 유저 아이디/비밀번호 찾기
				System.out.print("🏝️ 이름을 입력하세요 : ");
				name = scan.nextLine();
				System.out.print("🏝️ 주민번호을 입력하세요 : ");
				ssn = scan.nextLine();
				
				
				MainDAO.getuserId(name, ssn);
				
				System.out.println("회원님의 아이디는 : " + MainDAO.getuserId(name, ssn) + "입니다.");
				
				
				loop = false;
	
				
			} else if(sel.equals("2")) {
				
				//2. 유저 아이디/비밀번호 찾기
				System.out.print("🏝️ 이름을 입력하세요 : ");
				name = scan.nextLine();
				System.out.print("🏝️ 주민번호을 입력하세요 : ");
				ssn = scan.nextLine();
				System.out.print("🏝️ 아이디을 입력하세요 : ");
				id = scan.nextLine();
				
				MainDAO.getuserPw(name, ssn, id);
				System.out.println("회원님의 비밀번호는 : " + MainDAO.getuserPw(name, ssn, id) + "입니다.");
				
				
				//비밀번호줌
				
				loop = false;
				
				
				
				
			} else if(sel.equals("0")) {
				
				//0. 프로그램 종료
				
				System.out.println();
				System.out.println("❗️ 프로그램을 종료합니다 ❗️");
				loop = false;
				
			}
			
		}
	}
	
	public static void user_main() {
		
		System.out.println();
		System.out.println("               ");
		System.out.println(UI.alignCenter("안녕하세요, 김다미 님!"));
		System.out.println(UI.alignCenter("오늘도 제주어때와 함께 즐거운 여행을 계획하세요."));
		System.out.println("               ");
		System.out.println(UI.alignCenter("보유 포인트: 1,179,000 포인트"));
		System.out.println(UI.alignCenter("보유 마일리지: 16,066 점"));
		System.out.println("               ");
		System.out.println(UI.alignCenter("🏝️"));
		System.out.println("               ");
		System.out.println("               ");
		System.out.println("               ");
        System.out.println(UI.alignCenter("1. 맛집/카페 조회 및 예약"));
        System.out.println(UI.alignCenter("2. 관광지/액티비티 조회 및 예약"));
        System.out.println(UI.alignCenter("3. 숙소 조회 및 예약"));
        System.out.println(UI.alignCenter("4. 렌터카/주유소/전기차 충전소 조회 및 예약"));
        System.out.println(UI.alignCenter("5. 투어 조회"));
        System.out.println();
        System.out.println(UI.alignCenter("6. 내 즐겨찾기 목록 관리"));
        System.out.println(UI.alignCenter("7. 여행 스케줄러 조회"));
        System.out.println();
        System.out.println(UI.alignCenter("8. 내 예약 관리"));
        System.out.println(UI.alignCenter("9. 리뷰 관리"));
        System.out.println(UI.alignCenter("10. 내 포인트 관리"));
        System.out.println();
        System.out.println(UI.alignCenter("11. 공지사항 조회"));
        System.out.println(UI.alignCenter("12. 관리자에게 문의하기"));
        System.out.println(UI.alignCenter("13. 계정 관리"));
        System.out.println();
        System.out.println(UI.alignCenter("0. 로그아웃"));
        System.out.println();
        
	}
		
	
}
