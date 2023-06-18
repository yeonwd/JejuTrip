package user;

import java.util.Scanner;

public class Util {

	public static boolean reconfirm() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("진행 중이던 모든 내용이 유실됩니다.");
		System.out.println("정말 취소하시겠습니까?");
		System.out.println("1. 확인 2. 취소");
		
		//문제가 있음
		//2.취소 눌렀을 때 처리해준 게 아무것도 없음... 
		
		String confirmation = scan.nextLine();
		
		if(!confirmation.equals("1") || !confirmation.equals("2")) {
			System.out.println("유효하지 않은 숫자입니다.");
			reconfirm();
		}
		else if(confirmation.equals("1")) return true;
		else if(confirmation.equals("2")) return false;
		return false;
		
	} //reconfirm()
	
	
	public static void print_result(int result, String inProg) {
		
		if (result == 1) {
			System.out.println(inProg + " 완료되었습니다.");
			System.out.println("메인으로 돌아갑니다.");
			System.out.println();
		} else {
			System.out.println(inProg + " 실패했습니다.");
			System.out.println("메인으로 돌아갑니다.");
			System.out.println();
		}
		
	}
	
}
