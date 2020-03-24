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



public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
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
	// board 테이블의 전체 게시물의 수를 조회하는 메서드
	public int getListCount() {
		sql = "select count(*) from board1";
		int count = 0;
		
		conn = openConn();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return count;
	}


	// board1 테이블에서 게시물을 가져오는 메서드
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		BoardDTO dto = null;
	
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize -1);
				// 해당 페이지에 끝 번호
		int endNo = (page * rowsize);
		
		conn = openConn();
		// row_number() over : 특정 컬럼을 기준으로 행번호를 부여할 때 사용하는 함수
		
		sql = "select * from "
				+ "(select p.*, row_number() "
				+ " over(order by board_no desc) rnum "
				+ " from board1 p) "
				+ " where rnum >= ? and rnum <= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
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
			close(conn, pstmt, rs);
		}
	
		
		return list;
	}

	public int insertBoard2(BoardDTO dto) {
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
			close(conn, pstmt);
		}
		
		
		return result;
	}

	public int insertBoard(BoardDTO dto) {
		int count = 0;
		int result = 0;
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
			pstmt.setString(4, dto.getBoard_writer());
			pstmt.setInt(5, dto.getBoard_hit());
			
			result =pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		
		
		return result;
	}



	public BoardDTO selectOneRecordByNum(int board_no) {
		sql = "select * from board1 where board_no = ?";
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
			close(conn, pstmt, rs);
		}
		
		return dto;
	}



	public void boardHit(int board_no) {
		sql = "update board1 set board_hit = board_hit +1 where board_no=?";
		conn = openConn();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		
		
	}



	public int deleteBoard(int num) {
		sql = "delete from board1 where board_no =?";
		conn = openConn();
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			close(conn, pstmt);
		}
		
		return result;
		
	}

/*	public int getListCountBySearch() {
		// TODO Auto-generated method stub
		return 0;
	}*/


	// board1 테이블에서 검색어에 해당하는 레코드의 수를 조회하는 메서드.
	public int searchListCount(String field, String name) {
		int count = 0;
		
		conn = openConn();
		
		
		if(field.equals("title")) {
			sql = "select count(*) from board1 where board_title like ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+name+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
			
		} else if(field.equals("cont")) {
			sql = "select count(*) from board1 where board_cont like ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+name+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
		} else if(field.equals("title_cont")) {
			sql = "select count(*) from board1 where board_title like ? or board_cont like ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+name+"%");
				pstmt.setString(2, "%"+name+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
		} else if(field.equals("writer")) {
			sql = "select count(*) from board1 where board_writer like ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+name+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
		}
		
		
		return count;
	} // searchListCount() 메서드 end


	// board1 테이블에서 검색한 내용을 가지고 페이징 처리를 하는 메서드
	public List<BoardDTO> searchBoardList(String field, String name, int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize -1);
		// 해당 페이지에 끝 번호
		int endNo = (page * rowsize);
		
		conn = openConn();
		BoardDTO dto = null;
		
		if(field.equals("title")) {
			
			try {
				
				sql = "select * from "
						+ "(select p.*, row_number() "
						+ " over(order by board_no desc) rnum "
						+ " from board1 p where board_title like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+name+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
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
				close(conn, pstmt, rs);
			}
		} else if(field.equals("cont")) {
			try {
				
				sql = "select * from "
						+ "(select p.*, row_number() "
						+ " over(order by board_no desc) rnum "
						+ " from board1 p where board_cont like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+name+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
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
				close(conn, pstmt, rs);
			}
		} else if(field.equals("title_cont")) {
			try {
				
				sql = "select * from "
						+ "(select p.*, row_number() "
						+ " over(order by board_no desc) rnum "
						+ " from board1 p where board_title like ? or board_cont like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+name+"%");
				pstmt.setString(2, "%"+name+"%");
				pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);
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
				close(conn, pstmt, rs);
			}
		} else if(field.equals("writer")) {
			try {
				
				sql = "select * from "
						+ "(select p.*, row_number() "
						+ " over(order by board_no desc) rnum "
						+ " from board1 p where board_writer like ?) "
						+ " where rnum >= ? and rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+name+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
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
				close(conn, pstmt, rs);
			}
		}
		
		return list;
	}
	
	

	
	
	
}
