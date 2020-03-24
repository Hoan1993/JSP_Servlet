package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// 답변글 폼에서 넘어온 데이터를 db에 저장하는 클래스
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int board_group = Integer.parseInt(request.getParameter("board_group"));
		int board_step = Integer.parseInt(request.getParameter("board_step"));
		int board_indent = Integer.parseInt(request.getParameter("board_indent"));
		
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("cont").trim();
		String board_pwd = request.getParameter("pwd").trim();
		
		
		
		BbsDTO dto = new BbsDTO();
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		dto.setBoard_group(board_group);
		dto.setBoard_step(board_step);
		dto.setBoard_indent(board_indent);
		
		BbsDAO dao = BbsDAO.getInstance();
		dao.replyUpdate(board_group, board_step);
		int result = dao.replyBoard(dto);
		
		ActionForward forward = new ActionForward();
		
		if(result > 0) { // 답변글이 정상적으로 db에 등록이 된 경우
			forward.setRedirect(true);
			forward.setPath("board_list.do");
		} else {
				PrintWriter out;
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글 답변 등록 실패!!')");
				out.println("history.back()");
				out.println("</script>");
					
		}
		
		return forward;
	}

}
