package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import util.DBManager;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt= null;
	ResultSet rs = null;
	String sql = null;
	
	private BoardDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static BoardDAO instance = new BoardDAO();
	
	
	public static BoardDAO getInstance() {
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


	public List<BoardDTO> getInfo() {
		sql = "select * from board1 order by board_no desc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO dto = null;
		
		try {
			conn = openConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new BoardDTO();
				dto.setBoard_no(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_cont(rs.getString(4));
				dto.setBoard_board_pwd(rs.getString(5));
				dto.setBoard_hit(rs.getInt(6));
				dto.setBoard_regdate(rs.getString(7));
				list.add(dto);		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
		
		
		
	}


	public int insertRecord(BoardDTO dto) {
		sql = "insert into board1 values(board1_seq.nextval, ?,?,?,?, default, sysdate)";
		conn = openConn();
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_writer());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_board_pwd());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		
		return result;
	}
	
	public int insertRecordNew(BoardDTO dto) {
		
		int result = 0;
		int count = 0;
		try {
			conn = openConn();
			conn.setAutoCommit(false);
			sql = "select max(board_no) from board1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			} else {
				count = 1;
			}
			
			sql = "insert into board1 "
					+ "values(?,?,?,?,?,default,sysdate)";
			
	
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_board_pwd());
			result = pstmt.executeUpdate();
			conn.commit();
			// rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			try {
				// 처리 도중에 문제가 발생한 경우 되돌린다.
				conn.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		} 
		
		
		
		return result;
	}


	public BoardDTO selectOneRecordByNum(int board_no) {
		sql = "select * from board1 where board_no=?";
		
		conn = openConn();
		BoardDTO dto = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setBoard_no(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_cont(rs.getString(4));
				dto.setBoard_board_pwd(rs.getString(5));
				dto.setBoard_hit(rs.getInt(6));
				dto.setBoard_regdate(rs.getString(7));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return dto;
	}


	public void boardHit(int board_no) {
		sql = "update board1 set board_hit = board_hit+1 where board_no=?";
		
		conn = openConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}


	public int delete(int board_no) {
		sql = "delete from board1 where board_no = ?";
		conn = openConn();
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}


	public int update(BoardDTO dto) {
		sql = "update board1 set board_title = ?, board_cont = ? where board_no = ? and board_pwd=?";
		conn = openConn();
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_cont());
			pstmt.setInt(3, dto.getBoard_no());
			pstmt.setString(4, dto.getBoard_board_pwd());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}


	public List<BoardDTO> searchBoard(String find_field, String find_name) {
		String str = "";
		sql = "select * from board1 where "+str+" like ?";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO dto = null;
		
		if(find_field.equals("title")) {
			str = "board_title";
		} else if(find_field.equals("content")) {
			str = "board_cont";
		}
		
		conn = openConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+find_name+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new BoardDTO();
				dto.setBoard_no(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_cont(rs.getString(4));
				dto.setBoard_board_pwd(rs.getString(5));
				dto.setBoard_hit(rs.getInt(6));
				dto.setBoard_regdate(rs.getString(7));
				list.add(dto);
				
			}
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		
		
		return list;
	}
	
	public List<BoardDTO> searchBoard2(String find_field, String find_name) {
		String str = "";
		
		String sql ="";
		List<BoardDTO> search = new ArrayList<BoardDTO>();
		
		if(find_field.equals("title")) {
			//str = "board_title like ?";
		try {
			openConn();
			sql = "select * from board1 where board_title like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+find_name+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	// next() : 데이터가 존재하면 true 값 반환
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_cont(rs.getString(4));
				dto.setBoard_board_pwd(rs.getString(5));
				dto.setBoard_hit(rs.getInt(6));
				dto.setBoard_regdate(rs.getString(7));
				
				
				search.add(dto);				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
			
					
		} else if(find_field.equals("cont")) {
			/* str = "board_cont like ?";*/
			
			try {
				openConn();
				sql = "select * from board1 where board_cont like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+find_name+"%");
				rs = pstmt.executeQuery();
				
				while(rs.next()) {	// next() : 데이터가 존재하면 true 값 반환
					BoardDTO dto = new BoardDTO();
					dto.setBoard_no(rs.getInt(1));
					dto.setBoard_writer(rs.getString(2));
					dto.setBoard_title(rs.getString(3));
					dto.setBoard_cont(rs.getString(4));
					dto.setBoard_board_pwd(rs.getString(5));
					dto.setBoard_hit(rs.getInt(6));
					dto.setBoard_regdate(rs.getString(7));

					search.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			/*
			 * //str = "board_title like ? or board_cont like ?";
			 */ try {
				 openConn();
				sql = "select * from board1 where board_title like ? or board_cont like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + find_name + "%");
				pstmt.setString(2, "%" + find_name + "%");
				rs = pstmt.executeQuery();

				while (rs.next()) { // next() : 데이터가 존재하면 true 값 반환
					BoardDTO dto = new BoardDTO();
					dto.setBoard_no(rs.getInt(1));
					dto.setBoard_writer(rs.getString(2));
					dto.setBoard_title(rs.getString(3));
					dto.setBoard_cont(rs.getString(4));
					dto.setBoard_board_pwd(rs.getString(5));
					dto.setBoard_hit(rs.getInt(6));
					dto.setBoard_regdate(rs.getString(7));

					search.add(dto);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			 
			
		}

		return search;
	}	
	
}
