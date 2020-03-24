package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardWriteOkAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board_write.do";
		
		String board_writer = request.getParameter("writer");
		String board_title = request.getParameter("title");
		String board_cont = request.getParameter("content");
		String board_pwd = request.getParameter("pwd");
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_board_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.insertRecord(dto);
		
		// 
/*		PrintWriter out = response.getWriter();
		if(result == 1) {
			out.println("<script>");
			out.println("alert('게시글 추가 성공~~~')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 추가 실패~~~')");
			out.println("histroy.back()");
			out.println("</script>");
		}*/
		
		
		if(result == 1) {
			url ="board_list.do";
		} 
		
		return url;

	}

}
