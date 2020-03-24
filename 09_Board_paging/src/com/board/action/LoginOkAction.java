package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.model.MemberDAO;
import com.board.model.MemberDTO;

public class LoginOkAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인폼 페이지에서 넘어온 아이디와 비밀번호가 DB에 있는지 여부 체크하는 컨트롤러
				
		String member_id= request.getParameter("id").trim();		
		String member_pwd= request.getParameter("pwd").trim();		

		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.userCheck(member_id, member_pwd);
		
		PrintWriter out = response.getWriter();
		MemberDTO dto = null;
	
		if(result == 1) { // 로그인 성공
			dto = new MemberDTO();
			dto = dao.getMember(member_id);
			HttpSession session = request.getSession();
			//session.setAttribute("dto", dto);
			session.setAttribute("id", dto.getMember_id());
			session.setAttribute("name", dto.getMember_name());
			session.setAttribute("nickname", dto.getMember_nickname());
			
			out.println("<script>");
			out.println("alert('로그인 성공~~~')");
			out.println("location.href='main.jsp'");
			out.println("</script>");
			/*RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);*/
		} else if(result == -1) { // 비밀번호가 안 맞음.
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다~~~')");
			out.println("history.back()");
			out.println("</script>");
		} else {	// 그런 회원 없거나, 휴면 회원
			out.println("<script>");
			out.println("alert('가입되지 않은 회원입니다~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	
	
	}
	
}
