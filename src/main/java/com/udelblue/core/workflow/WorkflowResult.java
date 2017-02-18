package com.udelblue.core.workflow;

import java.util.List;

import com.udelblue.core.workflow.domain.History;
import com.udelblue.core.workflow.domain.ItemMeta;
import com.udelblue.core.workflow.domain.Notification;

public class WorkflowResult {

	
	
	
	
	public WorkflowResult() {
		super();
	}



	public WorkflowResult(ItemMeta item, List<History> history, List<Notification> notifications) {
		super();
		this.item = item;
		this.history = history;
		this.notifications = notifications;
	}
	
	

	public ItemMeta getItem() {
		return item;
	}
	public void setItem(ItemMeta item) {
		this.item = item;
	}
	public List<History> getHistory() {
		return history;
	}
	public void setHistory(List<History> history) {
		this.history = history;
	}
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}



	ItemMeta item;
	List<History> history;
	List<Notification> notifications;
	
}
