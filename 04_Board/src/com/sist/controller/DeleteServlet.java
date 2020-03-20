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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		String board_pwd = request.getParameter("pwd").trim();
		
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board_no);
		dto.setBoard_board_pwd(board_pwd);
		
		int result = dao.delete(dto);
		
	
		PrintWriter out = response.getWriter();	
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시물 삭제 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
			
		} else if(result  == 0) {
			out.println("<script>");
			out.println("alert('비밀번호 틀림')");
			out.println("history.back()");
			//out.println("location.href='update.do?no="+board_no+"'");
			out.println("</script>");
			
		} else {
			out.println("<script>");
			out.println("alert('게시물 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	
		

		
		
	}

}
