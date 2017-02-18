package com.udelblue.core.workflow.domain;

public class Notification {

	




	String user_name;
	String event;
	String item_id;
	String item_status;
	String type;
	
	public Notification(String user_name, String event, String item_id, String item_status, String type) {
		super();
		this.user_name = user_name;
		this.event = event;
		this.item_id = item_id;
		this.item_status = item_status;
		this.type = type;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_status() {
		return item_status;
	}
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}
	
	
	
}
