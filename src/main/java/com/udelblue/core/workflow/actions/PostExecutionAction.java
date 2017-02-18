package com.udelblue.core.workflow.actions;

import com.udelblue.core.workflow.domain.ItemMeta;

public interface PostExecutionAction {

	
	
	public void execute(ItemMeta item);
	
}
