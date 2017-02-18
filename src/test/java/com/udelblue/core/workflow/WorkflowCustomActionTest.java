package com.udelblue.core.workflow;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.udelblue.core.workflow.actions.CustomAction;
import com.udelblue.core.workflow.domain.ItemInput;
import com.udelblue.core.workflow.domain.ItemMeta;
import com.udelblue.core.workflow.domain.Stage;
import com.udelblue.core.workflow.exceptions.WorkflowException;

/**
 * Unit test for Workflow
 */
public class WorkflowCustomActionTest 
{

    /**
     * Test Custom Action 
     * @throws WorkflowException 
     */
    @Test()
    public void TestCustomAction() throws WorkflowException
    {
    	CustomAction ca = new TestCustomAction();
    	
    	
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =  WorkflowTestHelperFunctions.getStages(3);
    	ItemMeta item = new ItemMeta("12345","TomTester@test.com",2);
    	ItemInput input= new ItemInput("","");
    	WorkflowResult wr = engine.Process(input, item, stages, ca);
    	assertTrue(!wr.getNotifications().isEmpty());
    	assertTrue(!wr.getHistory().isEmpty());
    }

    
    //custom test custom action
    private class TestCustomAction implements CustomAction{

		@Override
		public void execute() {
			System.out.println("-- Custom Action Executed --");
		}
	
    }
    
    



    
}
