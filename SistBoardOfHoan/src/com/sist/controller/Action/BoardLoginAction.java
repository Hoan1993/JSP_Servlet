package com.sist.controller.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

public class BoardLoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/login2.jsp";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.loginProcess(id, pwd);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			MemberVO mVo = new MemberVO();
			mVo = mDao.getMember(id);
			session.setAttribute("loginUser", mVo);
			request.setAttribute("message", "로그인 성공");
			request.setAttribute("mVo", mVo);
			url = "BoardServlet?command=board_list&page=1";
		} else {
			request.setAttribute("message", "로그인에 실패했습니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
		
	}
	
}
