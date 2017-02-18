package com.udelblue.core.workflow.domain;

public class ItemInput {
	
	
	public ItemInput(String current_user, String comment) {
		super();
		this.current_user = current_user;
		this.comment = comment;
	}
	
	
	String current_user;

	String comment;

	
	
	public String getCurrent_user() {
		if(current_user == null) return "";
		return current_user;
	}
	public void setCurrent_user(String current_user) {
		this.current_user = current_user;
	}
	public String getCommment() {
		if(comment == null){return "";}
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
