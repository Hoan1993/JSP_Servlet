package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.board.model.MemberDAO;
import com.board.model.MemberDTO;

public class BoardWriteOkAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String board_writer= request.getParameter("writer").trim();		
		String board_title= request.getParameter("title").trim();
		String board_content= request.getParameter("content").trim();
		String board_pwd= request.getParameter("pwd").trim();

		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_board_pwd(board_pwd);
		
		
		int result = dao.insertBoard2(dto);

		
	
		PrintWriter out = response.getWriter();
	
		if(result == 1) { // 로그인 성공
			out.println("<script>");
			out.println("alert('등록 성공')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
			/*RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);*/
		} else  { // 비밀번호가 안 맞음.
			out.println("<script>");
			out.println("alert('등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		} 
		
	}
	
}
