package com.reply.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// 글번호에 해당하는 게시글을 조회하여 상세내역을 
		// 답변글 폼에 넘겨주는  클래스.
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		BbsDAO dao = BbsDAO.getInstance();
		BbsDTO dto = dao.selectOneRecordByNum(board_no);
		
		// 키로 저장하여 답변글 폼 페이지로 넘겨 주자
		request.setAttribute("reply", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_reply.jsp");
		
		
		return forward;
	}
	
}
