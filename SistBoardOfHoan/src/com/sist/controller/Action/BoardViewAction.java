package com.sist.controller.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class BoardViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/boardView.jsp";
		/*BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();
		
		String num = request.getParameter("num");
		
		bDao.updateReadCount(num);
		bVo = bDao.selectOneBoardByNum(num);
		
		request.setAttribute("board", bVo);*/
		
		int board_no = Integer.parseInt(request.getParameter("num"));
		//int nowPage = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardVO dto = dao.selectOneBoardByNum2(board_no);
		dao.updateReadCount2(board_no);
		
		request.setAttribute("board", dto);
		//request.setAttribute("nowPage", nowPage);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("board_view.jsp");
		dispatcher.forward(request, response);*/
		/*PrintWriter out = response.getWriter();
		
		if(dto != null) { // 로그인 성공
			out.println("<script>");
			out.println("alert('등록 성공')");
			out.println("location.href='board_view.jsp'");
			out.println("</script>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		} else  { // 비밀번호가 안 맞음.
			out.println("<script>");
			out.println("alert('등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		} */
		
	}

}
