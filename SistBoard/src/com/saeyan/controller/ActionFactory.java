package com.saeyan.controller;

import com.saeyan.controlle.action.Action;
import com.saeyan.controlle.action.BoardDeleteAction;
import com.saeyan.controlle.action.BoardListAction;
import com.saeyan.controlle.action.BoardUpdateAction;
import com.saeyan.controlle.action.BoardUpdateFormAction;
import com.saeyan.controlle.action.BoardViewAction;
import com.saeyan.controlle.action.BoardWriteAction;
import com.saeyan.controlle.action.BoardWriteFormAction;

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
		} 
		
 		
		return action;
 	}
}
