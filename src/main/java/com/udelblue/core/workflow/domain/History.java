package com.udelblue.core.workflow.domain;

import java.util.Date;

public class History {

    public History(String item_id, String item_type, String event, String event_user, String event_comment,
			Date event_date, int item_version, String stage) {
		super();
		this.item_id =  item_id == null ? "" : item_id;
		
		this.item_type = item_type == null ? "" : item_type;
		this.event = event == null ? "" :  event;
		this.event_user =  event_user == null ? "" : event_user;
		this.event_comment =  event_comment == null ? "" : event_comment;
		this.event_date = event_date;
		this.item_version = item_version;
		this.stage = stage == null ? "" : stage;
	}

    int stage_ordernum;

    
    
    
	public int getStage_ordernum() {
		return stage_ordernum;
	}

	public void setStage_ordernum(int stage_ordernum) {
		this.stage_ordernum = stage_ordernum;
	}

	String item_id;
    String item_type;
    String event;
    String event_user;
    String event_comment;
    Date event_date;
    int item_version;
    String stage;


    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent_user() {
        return event_user;
    }

    public void setEvent_user(String event_user) {
        this.event_user = event_user;
    }

    public String getEvent_comment() {
        return event_comment;
    }

    public void setEvent_comment(String event_comment) {
        this.event_comment = event_comment;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public int getItem_version() {
        return item_version;
    }

    public void setItem_version(int item_version) {
        this.item_version = item_version;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }


}
