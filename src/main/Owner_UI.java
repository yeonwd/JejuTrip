package main;

import business.Business_DB;

public class Owner_UI {
	
	public static void Owner_name() {
		Business_DB.name(UI.owner_id);
		System.out.println(UI.alignCenter("사업자 로그인 성공!"));
		System.out.printf(UI.alignCenter("안녕하세요, %s 님!"), UI.name);
	}
	
	public static void Owner_menu() {
		System.out.println();
		System.out.println("               ");
		System.out.println(UI.alignCenter("[제주 어때 with 사업자]"));
		System.out.println("               ");
		System.out.println(UI.alignCenter("1. 사업장 관리"));
		System.out.println(UI.alignCenter("2. 리뷰 관리"));
		System.out.println(UI.alignCenter("3. 예약 관리"));
		System.out.println(UI.alignCenter("4. 계정 관리"));
		System.out.println(UI.alignCenter("5. 공지사항 조회"));
		System.out.println(UI.alignCenter("0. 로그아웃"));
		System.out.println("         ");
	}

}
