package trip;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import business.Business_DB;
import jdbc.DBUtil;
import main.UI;

public class tour {
	
	//delay Loop()
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
	//Tour_loop()
	public static void Tour_loop() {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
	
		while (loop) {
			
		Tour_list();
		System.out.println("0. 이전 화면으로 돌아가기");
		
		System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("0")) {
			
			//0. 이전 화면으로 돌아감.
			
			System.out.println();
			System.out.println("❗️ 이전 화면으로 돌아갑니다. ❗️");
			loop = false;
			
		} else {
			Tour_select(sel);
			delay_loop();
		}
		
		}
		
	}//Tour_loop()
	
	
	//투어 조회
	public static void Tour_list() {
		
		System.out.println(UI.alignCenter("== 투어 조회 =="));
		System.out.println();
		System.out.println("[번호]          [제목]");
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select Tour_seq, Tour_name from Tour order by Tour_seq");
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("  %s 	",rs.getString("Tour_seq"));
			    System.out.printf("%s \r\n",rs.getString("Tour_name"));
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
	
	
	public static void Tour_select(String input) {
		
		System.out.println(UI.alignCenter("== 투어 내용 =="));
		System.out.println();
		System.out.println(" [번호]         [제목]		[투어시작]		[투어종료]");
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select Tour_seq, Tour_name, Tour_startdate, Tour_enddate, Tour_content from Tour where Tour_seq = '%s'", input);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("  %s 	",rs.getString("Tour_seq"));
			    System.out.printf("%s	",rs.getString("Tour_name"));
			    System.out.printf("%s	",rs.getString("Tour_startdate"));
			    System.out.printf("%s	\r\n",rs.getString("Tour_enddate"));
			    System.out.println(UI.alignCenter("[투어 일정]"));
			    System.out.printf("%s \r\n",rs.getString("Tour_content"));
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
