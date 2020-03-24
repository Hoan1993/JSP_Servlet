package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;




public class BbsDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	private BbsDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static BbsDAO instance = new BbsDAO();
	
	public static BbsDAO getInstance() {
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
		} catch (SQLException e) {
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


	public List<BbsDTO> getBbsList() {
		sql = "select * from jsp_bbs order by board_group desc, board_step asc";
		conn = openConn();
		List<BbsDTO> list = new ArrayList<BbsDTO>();
		BbsDTO dto = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new BbsDTO();
				dto.setBoard_no(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_cont(rs.getString(4));
				dto.setBoard_pwd(rs.getString(5));
				dto.setBoard_hit(rs.getInt(6));
				dto.setBoard_date(rs.getString(7));
				dto.setBoard_group(rs.getInt(8));
				dto.setBoard_step(rs.getInt(9));
				dto.setBoard_indent(rs.getInt(10));
				list.add(dto);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		
		
	
		return list;
	}


	public int insertRecord(BbsDTO dto) {
		sql = "insert into jsp_bbs values(bbs_seq.nextval, ?,?,?,?,default, sysdate, bbs_seq.currval, 0, 0)";
		conn = openConn();
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_writer());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_pwd());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	
	public BbsDTO selectOneRecordByNum(int board_no) {
		sql = "select * from jsp_bbs where board_no = ?";
		conn = openConn();
		BbsDTO dto = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BbsDTO();
				dto.setBoard_no(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_cont(rs.getString(4));
				dto.setBoard_pwd(rs.getString(5));
				dto.setBoard_hit(rs.getInt(6));
				dto.setBoard_date(rs.getString(7));
				dto.setBoard_group(rs.getInt(8));
				dto.setBoard_step(rs.getInt(9));
				dto.setBoard_indent(rs.getInt(10));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return dto;
	}
	
	public void boardHit(int board_no) {
		sql = "update jsp_bbs set board_hit = board_hit +1 where board_no=?";
		conn = openConn();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		
	}

	// jsp_bbs 테이블의 게시판의 글에 step을 하나 증가시키는 메서드
	public void replyUpdate(int board_group, int board_step) {
		sql = "update jsp_bbs set board_step = board_step +1 where board_group = ? and board_step > ?";
		conn = openConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_group);
			pstmt.setInt(2, board_step);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
	}

	// jsp_bbs 테이블의 게시판 원글에 답변글을 추가하는 메서드
	public int replyBoard(BbsDTO dto) {
		int result = 0;
		
		try {
			conn = openConn();
			sql = "insert into jsp_bbs "
					+ "values(bbs_seq.nextval, ?,?,?,?, default, sysdate, ?,?,?)";
	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_writer());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_pwd());
			pstmt.setInt(5, dto.getBoard_group());
			pstmt.setInt(6, dto.getBoard_step()+1);
			pstmt.setInt(7, dto.getBoard_indent()+1);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		return result;
	}
	

}
