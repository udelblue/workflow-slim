package com.udelblue.core.workflow.domain;

import java.util.ArrayList;
import java.util.List;

public class Stage {


	public Stage(String name, int order_number, List<String> users_at_stage) {
		super();
		this.name = name;
		this.order_number = order_number;
		this.users_at_stage = (ArrayList<String>) users_at_stage;
	}
	String name;
	int order_number;
	ArrayList<String> users_at_stage;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public ArrayList<String> getUsers_at_stage() {
		return users_at_stage;
	}
	public void setUsers_at_stage(ArrayList<String> users_at_stage) {
		this.users_at_stage = users_at_stage;
	}
	
}
