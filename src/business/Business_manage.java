package business;

import java.util.Scanner;

import main.UI;

public class Business_manage {
	
	public static void delay_loop() {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
	
		while (loop) {
		
		System.out.print("0. 이전 화면으로 돌아가기 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("0")) {
			
			//0. 프로그램 종료
			
			System.out.println();
			System.out.println("❗️ 이전 화면으로 돌아갑니다. ❗️");
			loop = false;
			
		} 
		}
		
	}
	
	//사업장 관리 Loop문
	public static void manage_loop() {
		
			
		
			boolean loop = true;
			Scanner scan = new Scanner(System.in);
		
			while (loop) {
				
			manage_UI();
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 내 사업장 등록
				insert_business();
	
				
			} else if(sel.equals("2")) {
				
				//2. 내 사업장 관리
				my_manage_loop();
				
								
			} else if(sel.equals("0")) {
				
				//0. 프로그램 종료
				
				System.out.println();
				System.out.println("❗️ 홈 화면으로 돌아갑니다. ❗️");
				loop = false;
				
			} 
			
		}
		
	}//manage_Loop
	
	//사업장 관리 UI
	public static void manage_UI() {
		
		//차후에, JDBC 쿼리를 통해 구현
		
		System.out.println(UI.alignCenter("== 사업장 관리 =="));
		System.out.println();
		
		//select 문을 통해 사업장에 대한 정보를 출력
		Business_DB.business_list(UI.owner_id);
		
		System.out.println("1. 새 사업장 등록");
		System.out.println("2. 내 사업장 관리");
		
		System.out.println();
		
		System.out.println("0. 홈 화면으로 돌아가기");
		System.out.println();
		
			
	}
	
	//사업장 등록 메소드
	public static void insert_business() {
		
		System.out.println(UI.alignCenter("== 새 사업장 등록 =="));
		
		String[] bs = new String[4];
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("사업자 번호를 입력하세요 : ");
		bs[0] = scan.nextLine();
		
		System.out.print("사업장 종류를 입력하세요 : ");
		bs[1] = scan.nextLine();
		
		System.out.print("상호명을 입력하세요 : ");
		bs[2] = scan.nextLine();
		
		System.out.print("사업장 주소를 입력하세요 : ");
		bs[3] = scan.nextLine();
		
		//배열문을 받고 JDBC insert 쿼리 작성
		Business_DB.business_insert(bs[0], bs[1], bs[2], bs[3]);
		
	}
	
	//사업장 관리 메소드
	public static void my_manage() {
		
		System.out.println(UI.alignCenter("== 내 사업장 관리 =="));
		System.out.println();
		
		//select 문을 통해 사업장에 대한 정보를 출력
		Business_DB.business_list(UI.owner_id);
		System.out.println();
		
		System.out.println("1. 광고 조회");
		System.out.println("2. 새 광고 등록");
		System.out.println();
		
		System.out.println("0. 이전 화면으로 돌아가기");
		
	}
	
	//사업장 관리 Loop문
	public static void my_manage_loop() {
		
			boolean loop = true;
			Scanner scan = new Scanner(System.in);
		
			while (loop) {
				
			my_manage();
			
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 광고 조회
				//select문을 통해 해당 광고를 조회
				Business_DB.advertisement_list(UI.owner_id);
				delay_loop();
				
			} else if(sel.equals("2")) {
				
				//2. 새 광고 등록
				insert_advertise();
				
				
								
			} else if(sel.equals("0")) {
				
				//0. 이전 화면으로 돌아감.
				
				System.out.println();
				System.out.println("❗️ 이전 화면으로 돌아갑니다. ❗️");
				loop = false;
				
			} 
		}
		
	}//my_manage_loop
	
	
	public static void insert_advertise() {
		
		System.out.println(UI.alignCenter("== 새 광고 등록 =="));
		
		String[] bs = new String[4];
		
		Scanner scan = new Scanner(System.in);
		
		//seq.nextVal
		 
		System.out.print("사업장 번호를 입력하세요 : ");
		bs[0] = scan.nextLine();
		
		System.out.print("사업자 번호를 입력하세요 : ");
		bs[1] = scan.nextLine();
		
		System.out.println("광고내용을 입력하세요 : ");
		bs[2] = scan.nextLine();
		
		//배열문을 받고 JDBC insert 쿼리 작성
		Business_DB.advertisement_insert(bs[0], bs[1], bs[2]);
	}
	
	//사업장 관리 Loop문
	public static void notice_loop() {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
	
		while (loop) {
			
		Business_DB.Notice_list();
		System.out.println("0. 이전 화면으로 돌아가기");
		
		System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("0")) {
			
			//0. 이전 화면으로 돌아감.
			
			System.out.println();
			System.out.println("❗️ 이전 화면으로 돌아갑니다. ❗️");
			loop = false;
			
		} else {
			Business_DB.Notice_select(sel);
			delay_loop();
		}
		
	}
		
	}//notice_loop()
	
}
