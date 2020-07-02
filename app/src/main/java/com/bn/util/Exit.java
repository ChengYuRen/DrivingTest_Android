package com.bn.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

public class Exit {
	
	public static List<Activity> activity=new LinkedList<Activity>();
    private static Exit instance;
    
    public static Exit getInstance()
    {
    	if(null == instance)
    	{
    		instance = new Exit();
    	}
    	
    	return instance;
    }
	
	public void addActivities(Activity ac)
	{
		activity.add(ac);
	}
	
	public static void exitActivity()
	{
		for(Activity ac:activity)
		{
			ac.finish();
		}
	}
}
