package com.udelblue.core.workflow;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.udelblue.core.workflow.actions.AdvanceCustomAction;
import com.udelblue.core.workflow.domain.ItemInput;
import com.udelblue.core.workflow.domain.ItemMeta;
import com.udelblue.core.workflow.domain.Stage;
import com.udelblue.core.workflow.exceptions.WorkflowException;

/**
 * Unit test for Workflow
 */
public class WorkflowCustomAdvancedActionTest 
{

    /**
     * Test Custom Action 
     * @throws WorkflowException 
     */
    @Test()
    public void TestCustomAdvancedAction() throws WorkflowException
    {
    	AdvanceCustomAction ca = new TestCustomAction();
    	
    	
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =  WorkflowTestHelperFunctions.getStages(3);
    	ItemInput input= new ItemInput("","");
    	ItemMeta item = new ItemMeta("12345","TomTester@test.com",2);
    	WorkflowResult wr = engine.Process(input ,item, stages, ca);
    	assertNotNull(wr.getItem());
    }

    
    //custom test custom action
    private class TestCustomAction implements AdvanceCustomAction{

		@Override
		public WorkflowResult execute(ItemMeta item, List<Stage> stages) {
			System.out.println("-- Advance Custom Action Executed --");
			return null;
		}
    }
    
    



    
}
