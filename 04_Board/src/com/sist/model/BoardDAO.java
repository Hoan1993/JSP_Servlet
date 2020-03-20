package com.sist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	Connection conn = null;	// db 연결 객체
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	
	public BoardDAO() {	// 기본 생성자
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "genie";
		String password = "1234";
		
		try {
			// 1. 드라이버를 로딩하는 작업
			Class.forName(driver);
			
			// 2. DB와 연결
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// board1 테이블의 전체 레코드를 조회하여 ArrayList 객체에 저장
	public List<BoardDTO> getList() {
		List<BoardDTO> board = new ArrayList<BoardDTO>();
		sql = "select * from board1 order by board_no desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {	// next() : 데이터가 존재하면 true 값 반환
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt(1));
				dto.setBoard_writer(rs.getString(2));
				dto.setBoard_title(rs.getString(3));
				dto.setBoard_cont(rs.getString(4));
				dto.setBoard_board_pwd(rs.getString(5));
				dto.setBoard_hit(rs.getInt(6));
				dto.setBoard_regdate(rs.getString(7));
				
				board.add(dto);				
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return board;
	}

	public void boardHit(int board_no) {
		String sql = "update board1 set board_hit = board_hit+1 where board_no= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
			
		pstmt.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public BoardDTO selectOneRecordByNum(int board_no) {
		String sql = "select * from board1 where board_no= ?";
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
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return dto;
	}

	public int insertToBoard(BoardDTO dto) {
		String sql = "insert into board1 values(board1_seq.nextval, ?,?,?,?, default, sysdate)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_writer());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_board_pwd());
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
		
		
	}

	public int updateBoard(BoardDTO dto) {
		String sql2 = "select * from board1 where board_no =?";
		String sql = "update board1 set board_writer = ?, board_title = ?, "
				+ "board_cont = ? where board_no =? and board_pwd =?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, dto.getBoard_no());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getBoard_board_pwd().equals(rs.getString("board_pwd"))) {
					sql2 = "update board1 set board_title=?, "
							+ "board_cont=? where board_no=?";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, dto.getBoard_title());
					pstmt.setString(2, dto.getBoard_cont());
					pstmt.setInt(3, dto.getBoard_no());
					result = pstmt.executeUpdate();	// 성공시에는 1 값 반환
				} else {
					result = -1;
				}
			}
			rs.close(); pstmt.close(); conn.close();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int delete(BoardDTO dto) {
		String sql2 = "select * from board1 where board_no =?";
		String sql = "update board1 set board_writer = ?, board_title = ?, "
				+ "board_cont = ? where board_no =? and board_pwd =?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, dto.getBoard_no());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getBoard_board_pwd().equals(rs.getString("board_pwd"))) {
					sql2 = "delete from board1 where board_no=?";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, dto.getBoard_no());
					result = pstmt.executeUpdate();	// 성공시에는 1 값 반환
				} else {
					result = 0;
				}
			}
			rs.close(); pstmt.close(); conn.close();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	// board1 테이블에서 게시물을 검색하는 메서드
	public List<BoardDTO> searchBoard(String find_field, String find_name) {
		String str = "";
		
		String sql ="";
		List<BoardDTO> search = new ArrayList<BoardDTO>();
		
		if(find_field.equals("title")) {
			//str = "board_title like ?";
		try {	
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
				
				rs.close(); pstmt.close(); conn.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			
		}

		return search;
	}


}
