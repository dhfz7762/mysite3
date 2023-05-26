package com.javaex.vo;


public class BoardVo {
	
	private int no;
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private int user_no;
	private String username;
	
	public BoardVo() {
		super();
	}
	public BoardVo(String title, String content, int hit, int user_no) {
		super();
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.user_no = user_no;
	}
	public BoardVo(int no, String title, String content, int hit, String regDate, int user_no) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.user_no = user_no;
	}
	
	public BoardVo(int no, String title, String content, int hit, String regDate, int user_no, String username) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.user_no = user_no;
		this.username = username;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ regDate + ", user_no=" + user_no + ", username=" + username + "]";
	}
	
}
