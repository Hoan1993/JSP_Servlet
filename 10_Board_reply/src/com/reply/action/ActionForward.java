package com.reply.action;

/*
 * ActionForward 클래스 
 * 1. Aciton 인터페이스에서 리턴 타입으로 사용될 클래스.
 * 2. isRedirect() 메서드 
 * 		- jsp 페이지(false 값이면 jsp페이지로) 
 * 		- *.do 페이지 (.do값이 들어오면 true)
 * 3. path() 메서드 : 파일 경로 지정.
 * 
 */


public class ActionForward {
	
	private boolean isRedirect;
	private String path;
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	
	
}
