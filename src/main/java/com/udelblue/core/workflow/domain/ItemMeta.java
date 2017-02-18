package com.udelblue.core.workflow.domain;

import java.util.ArrayList;

public class ItemMeta {



    
    
	public ItemMeta(String id, String created_by, ArrayList<String> currently_assigned_to,
			int current_stage_order_number) {
		super();
		this.id = id;
		this.created_by = created_by;
		this.currently_assigned_to = currently_assigned_to;
		this.current_stage_order_number = current_stage_order_number;
	}


	public ItemMeta(String id, String title, ArrayList<String> currently_assigned_to, String status, String type,
			String current_stage, int version, int current_stage_order_number) {
		super();
		this.id = id;
		this.title = title;
		this.currently_assigned_to = currently_assigned_to;
		this.status = status;
		this.type = type;
		this.current_stage = current_stage;
		this.version = version;
		this.current_stage_order_number = current_stage_order_number;
	}


	public ItemMeta(String id, String title, String created_by, ArrayList<String> currently_assigned_to, String status,
			String type, String current_stage, int version, int current_stage_order_number) {
		super();
		this.id = id;
		this.title = title;
		this.created_by = created_by;
		this.currently_assigned_to = currently_assigned_to;
		this.status = status;
		this.type = type;
		this.current_stage = current_stage;
		this.version = version;
		this.current_stage_order_number = current_stage_order_number;
	}



	public ItemMeta(String id, String created_by, int current_stage_order_number) {
		super();
		this.id = id;
		this.created_by = created_by;
		this.current_stage_order_number = current_stage_order_number;
	}








	String id;
    String title;
    String created_by;
	ArrayList<String> currently_assigned_to;
    String status;
    String type;
    String current_stage;
    int version;
    int current_stage_order_number;

    public String getCreated_by() {
		if(created_by == null) return "";
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
    
    
    public int getCurrent_stage_order_number() {
		return current_stage_order_number;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
	
		this.version = version;
	}

	public void setCurrent_stage_order_number(int current_stage_order_number) {
		this.current_stage_order_number = current_stage_order_number;
	}

	public String getCurrent_stage() {
		if(current_stage == null) return "";
        return current_stage;
    }

    public void setCurrent_stage(String current_stage) {
        this.current_stage = current_stage;
    }

    public String getId() {
    	if(id == null) return "";
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<String> getCurrently_assigned_to() {
    	if(currently_assigned_to == null){return new ArrayList();}
        return currently_assigned_to;
    }

    public void setCurrently_assigned_to(ArrayList<String> currently_assigned_to) {
        this.currently_assigned_to = currently_assigned_to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
    	if(type == title) return "";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    
}






