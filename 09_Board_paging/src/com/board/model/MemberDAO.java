package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.xml.internal.ws.Closeable;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";	
	
	private MemberDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public Connection openConn() {
		
		try {

			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();
			// 2. lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			// 3. DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			conn = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int userCheck(String member_id, String member_pwd) {
		sql = "select * from jsp_member where member_id=? and member_state=1";
		conn = openConn();
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(member_pwd.equals(rs.getString("member_pwd"))) {
					result = 1;
				} else {
					result = -1;
				}
			} else {	// 회원이 아닌 경우이거나 탈퇴한 경우
				result = 0;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			if(rs!= null ) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		return result;
	}

	public MemberDTO getMember(String member_id) {
		sql = "select * from jsp_member where member_id=?";
		conn = openConn();
		MemberDTO dto = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setMember_id(rs.getString(1));
				dto.setMember_pwd(rs.getString(2));
				dto.setMember_name(rs.getString(3));
				dto.setMember_nickname(rs.getString(4));
				dto.setMember_zip1(rs.getString(5));
				dto.setMember_zip2(rs.getString(6));
				dto.setMember_addr1(rs.getString(7));
				dto.setMember_addr2(rs.getString(8));
				dto.setMember_regdate(rs.getString(9));
				dto.setMember_state(rs.getInt(10));
				dto.setMember_delcont(rs.getString(11));
				dto.setMember_deldate(rs.getString(12));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			if(rs!= null ) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		return dto;
	}
	
	public int checkMemberId(String userId) {
		sql = "select * from jsp_member where member_id = ?";
		int result = 0;
		conn = openConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}

		return result;
	}
	// 우편번호를 검색하는 메서드
	public ArrayList<String> searchZipCode(String dong) {
		ArrayList<String> zip = new ArrayList<String>();
		
		conn = openConn();
		sql = "select * from zipcode where dong like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+dong+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String zipCode = rs.getString("zipcode");
				String sido = rs.getString("sido");
				String gugun = rs.getString("gugun");
				String dong1 = rs.getString("dong");
				String bunji = rs.getString("bunji");
				
				// 번지를 뺀 주소를 저장
				String addr1 = sido + gugun + dong1;
				// 번지를 포함한 주소 저장
				String addr2 = sido + gugun+ dong1 + bunji;
				
				// list에 레코드 형태로 저장
				zip.add(zipCode+", "+addr1+", "+addr2);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return zip;
	}	// searchZipCode() 메서드 end
}
