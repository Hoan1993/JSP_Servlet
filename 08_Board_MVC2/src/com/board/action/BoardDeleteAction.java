package com.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board_delete.jsp";
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		
		request.setAttribute("board_no", board_no);
		
		
		return url;
	}

}
