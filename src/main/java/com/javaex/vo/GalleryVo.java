package com.javaex.vo;

public class GalleryVo {
	private int no;
	private int user_no;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private int fileSize;
	private String username;
	
	public GalleryVo() {
		super();
	}

	public GalleryVo(int no) {
		super();
		this.no = no;
	}

	public GalleryVo(int user_no, String content, String filePath, String orgName, String saveName, int fileSize) {
		super();
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}


	public GalleryVo(int no, int user_no, String content, String filePath, String orgName, String saveName,
			int fileSize, String username) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.username = username;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", user_no=" + user_no + ", content=" + content + ", filePath=" + filePath
				+ ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize=" + fileSize + ", username="
				+ username + "]";
	}

	
	

}
