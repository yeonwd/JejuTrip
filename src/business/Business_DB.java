package business;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.DBUtil;
import main.UI;

public class Business_DB {
	
	//로그인한 id에 따른 
	public static void name(String id) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select Owner_name from owner where owner_id='%s'", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
//					System.out.println(rs.getString("Owner_name"));
				UI.name = rs.getString("Owner_name");
			}
			
			
					
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//id에 따른 business 목록 
	public static void business_list(String id) {
		
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select Business_name, buisiness_address from Business b inner join Owner o on b.owner_seq = o.owner_seq where o.owner_seq = (select owner_seq from Owner where owner_id ='%s')", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				System.out.printf("[%s] ", rs.getString("BUSINESS_NAME"));
				System.out.println(rs.getString("BUISINESS_ADDRESS"));
			}
			
			
					
			rs.close();
			stat.close();
			conn.close();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//광고 리스트 출력
	public static void advertisement_list(String id) {
		
		System.out.println(UI.alignCenter("== 광고 조회 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select a.ad_seq, a.business_seq, a.ad_content from Advertisement a inner join Owner o on a.owner_seq = o.owner_seq where o.owner_seq = (select owner_seq from Owner where owner_id ='%s')", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				System.out.printf("[광고 번호] : %s \r\n", rs.getString("Ad_seq"));
				System.out.printf("[사업장 번호] : %s \r\n", rs.getString("BUSINESS_seq"));
				System.out.printf("[광고 내용] : %s \r\n",rs.getString("Ad_content"));				
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

	//광고 컬럼 추가
	public static void advertisement_insert(String s1, String s2, String s3) {
		
		System.out.println(UI.alignCenter("== 새 광고 등록 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("insert into advertisement values (AD_NSEQ.nextval, '%s', '%s', '%s')", s1, s2, s3);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
	//			while (rs.next()) {
	//				System.out.printf("[광고 번호] : %s \r\n", rs.getString("Ad_seq"));
	//				System.out.printf("[사업장 번호] : %s \r\n", rs.getString("BUSINESS_seq"));
	//				System.out.printf("[광고 내용] : %s \r\n",rs.getString("Ad_content"));				
	//			}
			
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
	
	//사업자 컬럼 추가
	public static void business_insert(String s1, String s2, String s3, String s4) {
	
		System.out.println(UI.alignCenter("== 새 사업장 등록 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("insert into Business values (BUSINESS_NSEQ.nextval, '%s', '%s', '%s', '%s')", s1, s2, s3, s4);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
//				while (rs.next()) {
//					System.out.printf("[광고 번호] : %s \r\n", rs.getString("Ad_seq"));
//					System.out.printf("[사업장 번호] : %s \r\n", rs.getString("BUSINESS_seq"));
//					System.out.printf("[광고 내용] : %s \r\n",rs.getString("Ad_content"));				
//				}
			
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
	
	//reivew 목록 출력
	public static void review_list(String id) {
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			System.out.println(UI.alignCenter("== 리뷰 관리 =="));
			System.out.println();
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("SELECT r.review_seq, u.user_id, r.review_content, r.review_open, rp.review_reply_detail FROM owner o INNER JOIN business b ON o.owner_seq = b.owner_seq INNER JOIN review r ON r.business_seq = b.business_seq INNER JOIN users u ON u.user_seq = r.user_seq LEFT JOIN review_reply rp ON r.review_seq = rp.review_seq WHERE r.business_seq IN (SELECT b.business_seq FROM owner o INNER JOIN business b ON o.owner_seq = b.owner_seq WHERE o.owner_id = '%s')", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.println("[번호] 	[ID] 	     	[content] 	      [공개] 	   [답글]");
			    System.out.printf("%s 	",rs.getString("REVIEW_SEQ"));
			    System.out.printf("%s 	",rs.getString("USER_ID"));
			    System.out.printf("%s 	",rs.getString("REVIEW_CONTENT"));   
			    System.out.printf("%s 	",rs.getString("REVIEW_OPEN"));
			    System.out.printf("%s \r\n",rs.getString("REVIEW_REPLY_DETAIL"));
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
	
	//예약 목록 출력
	public static void reservation_list(String id) {
		
		System.out.println(UI.alignCenter("== 예약 관리 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("SELECT r.reservation_seq, u.user_id, r.reservation_begin, r.reservation_end \r\n"
					+ "from business b\r\n"
					+ "inner join reservation r\r\n"
					+ "on b.business_seq = r.business_seq\r\n"
					+ "inner join owner o\r\n"
					+ "on o.owner_seq = b.owner_seq\r\n"
					+ "inner join users u\r\n"
					+ "on u.user_seq = r.user_seq\r\n"
					+ "WHERE r.business_seq IN (SELECT b.business_seq FROM owner o INNER JOIN business b ON o.owner_seq = b.owner_seq WHERE o.owner_id = '%s')", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.println("[번호] 	[ID] 	     	[예약 시작일] 	      [예약 종료일]");
			    System.out.printf("%s 	",rs.getString("RESERVATION_SEQ"));
			    System.out.printf("%s 	",rs.getString("USER_ID"));
			    System.out.printf("%s 	",rs.getString("RESERVATION_BEGIN"));   
			    System.out.printf("%s 	\r\n",rs.getString("RESERVATION_END"));
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
	
	//계정 관리 출력
	public static void business_account(String id) {
		
		System.out.println(UI.alignCenter("== 계정 관리 =="));
		System.out.println();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select owner_name, owner_id, owner_pw\r\n"
					+ "from owner\r\n"
					+ "where owner_id = '%s'", id);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("1. 이름 : %s \r\n",rs.getString("owner_name"));
			    System.out.printf("2. 아이디 : %s \r\n",rs.getString("owner_id"));
			    System.out.printf("3. 비밀번호 : %s \r\n",rs.getString("owner_pw"));   
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
	
	//공지 사항 출력
	public static void Notice_list() {
		
		System.out.println(UI.alignCenter("== 공지 사항 =="));
		System.out.println();
		System.out.println("[번호]          [제목]");
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select notice_seq, notice_title from notice order by notice_seq");
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("  %s 	",rs.getString("notice_seq"));
			    System.out.printf("%s \r\n",rs.getString("notice_title"));
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
	
	
	public static void Notice_select(String input) {
		
		System.out.println(UI.alignCenter("== 공지 사항 내용 =="));
		System.out.println();
		System.out.println(" [번호]         [제목]");
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
			String sql = String.format("select notice_seq, notice_title, notice_content from notice where notice_seq = '%s'", input);
			
			
			//60명 레코드 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
			    System.out.printf("  %s 	",rs.getString("notice_seq"));
			    System.out.printf("%s\r\n",rs.getString("notice_title"));
			    System.out.println(UI.alignCenter("[내용]"));
			    System.out.printf("%s \r\n",rs.getString("notice_content"));
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
