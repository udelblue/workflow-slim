package com.udelblue.core.workflow.actions;

import java.util.List;

import com.udelblue.core.workflow.WorkflowResult;
import com.udelblue.core.workflow.domain.ItemMeta;
import com.udelblue.core.workflow.domain.Stage;

public interface AdvanceCustomAction {

	public WorkflowResult execute(ItemMeta item,  List<Stage> stages);
	
	
}
