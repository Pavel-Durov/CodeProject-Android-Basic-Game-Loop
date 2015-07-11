package net.simplydone.main;

import net.simplydone.handlers.BitmapBank;
import net.simplydone.objects.GameEngine;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants 
{

	static BitmapBank _bitmapsBank;
	static GameEngine _engine;
	
	public static int SCREEN_WIDTH, 
					  SCREEN_HEIGHT;
	
	/**
	 * Initiates the applciation constants
	 * */
	public static void Initialization(Context context)  
	{
		_bitmapsBank = new BitmapBank(context.getResources());
	    SetScreenSize(context); 
	    _engine = new GameEngine();
	}


	/**
	 * Sets screen size constants accordingly to device screen size
	 * */
	private static void SetScreenSize(Context context) 
	{
	    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    DisplayMetrics metrics = new DisplayMetrics();
	    display.getMetrics(metrics);
	    int width = metrics.widthPixels;
	    int height = metrics.heightPixels;
	    
	    AppConstants.SCREEN_WIDTH = width;
	    AppConstants.SCREEN_HEIGHT = height;
	}

	/**
	 * @return BitmapBank instance
	 * */
	public static BitmapBank GetBitmapsBank()
	{
		return _bitmapsBank;
	}
	
	/**
	 * @return GameEngine instance
	 * */
	public static GameEngine GetEngine() 
	{
		return _engine;
	}


	/**
	 * Stops the given thread
	 * @param thread
	 * 			thread to stop
	 * */
	public static void StopThread(Thread thread) 
	{
		boolean retry = true;
		while (retry) 
		{
		    try 
		    {
		        thread.join();
		        retry = false;
		    } 
		    catch (InterruptedException e) {}
		}
	}
	
}
