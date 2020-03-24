package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.board.model.BoardDTO;
import com.sist.vo.BoardVO;

import util.DBManager;

public class BoardDAO {

	private BoardDAO() {

	}
	
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	
	public List<BoardVO> selectAllBoard() {
		String sql = "select * from board order by num desc";
		List<BoardVO> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setPass(rs.getString("pass"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
				list.add(bVo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		
		return list;
	
	}
	
	public void insertBoard(BoardVO bVo) {
		String sql = "insert into board(num, name, email, pass, title, content) "
				+ "values(board_seq.NEXTVAL,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
			
	}
	
	public void updateReadCount(String num) {
		String sql = "update board set readcount = readcount+1 where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	public void updateReadCount2(int num) {
		String sql = "update board set readcount = readcount+1 where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	public BoardVO selectOneBoardByNum(String num) {
		String sql = "select * from board where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO bVo = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bVo= new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setPass(rs.getString("pass"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		 
		return bVo;
	}
	
	public BoardVO selectOneBoardByNum2(int num) {
		String sql = "select * from board where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO bVo = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bVo= new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setPass(rs.getString("pass"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		 
		return bVo;
	}
	
	public void updateBoard(BoardVO bVo) {
		String sql = "update board set name=?, email=?, pass=?, title=?, content=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setInt(6, bVo.getNum());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	public BoardVO checkPassWord(String pass, int num) {
		String sql = "select * from board where pass=? and num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVO bVo = null;
	
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		
		return bVo;
	}
	
	public void deleteBoard(String num) {
		String sql ="delete board where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public int getListCount() {
		String sql = "select count(*) from board";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		conn = DBManager.getConnection();
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
			DBManager.close(conn, pstmt, rs);
		}
		
		return count;
		
	}

	public List<BoardVO> getBoardList(int page, int rowsize) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize -1);
				// 해당 페이지에 끝 번호
		int endNo = (page * rowsize);
		
		conn = DBManager.getConnection();
		// row_number() over : 특정 컬럼을 기준으로 행번호를 부여할 때 사용하는 함수
		
		String sql = "select * from "
				+ "(select p.*, row_number() "
				+ " over(order by num desc) rnum "
				+ " from board p) "
				+ " where rnum >= ? and rnum <= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new BoardVO();
				dto.setNum(rs.getInt(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setReadcount(rs.getInt(7));
				dto.setWritedate(rs.getTimestamp(8));
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
	
	public int searchListCount(String field, String name) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBManager.getConnection();
		String sql = "";
		
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
				DBManager.close(conn, pstmt, rs);
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
				DBManager.close(conn, pstmt, rs);
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
				DBManager.close(conn, pstmt, rs);
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
				DBManager.close(conn, pstmt, rs);
			}
		}
		
		
		return count;
	} // searchListCount() 메서드 end
	
	public List<BoardVO> searchBoardList(String field, String name, int page, int rowsize) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBManager.getConnection();
		String sql = "";
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize -1);
		// 해당 페이지에 끝 번호
		int endNo = (page * rowsize);
		
		BoardVO dto = null;
		
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
					dto = new BoardVO();
					dto.setNum(rs.getInt(1));
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
