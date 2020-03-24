package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int board_no = Integer.parseInt(request.getParameter("no"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO dto = dao.selectOneRecordByNum(board_no);
		dao.boardHit(board_no);
		
		request.setAttribute("dto", dto);
		request.setAttribute("nowPage", nowPage);
		
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("board_view.jsp");
		dispatcher.forward(request, response);*/
		PrintWriter out = response.getWriter();
		
		if(dto != null) { // 로그인 성공
			out.println("<script>");
			out.println("alert('등록 성공')");
			out.println("location.href='board_view.jsp?no=${dto.getBoard_no}&page=${nowPage}'");
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
