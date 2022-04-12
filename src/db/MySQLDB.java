package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;

public class MySQLDB {
	public void insert(String mID, String mPW) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:mysql://dbckd999.iptime.org:3061/swingDB?useSSL=false&allowPublicKeyRetrieval=true";
		String id = "dbckd999";
		String pw = "skdmlelq99@";
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "insert into memberTBL(mID, mPW) values(?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mID);
			pstmt.setString(2, mPW);
			pstmt.executeUpdate();
			System.out.println("MemberDTO insert() 메서드 실행 완료");
		} catch (SQLException e) {
			
			System.out.println("pstmt ERROR");
			e.printStackTrace();
		}finally {
			//pstmt꽉 차있다면
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean select(String mID, String mPW) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://dbckd999.iptime.org:3061/swingDB?useSSL=false&allowPublicKeyRetrieval=true";
		String id = "dbckd999";
		String pw = "skdmlelq99@";
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "select * from memberTBL where mID = ? and mPW = ?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mID);
			pstmt.setString(2, mPW);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
//			System.out.println("MemberDTO select() 메서드 실행 완료");
		} catch (SQLException e) {
			
			System.out.println("pstmt ERROR");
			e.printStackTrace();
		}finally {
			//pstmt꽉 차있다면
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean alreadyHaveIDCheck(String mID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://dbckd999.iptime.org:3061/swingDB?useSSL=false&allowPublicKeyRetrieval=true";
		String id = "dbckd999";
		String pw = "skdmlelq99@";
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "select mID from memberTBL WHERE mID = ?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//이미 아이디가 있다면
				return true;
			}else {
				//아이디가 없다면
				return false;
			}
//			System.out.println("MemberDTO select() 메서드 실행 완료");
		} catch (SQLException e) {
			
			System.out.println("pstmt ERROR");
			e.printStackTrace();
		}finally {
			//pstmt꽉 차있다면
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
		
	}
	
	
}
