package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteOkAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board_cont.do";
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.delete(board_no);
		
		if(result == 1) {
			url = "board_list.do";
		} 
		
		return url;
	}
	
}
