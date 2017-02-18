package com.udelblue.core.workflow;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.udelblue.core.workflow.actions.Action;
import com.udelblue.core.workflow.domain.ItemInput;
import com.udelblue.core.workflow.domain.ItemMeta;
import com.udelblue.core.workflow.domain.Stage;
import com.udelblue.core.workflow.exceptions.WorkflowException;


/**
 * Unit test for Workflow
 */
public class WorkflowMainTest 
{


	
    /**
     * Test Approve 
     * @throws WorkflowException 
     */
    @Test()
    public void TestApprove() throws WorkflowException
    {
    	int current_stage_number = 2;
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =  WorkflowTestHelperFunctions.getStages(3);
    	ItemMeta item = new ItemMeta("12345","TomTester@test.com",current_stage_number);
    	
    	WorkflowResult wr = engine.Process(item, stages, Action.Approve);
    	assertNotNull("Workflow item is null ", wr.getItem());
    	assertTrue("Currently assigned to someone", !wr.getItem().getCurrently_assigned_to().isEmpty());
    	assertTrue("Stage numbers not matching", wr.getItem().getCurrent_stage_order_number()  == (current_stage_number + 1));
    	assertTrue(!wr.getNotifications().isEmpty());
    	assertTrue(!wr.getHistory().isEmpty());
    }
	
	
	
	
    /**
     * Test Reject 
     * @throws WorkflowException 
     */
    @Test()
    public void TestReject() throws WorkflowException
    {
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =  WorkflowTestHelperFunctions.getStages(3);
    	ItemMeta item = new ItemMeta("12345","TomTester@test.com",2);
    	WorkflowResult wr = engine.Process(item, stages, Action.Reject);
    	assertTrue("Rejected is not status",wr.getItem().getStatus().toLowerCase().equals("rejected"));
    	assertTrue("Check order number value",wr.getItem().getCurrent_stage_order_number() > 9999);
    	assertTrue("Currently assigned to someone",wr.getItem().getCurrently_assigned_to().isEmpty());
    	assertTrue(!wr.getNotifications().isEmpty());
    	assertTrue(!wr.getHistory().isEmpty());
    }

    
    /**
     * Test Cancelled
     * @throws WorkflowException 
     */
    @Test()
    public void TestCancelled() throws WorkflowException
    {
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =  WorkflowTestHelperFunctions.getStages(3);
    	ItemMeta item = new ItemMeta("12345","TomTester@test.com",2);
    	WorkflowResult wr = engine.Process(item, stages, Action.Cancel);
    	assertTrue("Cancelled is not status",wr.getItem().getStatus().toLowerCase().equals("cancelled"));
    	assertTrue("Check order number value",wr.getItem().getCurrent_stage_order_number() > 9999);
    	assertTrue("Currently assigned to someone",wr.getItem().getCurrently_assigned_to().isEmpty());
    	assertTrue(!wr.getNotifications().isEmpty());
    	assertTrue(!wr.getHistory().isEmpty());
    }
    
   
    
    /**
     * Test Task Assigned
     * @throws WorkflowException 
     */
    @Test()
    public void TestAssign_Task() throws WorkflowException
    {
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =   WorkflowTestHelperFunctions.getStages(3);
    	ItemMeta item = new ItemMeta("12345","TomTester@test.com",2);
    	ItemInput input = new ItemInput("TeddyTester@test.com", "Reassigned to tracey");
    	WorkflowResult wr = engine.Process(input ,item, stages, Action.Assign_Task, Arrays.asList("AlexiaThibert@test.com", "TracyEsqueda@test.com" , "JuanGregson@test.com"));
    	assertTrue("Check order number value",wr.getItem().getCurrent_stage_order_number() == 2);
    	assertTrue(!wr.getNotifications().isEmpty());
    	assertTrue(!wr.getHistory().isEmpty());
    }
    
    /**
     * Empty Change Request
     * @throws WorkflowException 
     */
    @Test()
    public void TestEmptyChangeRequest() throws WorkflowException
    {
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =   WorkflowTestHelperFunctions.getStages(3);
    	ItemMeta item = new ItemMeta("12345","TomTester@test.com",2);
    	Stage emptystage = new Stage(null, 0, null);
    	ItemInput input = new ItemInput("TeddyTester@test.com", "Resquesting change");
    	WorkflowResult wr = engine.Process(input, item, stages, Action.Change_Request, emptystage);
    	assertTrue(!wr.getNotifications().isEmpty());
    	assertTrue(!wr.getHistory().isEmpty());

    }
    
    
    /**
     * Null ItemMeta
     * @throws WorkflowException 
     */
    @Test(expected = WorkflowException.class) 
    public void TestNullItemMeta() throws WorkflowException
    {
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =   WorkflowTestHelperFunctions.getStages(3);
    	engine.Process(null, stages, Action.Approve);

    }
    
    
    /**
     * Empty Task Assigned
     */
    @Test()
    public void TestEmptyTaskAssigned() throws WorkflowException
    {
    	WorkflowEngine engine = new WorkflowEngine();
    	List<Stage> stages =   WorkflowTestHelperFunctions.getStages(3);
    	ItemMeta item = new ItemMeta("12345","TomTester@test.com",2);
    	ItemInput input = new ItemInput("TeddyTester@test.com", "Reassigned to tracey");
    	WorkflowResult wr = engine.Process(input ,item, stages, Action.Assign_Task, Arrays.asList(""));
    	assertTrue(!wr.getNotifications().isEmpty());
    	assertTrue(!wr.getHistory().isEmpty());
    }

    
    
}
