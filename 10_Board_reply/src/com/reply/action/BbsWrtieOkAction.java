package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;



public class BbsWrtieOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String url = "board_write.do";
		
		String board_writer = request.getParameter("writer");
		String board_title = request.getParameter("title");
		String board_cont = request.getParameter("content");
		String board_pwd = request.getParameter("pwd");
		
		
		BbsDTO dto = new BbsDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);

		BbsDAO dao = BbsDAO.getInstance();
		int result = dao.insertRecord(dto);
		
		//request.setAttribute("result", result);
		HttpSession session = request.getSession();
		session.setAttribute("joinResult", result);
		session.setAttribute("joinFail", result);
		/*PrintWriter out;*/
		/*try {
			out = response.getWriter();
			if(result == 1) {
				out.println("<script>");
				out.println("alert('게시글 추가 성공~~~')");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시글 추가 실패~~~')");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String path = "board_write.do";
		
		if(result == 1) {
			path = "board_list.do";
		}
		// viewPage로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(path);
		
		return forward;
	}
	
}
