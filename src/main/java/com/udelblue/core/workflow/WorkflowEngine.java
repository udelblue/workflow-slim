package com.udelblue.core.workflow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.udelblue.core.workflow.actions.Action;
import com.udelblue.core.workflow.actions.AdvanceCustomAction;
import com.udelblue.core.workflow.actions.CustomAction;
import com.udelblue.core.workflow.actions.PostExecutionAction;
import com.udelblue.core.workflow.domain.History;
import com.udelblue.core.workflow.domain.ItemInput;
import com.udelblue.core.workflow.domain.ItemMeta;
import com.udelblue.core.workflow.domain.Notification;
import com.udelblue.core.workflow.domain.Stage;
import com.udelblue.core.workflow.exceptions.WorkflowException;

public class WorkflowEngine {

	private static final Logger logger = Logger.getLogger(WorkflowEngine.class);
	
	
	
	//custom process
	public WorkflowResult Process( ItemMeta item, List<Stage> stages, CustomAction action) throws WorkflowException{
		ItemInput ii = new ItemInput("","");
		return this.Process(ii , item, stages, action);

	}
	public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, CustomAction action) throws WorkflowException{
		
		List<History> history = new ArrayList<History>();
		List<Notification> notifactions = new ArrayList<Notification>(); 
		 init_validate(item ,stages);
		 stages = stages.stream().filter(s -> s != null).collect(Collectors.toList());
		 if( action  == null   ){ throw new WorkflowException("action is null");}
		 action.execute();
		 
		 History h = new History(item.getId(),item.getType(),"Custom Action", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage());
		 history.add(h);
		 
		 notifactions.addAll(this.notificationBuilder("Custom Action" , input.getCurrent_user() , item));
		 logger.info("Custom action executed : id " + item.getId());
		 return new WorkflowResult(item ,history,notifactions); 
	}
	
	//custom advance process
	public WorkflowResult Process( ItemMeta item, List<Stage> stages, AdvanceCustomAction action) throws WorkflowException{
		return this.Process(new ItemInput("",""),item, stages, action);
	}
	public WorkflowResult Process(  ItemInput input , ItemMeta item, List<Stage> stages, AdvanceCustomAction action) throws WorkflowException{
		 
		
		 WorkflowResult wr0 = new WorkflowResult();
		init_validate(item,stages);
		 stages = stages.stream().filter(s -> s != null).collect(Collectors.toList());
		 if( action  == null   ){ throw new WorkflowException("action is null");}
		 WorkflowResult wrr = action.execute(item,stages);
		 WorkflowResult wr = wrr == null ? wr0 : wrr;
		 if(wr.getItem() == null) {wr.setItem(item);} 
		 if(wr.getHistory() == null) {
			 ArrayList<History> h = new ArrayList<History>();
			 wr.setHistory(h); }
		 if(wr.getNotifications() == null) {
			 ArrayList<Notification> n = new ArrayList<Notification>();
			 wr.setNotifications(n); 
			 }
		 History h = new History(item.getId(),item.getType(),"Advance Custom Action", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage()); 
		 wr.history.add(h);
		 wr.notifications.addAll(this.notificationBuilder("Advance Custom Action" , input.getCurrent_user() , item));
		 logger.info("Advanced custom action executed : id " + item.getId());
		return wr;
	}
	
	
	//reassign
	public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, Action action, String reassigned_to ) throws WorkflowException{
		 return this.Process(input, item, stages, action, null , null, reassigned_to, null );	
		}
	
	//reassign post execution
	public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, Action action, String reassigned_to , PostExecutionAction postAction ) throws WorkflowException{
		 return this.Process(input, item, stages, action, null , null, reassigned_to,  postAction);	
		}
	
	
	//assign task
	public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, Action action, List<String> task_assignees ) throws WorkflowException{
		 return this.Process(input, item, stages, action, null , task_assignees , null, null);	
		}
	
	//assign task post execution
	public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, Action action, List<String> task_assignees , PostExecutionAction postAction ) throws WorkflowException{
		 return this.Process(input, item, stages, action, null , task_assignees , null, postAction);	
		}
	
	
	
	
	
	//change request
	public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, Action action,Stage change_request_stage ) throws WorkflowException{
		 return this.Process(input, item, stages, action, change_request_stage, null, null, null);	
		}
	
	
	
	//change request post execution 
	public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, Action action,Stage change_request_stage , PostExecutionAction postAction) throws WorkflowException{
		 return this.Process(input, item, stages, action, change_request_stage, null, null , postAction );	
		}
	
	
	
	
	
	//with input process
	public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, Action action) throws WorkflowException{
		 return this.Process(input, item, stages, action, null, null, null, null);	
		}
	
	//with input process post execution
		public WorkflowResult Process( ItemInput input, ItemMeta item, List<Stage> stages, Action action , PostExecutionAction postAction) throws WorkflowException{
			 return this.Process(input, item, stages, action, null, null, null , postAction );
			}
	
	
	
	//vanilla process
	public WorkflowResult Process( ItemMeta item, List<Stage> stages, Action action) throws WorkflowException{
	 return this.Process(new ItemInput("",""), item, stages, action);	
	}
	@SuppressWarnings("null")
	public WorkflowResult Process(ItemInput input, ItemMeta item, List<Stage> stages, Action action, Stage change_request_stage , List<String> task_assignees, String reassigned_to,  PostExecutionAction postAction ) throws WorkflowException{
	
			
		
		 init_validate(item,stages);
		 stages = stages.stream().filter(s -> s != null).collect(Collectors.toList());
		 
		 if( action  == null   ){ throw new WorkflowException("action is null");}
		WorkflowResult wr;
		
		switch(action) {
		    case Approve:
		    	 wr = approve(input, item,stages,action);
		    	 logger.info("Aprroval executed : id " + item.getId());
		    	 if(postAction != null){
		    		 postAction.execute(item);
		    		 logger.info("Post Execution Event Executed : id " + item.getId());
		    	 } 
		        break;
		    case  Reject:
		    	 wr = reject(input,item,stages,action);
		    	 wr.notifications.addAll(this.notificationBuilder("Rejected" , input.getCurrent_user(), item));
		    	 logger.info("Rejection executed : id " + item.getId());
		    	 if(postAction != null){
		    		 postAction.execute(item);
		    		 logger.info("Post Execution Event Executed : id " + item.getId());
		    	 } 
		        break;
		    case  Change_Request:
		    	//todo will need change request stage 
		    	 wr = change_request(input, item,stages,action , change_request_stage);
		    	 wr.notifications.addAll(this.notificationBuilder("Change Request" , input.getCurrent_user(), item));
		    	 logger.info("Change Request : id " + item.getId());
		    	 if(postAction != null){
		    		 postAction.execute(item);
		    		 logger.info("Post Execution Event Executed : id " + item.getId());
		    	 } 
		        break;
		    case  Assign_Task:
		    	//todo will need change request stage 
		    	 wr = assign_task(input, item,stages,action , task_assignees);
		    	 wr.notifications.addAll(this.notificationBuilder("Assigned Task" , input.getCurrent_user(), item));
		    	 logger.info("Assigned Task : id " + item.getId());
		    	 if(postAction != null){
		    		 postAction.execute(item);
		    		 logger.info("Post Execution Event Executed : id " + item.getId());
		    	 } 
		        break;   
		    case  Reassign:
		    	 wr = reassign(input ,item,stages,action,reassigned_to );
		    	 wr.notifications.addAll(this.notificationBuilder("Reassigned" , input.getCurrent_user(), item));
		    	 if(reassigned_to == null && reassigned_to.equals("")) {throw new WorkflowException("Reassigned can not be empty or null");}
		    	 Notification n = new Notification(input.getCurrent_user() ,"Reassigned",item.getId(),item.getStatus(), "item reassigned");
		    	 wr.notifications.add(n);
		    	 logger.info("Reassigned : id " + item.getId());
		    	 if(postAction != null){
		    		 postAction.execute(item);
		    		 logger.info("Post Execution Event Executed : id " + item.getId());
		    	 } 
		        break; 
		    case  Cancel:
		    	 wr = cancel(input ,item,stages,action);
		    	 wr.notifications.addAll(this.notificationBuilder("Cancelled" , input.getCurrent_user(), item));
		    	 logger.info("Cancelled : id " + item.getId());
		    	 if(postAction != null){
		    		 postAction.execute(item);
		    		 logger.info("Post Execution Event Executed : id " + item.getId());
		    	 } 
		        break;
		    default: 
		    	throw new WorkflowException("unknown action type");    
				}
		
			return wr;
	}
	
	

	
	//reassigned action
	@SuppressWarnings("null")
	private WorkflowResult reassign(ItemInput input, ItemMeta item, List<Stage> stages, Action action, String reassigned_to) throws WorkflowException {
		List<History> history = new ArrayList<History>();
		List<Notification> notifactions = new ArrayList<Notification>();
		List<String> current = item.getCurrently_assigned_to();
		if(reassigned_to == null && reassigned_to.equals("")) {throw new WorkflowException("Reassigned can not be empty or null");}
		current.add(reassigned_to);
		
		 History h = new History(item.getId(),item.getType(),"Reassigned ", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage());
		 history.add(h);
	
		return new WorkflowResult(item,history,notifactions);
	}
	// approve action
	private WorkflowResult approve( ItemInput input, ItemMeta item, List<Stage> stages, Action action  ){
		WorkflowResult wr = new WorkflowResult();
		List<History> history = new ArrayList<History>();
		List<Notification> notifactions = new ArrayList<Notification>();
		
		final Comparator<Stage> comp = (p1, p2) -> Integer.compare( p1.getOrder_number(), p2.getOrder_number());
		
		
		List<Stage> future_stages = stages.stream().filter(s -> s.getOrder_number() > item.getCurrent_stage_order_number()).collect(Collectors.toList());
		
		
		//workflow completed
		if(future_stages.isEmpty())
		{
			item.setCurrent_stage_order_number(999999999); ;
			item.setCurrent_stage("Completed"); 
			item.setStatus("Completed");
			
			 String createdby  = item.getCreated_by();
			//created by 
			 if(createdby != null && !createdby.equals(""))
			 {
			 Notification n = new Notification(createdby , "Completed" ,item.getId(),item.getStatus(), "requestor");
			 notifactions.add(n);
			 }
			 
			 //currently assigned users
			 
			 if(item.getCurrently_assigned_to() != null && !item.getCurrently_assigned_to().isEmpty())
			 {
				 item.getCurrently_assigned_to().forEach(u -> {
					 if(u != null && !u.equals("")){
					 Notification n = new Notification(u ,"Completed",item.getId(),item.getStatus(), "currently assigned");
					 notifactions.add(n);
					 }
				 });
			 }
			
			 // current user
			 if(input.getCurrent_user() != null && !input.getCurrent_user().equals(""))
    		 {
    		 Notification n = new Notification(input.getCurrent_user(),"Completed",item.getId(),item.getStatus(), "executed event");
    		 notifactions.add(n);
    		 }
			 
			 item.setCurrently_assigned_to(new ArrayList<String>());
			 History h = new History(item.getId(),item.getType(),"Completed", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage());
			 history.add(h);
			
			
			
		
			
		}
		// next stage
		else{
			
			// add notify for previous stage(now current stage)
			 if(item.getCurrently_assigned_to() != null && !item.getCurrently_assigned_to().isEmpty())
			 {
				 item.getCurrently_assigned_to().forEach(u -> {
					 if(u != null && !u.equals("")){
					 Notification n = new Notification(u ,"Approved", item.getId(), item.getStatus(), "previously assigned");
					 notifactions.add(n);
					 }
				 });
			 }	
			 //add history
			 
			
			//get next stage 
			
			 future_stages.stream().sorted(comp);
			 Stage currentstage =  future_stages.get(0);
			 item.setCurrently_assigned_to(currentstage.getUsers_at_stage());
			 item.setCurrent_stage_order_number(currentstage.getOrder_number());
			 item.setStatus("In Process");
			 
			
			 //notifications
			 notifactions.addAll( this.notificationBuilder("Approved" , input.getCurrent_user(), item));
			 //add history 
			 History h = new History(item.getId(),item.getType(),"Approved", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage());
			 history.add(h);
			 
			 
		}
		wr.setItem(item);
		wr.setNotifications(notifactions);
		wr.setHistory(history);
		return wr;
	}
	
	// reject action
	private WorkflowResult reject(ItemInput input, ItemMeta item, List<Stage> stages, Action action  ){
		List<History> history = new ArrayList<History>();
		List<Notification> notifactions = new ArrayList<Notification>();
		item.setStatus("Rejected");
		item.setCurrent_stage_order_number( 999999998);
		item.setCurrently_assigned_to(new ArrayList<String>());

		 History h = new History(item.getId(),item.getType(),"Rejected", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage());
		 history.add(h);
	
		return new WorkflowResult(item,history,notifactions);
	}
	
	
	// cancel action
	private WorkflowResult cancel(ItemInput input, ItemMeta item, List<Stage> stages, Action action  ){
		List<History> history = new ArrayList<History>();
		List<Notification> notifactions = new ArrayList<Notification>();
		item.setStatus("Cancelled");
		item.setCurrent_stage_order_number(999999997);
		item.setCurrently_assigned_to( new ArrayList<String>());
		History h = new History(item.getId(),item.getType(),"Cancelled", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage());
		 history.add(h);
		return new WorkflowResult(item,history,notifactions);
	}
	
	
	// change request action
	private WorkflowResult change_request(ItemInput input, ItemMeta item, List<Stage> stages, Action action, Stage change_request_stage  ) throws WorkflowException{
		List<History> history = new ArrayList<History>();
		List<Notification> notifactions = new ArrayList<Notification>();
		if(change_request_stage  == null) {throw new WorkflowException("Change request stage is null");}
		
		
		 List<Stage> crs = stages.stream().filter(s -> s.getOrder_number() == change_request_stage.getOrder_number()).collect(Collectors.toList());
		if(crs.isEmpty()) {throw new WorkflowException("No change request stage is found within proved stages");}
		
		 Stage s = crs.get(0);
		 
		 
		 s.getUsers_at_stage().forEach(u -> {
			 if(u != null && !u.equals("")){
			 Notification n = new Notification(u ,"Change Request",item.getId(),item.getStatus(), "change request assigned");
			 notifactions.add(n);
			 }
		 });
		 
		History h = new History(item.getId(),item.getType(),"Change Request", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage());
		 history.add(h);
		return new WorkflowResult(item,history,notifactions);
	}
	
	
	//assign task
	private WorkflowResult assign_task(ItemInput input, ItemMeta item, List<Stage> stages, Action action, List<String> task_assignees) throws WorkflowException {
		List<History> history = new ArrayList<History>();
		List<Notification> notifactions = new ArrayList<Notification>();
		if(task_assignees  == null) {throw new WorkflowException("Task assignees is null");}
		if(task_assignees.isEmpty()) {throw new WorkflowException("Task assignees is empty");}
		int assignees_count = 0;
		for(String u :  task_assignees)
		{
			 if(u != null && !u.equals("")){
				 assignees_count = assignees_count + 1 ;
			 Notification n = new Notification(u ,"Assigned Task",item.getId(),item.getStatus(), "task assigned");
			 notifactions.add(n);
			 }	
		}
		History h = new History(item.getId(),item.getType(),"Assigned Task", input.getCurrent_user() , input.getCommment() , new Date(), item.getVersion(), item.getCurrent_stage());
		history.add(h);
		return new WorkflowResult(item,history,notifactions);
	}
	
	//notification builder
	private List<Notification> notificationBuilder(String event, String current_user, ItemMeta item  )
	{
		List<Notification> notifactions = new ArrayList<Notification>(); 
		
		//notify current user 
		 if(current_user != null && !current_user.equals(""))
		 {
		 Notification n = new Notification(current_user,event,item.getId(),item.getStatus(), "executed event");
		 notifactions.add(n);
		 }
		
		 String createdby  = item.getCreated_by();
		 
		//created by 
		 if(createdby != null && !createdby.equals(""))
		 {
		 Notification n = new Notification(createdby ,event,item.getId(),item.getStatus(), "requestor");
		 notifactions.add(n);
		 }
		 
		 //currently assigned users
		 
		 if(item.getCurrently_assigned_to() != null && !item.getCurrently_assigned_to().isEmpty())
		 {
			 item.getCurrently_assigned_to().forEach(u -> {
				 if(u != null && !u.equals("")){
				 Notification n = new Notification(u ,event,item.getId(),item.getStatus(), "currently assigned");
				 notifactions.add(n);
				 }
			 });
		 }
		 
		 
		 return notifactions;
	}
	
	//validation function
	private void init_validate(ItemMeta item, List<Stage> stages ) throws WorkflowException{
		 final Comparator<Stage> comp = (p1, p2) -> Integer.compare( p1.getOrder_number(), p2.getOrder_number());
		if( stages  == null | item  == null  ){ throw new WorkflowException("item or stages are null");}
		if(item.getStatus() != null)
		{
			if(item.getStatus().toLowerCase().equals("completed")){ throw new WorkflowException("item has completed status");}
		}
		if(stages.isEmpty()){ throw new WorkflowException("stages are empty");}
		   stages.sort(comp);
		   Stage highest_stage = stages.stream().max(comp).get();
		if(item.getCurrent_stage_order_number() > highest_stage.getOrder_number()){ throw new WorkflowException("highest level stages exceeds current item stage");}
		
	}
	

	
	
}
