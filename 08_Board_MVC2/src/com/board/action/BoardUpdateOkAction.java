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
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board_update.jsp";
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		String board_title = request.getParameter("title");
		String board_content = request.getParameter("content");
		String board_pwd = request.getParameter("pwd");
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board_no);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_board_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 비밀번호까지 확인
		int result =dao.update(dto);
		
		/*PrintWriter out = response.getWriter();*/
		
		if(result == 1) {
			url = "board_list.do";
		} /*else {
			out.println("<script>");
			out.println("alert('비밀번호 틀림')");
			out.println("history.back()");
			//out.println("location.href='update.do?no="+board_no+"'");
			out.println("</script>");
		} */
		
	
		return url;
	}

}
