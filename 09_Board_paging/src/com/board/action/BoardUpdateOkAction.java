package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;



public class BoardUpdateOkAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board_update.jsp";
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		String board_title = request.getParameter("title");
		String board_content = request.getParameter("content");
		String board_pwd = request.getParameter("pwd");
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board_no);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_board_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		request.setAttribute("board_no", board_no);
		request.setAttribute("nowPage", nowPage);
		
		// 비밀번호까지 확인
		int result =dao.update(dto);
		
		PrintWriter out = response.getWriter();
		
		if(result == 1) { // 업데이트 성공
			out.println("<script>");
			out.println("alert('업데이트 성공')");
			out.println("location.href='board_list.do?page=${nowPage}'");
			out.println("</script>");
			/*RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);*/
		} else  { // 비밀번호가 안 맞음.
			out.println("<script>");
			out.println("alert('업데이트 실패')");
			out.println("history.back()");
			out.println("</script>");
		} 
		
	}
		
}


