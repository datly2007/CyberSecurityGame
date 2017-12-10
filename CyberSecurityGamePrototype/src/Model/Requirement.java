package Model;

import java.util.ArrayList;
import java.util.List;

public class Requirement {
	
	private List<Boolean> myLevelOneChecker; 
	
	public Requirement() {
		
		myLevelOneChecker = new ArrayList<>();
		
	}
	
	private void levelOneRequirement() {
		
		/*
		 * If default password == user's password 
		 *     user didn't pass first level
		 * Else
		 *     user pass first level
		 */
	}
	
	public boolean passLevelOne() {
		
		return myLevelOneChecker.get(0);
	}

}
