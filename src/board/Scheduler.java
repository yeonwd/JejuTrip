package board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import business.Business_DB;
import jdbc.DBUtil;
import main.UI;

public class Scheduler {
	
	//delay_loop문
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

	//스케줄러 Loop문
	public static void Scheduler_loop() {
		
			boolean loop = true;
			Scanner scan = new Scanner(System.in);
		
			while (loop) {
				
			Scheduler.Scheduler_UI();
			System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) {
				
				//1. 내 스케줄러 조회
				Scheduler_select_UI();
				delay_loop();
	
				
			} else if(sel.equals("2")) {
				
				//2. 스케줄러 등록
				Scheduler_insert_UI();
				delay_loop();
				
								
			} else if(sel.equals("3")) {
				
				//3. 스케줄러 삭제
				Scheduler_delete_UI();
				
			} else if(sel.equals("0")) {
				
				//0. 프로그램 종료
				System.out.println();
				System.out.println("❗️ 홈 화면으로 돌아갑니다. ❗️");
				loop = false;
				
			} 
			
		}
		
	}//Scheduler_loop
	
	
	//스케줄러 UI
	public static void Scheduler_UI() {
		
		//차후에, JDBC 쿼리를 통해 구현
		
		System.out.println(UI.alignCenter("== 스케줄러 =="));
		System.out.println();
		
		System.out.println("1. 내 스케줄러 조회");
		System.out.println("2. 내 스케줄러 등록");
		System.out.println("3. 내 스케줄러 삭제");
		
		System.out.println();
		
		System.out.println("0. 홈 화면으로 돌아가기");
		System.out.println();
		
			
	}//Scheduler_UI()
	
	public static void Scheduler_select_UI() {
		
		System.out.println(UI.alignCenter("== 스케줄러 조회 =="));
		System.out.println();
		Scheduler_select(main.user_id);
		
	}
	
	//스케줄러 조회
	public static void Scheduler_select(String id) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select s.scheduler_seq, u.user_id, s.scheduler_date, s.scheduler_content\r\n"
					+ "from Scheduler s\r\n"
					+ "inner join Users u\r\n"
					+ "on s.user_seq = u.user_seq\r\n"
					+ "where u.user_id = '%s'", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("1. 번호 : %s \r\n",rs.getString("scheduler_seq"));
			    System.out.printf("2. 아이디 : %s \r\n",rs.getString("user_id"));
			    System.out.printf("3. 날짜 : %s \r\n",rs.getString("scheduler_date"));   
			    System.out.printf("4. 스케줄러 내용 : %s \r\n\n",rs.getString("scheduler_content"));   
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
	
	
	
	//스케줄러 컬럼 추가
	public static void Scheduler_insert(String s1, String s2, String s3) {
	
		System.out.println(UI.alignCenter("== 새 스케줄러 등록 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("insert into Scheduler values (Scheduler_next.nextval, '%s', '%s', '%s')", s1, s2, s3);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
//					while (rs.next()) {
//						System.out.printf("[광고 번호] : %s \r\n", rs.getString("Ad_seq"));
//						System.out.printf("[사업장 번호] : %s \r\n", rs.getString("BUSINESS_seq"));
//						System.out.printf("[광고 내용] : %s \r\n",rs.getString("Ad_content"));				
//					}
			
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
	
	
	
	//스케줄러 등록
	public static void Scheduler_insert_UI() {
		
		System.out.println(UI.alignCenter("== 새 스케줄러 등록 =="));
		
		String[] bs = new String[3];
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("유저 번호를 입력하세요 : ");
		bs[0] = scan.nextLine();
		
		System.out.print("날짜를 입력하세요 : ");
		bs[1] = scan.nextLine();
		
		System.out.print("스케줄러 내용을 입력하세요 : ");
		bs[2] = scan.nextLine();
		
		//배열문을 받고 JDBC insert 쿼리 작성
		Scheduler_insert(bs[0], bs[1], bs[2]);
		
	}
	
	//스케줄러 삭제
	
	//스케줄러 컬럼 추가
	public static void Scheduler_delete(String seq) {
	
		System.out.println(UI.alignCenter("== 스케줄러 삭제 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("delete from Scheduler where Scheduler_seq = '%s'", seq);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
//						while (rs.next()) {
//							System.out.printf("[광고 번호] : %s \r\n", rs.getString("Ad_seq"));
//							System.out.printf("[사업장 번호] : %s \r\n", rs.getString("BUSINESS_seq"));
//							System.out.printf("[광고 내용] : %s \r\n",rs.getString("Ad_content"));				
//						}
			
			System.out.println("삭제가 완료 되었습니다.");
			
			
					
			rs.close();
			stat.close();
			conn.close();
			System.out.println();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//스케줄러 삭제
	public static void Scheduler_delete_UI() {
		
		System.out.println(UI.alignCenter("== 스케줄러 삭제 =="));
		
		Scheduler_select(main.user_id);
		
		String seq = "";
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("삭제할 스케줄러 번호를 입력하세요 : ");
		seq = scan.nextLine();
		
		//배열문을 받고 JDBC insert 쿼리 작성
		Scheduler_delete(seq);
		
	}
	
	
	
	
	
}
