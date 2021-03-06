package com.board.model;

public class BoardDTO {
	private int board_no;
	private String board_writer;
	private String board_title;
	private String board_cont;
	private String board_board_pwd;
	private int board_hit;
	private String board_regdate;
	
	
	public int getBoard_no() {
	
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_cont() {
		return board_cont;
	}
	public void setBoard_cont(String board_cont) {
		this.board_cont = board_cont;
	}
	public String getBoard_board_pwd() {
		return board_board_pwd;
	}
	public void setBoard_board_pwd(String board_board_pwd) {
		this.board_board_pwd = board_board_pwd;
	}
	public int getBoard_hit() {
		return board_hit;
	}
	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}
	public String getBoard_regdate() {
		return board_regdate;
	}
	public void setBoard_regdate(String board_regdate) {
		this.board_regdate = board_regdate;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [board_no=" + board_no + ", board_writer=" + board_writer + ", board_title=" + board_title
				+ ", board_cont=" + board_cont + ", board_board_pwd=" + board_board_pwd + ", board_hit=" + board_hit
				+ ", board_regdate=" + board_regdate + "]";
	}
	
	
}
