package com.reply.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// DB의 전체 레코드를 화면으로 이동시켜서 출력시키는 클래스
		BbsDAO dao = BbsDAO.getInstance();
		List<BbsDTO> list = dao.getBbsList();
		
		request.setAttribute("list", list);
		
		/*String result = request.getParameter("result");*/
		/*request.setAttribute("joinResult", result);*/
		
		// viewPage로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_list.jsp");
	
		return forward;
	}
	
}
