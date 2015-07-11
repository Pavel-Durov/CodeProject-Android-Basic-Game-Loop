package net.simplydone.main;

import android.app.Application;

public class GameApplication extends Application{


	public GameApplication() 
	{
	
	}

	@Override
	public void onCreate() 
	{
		super.onCreate();
		//Initialization of the AppConstants class
	    AppConstants.Initialization(this.getApplicationContext());
	  
	}
}
