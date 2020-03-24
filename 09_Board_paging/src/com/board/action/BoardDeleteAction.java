package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;



public class BoardDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("no"));
		//int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		
		
		
		BoardDAO bDao = BoardDAO.getInstance();
		int result = bDao.deleteBoard(num);
		
		//request.setAttribute("nowPage", nowPage);
		request.setAttribute("no", num);
		
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("board_view.jsp");
		dispatcher.forward(request, response);*/
		PrintWriter out = response.getWriter();
		
		if(result == 1) { // 삭제성공
			out.println("<script>");
			out.println("alert('등록 성공')");
			/*no=${dto.getBoard_no()}&page=${page}*/
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
