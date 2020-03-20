package com.sist.controller;


import com.sist.controller.Action.Action;
import com.sist.controller.Action.BoardCheckPassAction;
import com.sist.controller.Action.BoardCheckPassFormAction;
import com.sist.controller.Action.BoardDeleteAction;
import com.sist.controller.Action.BoardListAction;
import com.sist.controller.Action.BoardLoginAction;
import com.sist.controller.Action.BoardLoginFormAction;
import com.sist.controller.Action.BoardLogoutAction;
import com.sist.controller.Action.BoardUpdateAction;
import com.sist.controller.Action.BoardUpdateFormAction;
import com.sist.controller.Action.BoardViewAction;
import com.sist.controller.Action.BoardWriteAction;
import com.sist.controller.Action.BoardWriteFormAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		super();
	}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory : "+command);
		
		
		if(command.equals("board_list")) {
			action = new BoardListAction();
		} else if(command.equals("board_view")) {
			action = new BoardViewAction();
		} else if(command.equals("board_write")) {
			action = new BoardWriteAction(); 
		} else if(command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		} else if(command.equals("board_delete")) {
			action = new BoardDeleteAction();
		} else if(command.equals("board_update")) {
			action = new BoardUpdateAction();
		} else if(command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		} else if(command.equals("board_check_pass")) {
			action = new BoardCheckPassAction(); 
		} else if(command.equals("board_check_pass_form")) {
			action = new BoardCheckPassFormAction();
		} else if(command.equals("board_loginForm")) {
			action = new BoardLoginFormAction();
		} else if(command.equals("board_login")) {
			action = new BoardLoginAction();
		} else if(command.equals("board_logout")) {
			action = new BoardLogoutAction();
		}
		
		return action;
 	}
}
