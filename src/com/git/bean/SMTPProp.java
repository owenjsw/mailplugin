package com.git.bean;

public class SMTPProp {
	private String host;
	private String port;
	private String username;
	private String pwd;
	private String emailtemplete;
	private String scaleFactor;
	private String readdress;
	private String center;
	
	
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getReaddress() {
		return readdress;
	}
	public void setReaddress(String readdress) {
		this.readdress = readdress;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmailtemplete() {
		return emailtemplete;
	}
	public void setEmailtemplete(String emailtemplete) {
		this.emailtemplete = emailtemplete;
	}
	public String getScaleFactor() {
		return scaleFactor;
	}
	public void setScaleFactor(String scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
}
