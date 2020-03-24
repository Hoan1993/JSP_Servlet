package com.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 글제목을 클릭 시 상세내용을 보여주는 클래스.
		String url = "board_list.do";
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		int board_no2 = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = BoardDAO.getInstance();
		dao.boardHit(board_no2);
		
		BoardDTO dto = dao.selectOneRecordByNum(board_no2);
		System.out.println(dto.toString());
		request.setAttribute("dto", dto);
		
		if(dto != null) {
			url = "board_cont.jsp";
		}
		
		
		
		return url;
	}


}
