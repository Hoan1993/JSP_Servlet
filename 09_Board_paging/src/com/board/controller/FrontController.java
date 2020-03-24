package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;
import com.board.action.LoginOkAction;

/*import com.board.action.Action;
import com.board.action.BoardContAction;
import com.board.action.BoardDeleteAction;
import com.board.action.BoardDeleteOkAction;
import com.board.action.BoardListAction;
import com.board.action.BoardSearchAction;
import com.board.action.BoardUpdateAction;
import com.board.action.BoardUpdateOkAction;
import com.board.action.BoardWriteAction;
import com.board.action.BoardWriteOkAction;*/

public class FrontController extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// getRequestURI() : "/프로젝트명/파일명(*.do)"라는 문자열을 반환하는 메서드
		String uri = request.getRequestURI();
		System.out.println("URI ==> "+uri);
		
		// getContextPath() : 현재 프로젝트명을 반환하는 메서드
		String path = request.getContextPath();
		System.out.println("path ==> "+path);
		
		String command = uri.substring(path.length()+1);
		
		System.out.println("command ==> "+command);
		Action action = null;
		String viewPage = null;
		
		if(command.equals("login.do")) {
			viewPage = "login.jsp";
			
		} else if(command.equals("loginOk.do")) { 
			action = new LoginOkAction();
			viewPage = "main.jsp";
			action.execute(request, response);
		} else if(command.equals("join.do")) {
			viewPage = "join.jsp";
		} else if(command.equals("board_list.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewPage = "board_list.jsp";
		} else if(command.equals("board_write.do")) {
			viewPage = "board_write.jsp";
			
		} else if(command.equals("board_write_ok.do")) {
			action = new BoardWriteOkAction();
			action.execute(request, response);
			viewPage = "board_list.do";	
		} else if(command.equals("board_cont.do")) {
			action = new BoardContAction();
			action.execute(request, response);
			viewPage = "board_view.jsp";
		} else if(command.equals("board_update.do")) {
			action = new BoardUpdateAction();
			action.execute(request, response);
			viewPage = "board_update.jsp";
		} else if(command.equals("board_updateOk.do")) {
			action = new BoardUpdateOkAction();
			action.execute(request, response);
			viewPage = "board_list.do";
		} else if(command.equals("board_delete.do")) {
			action = new BoardDeleteAction();
			action.execute(request, response);
			viewPage = "board_list.do";	
		} else if(command.equals("board_search.do")) {
			action = new BoardSearchAction();
			action.execute(request, response);
			viewPage = "board_search.jsp";
		} else if(command.equals("board_searchCont.do")) {
			action = new BoardSearchContAction();
			action.execute(request, response);
			viewPage = "board_searchCont.jsp";
		} else if(command.equals("zipcode_find.do")) {
			viewPage = "zipcode.jsp";	
		} else if(command.equals("zipcode_ok.do")) {
			action = new ZipCodeAction();
			action.execute(request, response);
			viewPage = "zipcode.jsp";	
		} else if(command.equals("logout.do")) {
			viewPage = "logout.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}
}

