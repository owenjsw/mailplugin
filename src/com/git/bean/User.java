package com.git.bean;

import java.sql.Timestamp;





public class User {
	private String user;
	private String email;
	private String activeCode;
	private Timestamp dateUpdate;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public Timestamp getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Timestamp dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	
	
}
