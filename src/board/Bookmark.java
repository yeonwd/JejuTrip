package board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import jdbc.DBUtil;
import main.UI;

public class Bookmark {

	//즐겨찾기
	public static void business_account(String id) {
		
		System.out.println(UI.alignCenter("== 즐겨찾기 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select b.bookmark_seq, u.user_name, u.user_id, b.bookmark_name\r\n"
					+ "from Bookmark b\r\n"
					+ "inner join Users u\r\n"
					+ "on b.user_seq = u.user_seq\r\n"
					+ "where u.user_id in '%s'", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("1. 북마크 번호 : %s \r\n",rs.getString("bookmark_seq"));
			    System.out.printf("2. 유저 이름 : %s \r\n",rs.getString("user_name"));
			    System.out.printf("3. 유저 아이디 : %s \r\n",rs.getString("user_id"));   
			    System.out.printf("4. 북마크 이름 : %s \r\n",rs.getString("bookmark_name"));   
			    System.out.println();
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
	
	
	
	//Bookmark_loop()
	public static void Bookmark_loop() {
		
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
	
		while (loop) {
			
		business_account(main.user_id);
		System.out.println("0. 이전 화면으로 돌아가기");
		
		System.out.print("🏝️ 원하시는 서비스를 입력하세요 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("0")) {
			
			//0. 이전 화면으로 돌아감.
			
			System.out.println();
			System.out.println("❗️ 이전 화면으로 돌아갑니다. ❗️");
			loop = false;
			
		} else {
			trip.tour.delay_loop();
		}
		
		}
		
	}//Tour_loop()
	
}
