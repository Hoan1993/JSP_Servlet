package com.reply.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsContAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		BbsDAO dao = BbsDAO.getInstance();
		BbsDTO dto = dao.selectOneRecordByNum(board_no);
		dao.boardHit(board_no);
		
		request.setAttribute("dto", dto);
		// viewPage로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_cont.jsp");
	
		return forward;
	}
	
}
