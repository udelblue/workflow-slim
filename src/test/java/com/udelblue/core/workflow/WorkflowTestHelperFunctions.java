package com.udelblue.core.workflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.udelblue.core.workflow.domain.Stage;

public class WorkflowTestHelperFunctions {

    
    //helper function
    public static List<Stage> getStages(int numberOfStages){
    	List<Stage> stages = new ArrayList<Stage>();    	
    	for(int i = 0 ; i <= numberOfStages; i++){
    		List<String> stagenames = getRandomNames(RandomNUM());
    		Stage stage = new Stage("Stage " + Integer.toString(i)  , i , stagenames );
    		stages.add(stage);
    	}
    	return stages;
    }
    
    //helper function
    public static List<String> getRandomNames(int numberOfNames) {
    	List<String> returnnames = new ArrayList<String>(); 
      	List<String> names = Arrays.asList("AlexiaThibert@test.com", "TracyEsqueda@test.com" , "JuanGregson@test.com" ,
    			"SteveLanham@test.com" , "DonnieRojo@test.com" , "NathanialParikh@test.com" , "MasonWiesen@test.com" , 
    			"DonaLangley@test.com" , "MohammadPotts@test.com" , "VinceWurth@test.com" , "RachaelHaggerty@test.com" ,
    			"EmeryLaporte@test.com");
    	for(int i = 0 ; i <= numberOfNames; i++)
    	{
        int rnd = new Random().nextInt(names.size());
        returnnames.add(names.get(rnd));}
        return  returnnames;
    }
    //helper function
    public  static int RandomNUM(){
    	Random r = new Random();
    	int Low = 1;
    	int High = 10;
    	return r.nextInt(High-Low) + Low;	
    }
	
}
