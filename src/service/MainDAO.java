package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.DBUtil;

public class MainDAO {

	//관리자 정보
	public static ResultSet getAdmin(String id, String pw) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "SELECT * FROM Admin WHERE Admin_id = ? AND Admin_pw = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setString(2, pw);
	        rs = pstmt.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rs;
	}
	
	//사업자 정보
	public static ResultSet getOwner(String id, String pw) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "SELECT * FROM Owner WHERE Owner_id = ? AND Owner_pw = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setString(2, pw);
	        rs = pstmt.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rs;
	}
	
	//사업자 회원가입
	public static ResultSet sign_up_Owner(String name, String id, String pw) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "INSERT INTO Owner VALUES (owner_seq.nextVal, ?, ?, ?)";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, id);
	        pstmt.setString(3, pw);
	        rs = pstmt.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rs;
	}
	
	
	
	//사업자 아이디찾기
	public static String getownerId(String name) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String OWNER_id = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "SELECT OWNER_id FROM owner WHERE OWNER_NAME = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	        	OWNER_id = rs.getString("OWNER_id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    
	    return OWNER_id;
	}
	
	//사업자 비밀번호 찾기
	public static String getownerPw(String name, String id) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String OWNER_pw = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "SELECT OWNER_pw FROM owner WHERE OWNER_NAME = ? and OWNER_ID = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, id);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	        	OWNER_pw = rs.getString("OWNER_pw");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    
	    return OWNER_pw;
	}
		
	//유저 정보
	public static ResultSet getUser(String id, String pw) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "SELECT * FROM Users WHERE user_id = ? AND user_pw = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setString(2, pw);
	        rs = pstmt.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rs;
	}
	
	//유저 회원가입
	public static ResultSet sign_up_Users(String name, String ssn, String id, String pw, String nickname) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "INSERT INTO Users VALUES (User_seq.nextVal, ?, ?, ?, ?, ?, 0, 0)";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, ssn);
	        pstmt.setString(3, id);
	        pstmt.setString(4, pw);
	        pstmt.setString(5, nickname);
	        rs = pstmt.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rs;
	}
	
	//유저 아이디찾기
	public static String getuserId(String name, String ssn) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String USER_id = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "SELECT USER_id FROM users WHERE USER_NAME = ? and USER_SSN = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, ssn);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	        	USER_id = rs.getString("USER_id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    
	    return USER_id;
	}
	
	//유저 비밀번호 찾기
	public static String getuserPw(String name, String ssn, String id) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String USER_pw = null;
	    try {
	        conn = DBUtil.open();
	        String sql = "SELECT USER_pw FROM users WHERE USER_NAME = ? and USER_SSN = ? and USER_id = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, ssn);
	        pstmt.setString(3, id);
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	        	USER_pw = rs.getString("USER_pw");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    
	    return USER_pw;
	}
	
	//관리자 로그인
	public static boolean Admin_login(String id, String pw) {
	    ResultSet rs = null;
	    try {
	        rs = getAdmin(id, pw);
	        if (rs.next()) {
	            // 로그인 성공
	        	main.UI.admin_id = id;
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    // 로그인 실패
	    return false;
	}
	
	//사업자 로그인
	public static boolean Owner_login(String id, String pw) {
	    ResultSet rs = null;
	    try {
	        rs = getOwner(id, pw);
	        if (rs.next()) {
	            // 로그인 성공
	        	main.UI.owner_id = id;
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    // 로그인 실패
	    return false;
	}
	//유저 로그인
	public static boolean Users_login(String id, String pw) {
	    ResultSet rs = null;
	    try {
	        rs = getUser(id, pw);
	        if (rs.next()) {
	            // 로그인 성공
	        	main.UI.user_id = id;
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    // 로그인 실패
	    return false;
	}
	
	//유저 계정 관리
	public static boolean Users_account(String id, String pw) {
	    ResultSet rs = null;
	    try {
	        rs = getUser(id, pw);
	        if (rs.next()) {
	            // 로그인 성공
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    // 로그인 실패
	    return false;
	}
	
}
