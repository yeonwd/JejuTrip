package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		
		while (loop) {
			
			UI.mainMenu();
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 로그인
//				UI.login_UI();
				UI.login_UI();
				
			} else if(sel.equals("2")) {
				
				//2. 회원가입
				UI.regist_UI();
				
			} else if(sel.equals("3")) {
				
				//3. 아이디/비밀번호 찾기
				UI.find_idpw_UI();
				
			} else if(sel.equals("0")) {
				
				//0. 프로그램 종료
				
				System.out.println();
				System.out.println("❗️ 프로그램을 종료합니다 ❗️");
				loop = false;
				
			} 
			
		}//while(menu)

	}
	

}
