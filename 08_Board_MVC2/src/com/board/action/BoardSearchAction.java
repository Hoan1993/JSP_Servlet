package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;



public class BoardSearchAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board_search.jsp";
		
		// 1. 검색 창에서 넘어온 데이터를 처리해 주자. title과 content로 검색한다.
		String find_field = request.getParameter("find_field").trim();
		String find_name = request.getParameter("find_name").trim();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardDTO> search = dao.searchBoard2(find_field, find_name);
		
		request.setAttribute("list", search);
		
		if(search!= null) {
			url = "board_search.jsp";
		} 
		
		
		return url;
	}

}
