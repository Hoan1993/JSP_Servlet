package com.sist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.model.BoardDAO;
import com.sist.model.BoardDTO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "";
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 게시물 입력폼 창에서 넘어온 데이터를 처리해 주자.
		String board_writer = request.getParameter("writer").trim();
		String board_title  = request.getParameter("title").trim();
		String board_content  = request.getParameter("content").trim();
		String board_pwd  = request.getParameter("pwd").trim();
		
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_board_pwd(board_pwd);
		
		
		BoardDAO dao = new BoardDAO();
		int result = dao.insertToBoard(dto);
		
		if(result == 1) {
			url = "select.do";	
		} else {
			url = "insert.do";	
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
/*		
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			response.sendRedirect("select.do");
		} else {
			out.println("<script>");
			out.println("alert('레코드추가 실패!!!')");
			out.println("history.back()");
			out.println("</script>");
			response.sendRedirect("insert.do");
		}
		*/
		
		//doGet(request, response);
	}



}
