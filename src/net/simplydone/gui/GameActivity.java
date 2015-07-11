package net.simplydone.gui;

import net.simplydone.handlers.RotationHandler;
import net.simplydone.main.AppConstants;
import net.simplydone.objects.FingerAim;
import net.simplydone.views.GameView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class GameActivity extends Activity {

	GameView _gameEngineView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        //sets the activity view as GameView class
        SurfaceView view = new GameView(this, AppConstants.GetEngine());
        setContentView(view);

        getActionBar().hide();
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		 super.onTouchEvent(event);
		int action = event.getAction();
		switch (action) 
		{
			case MotionEvent.ACTION_DOWN:
			{
				OnActionDown(event);
				break;
			}
			case MotionEvent.ACTION_UP:
			{
				OnActionUp(event);
				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				OnActionMove(event);
				break;
			}
			default:break;
		}
		return false;
	}
	
	/*activates on touch move event*/
	private void OnActionMove(MotionEvent event) 
	{
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		if(GetIfTouchInTheZone(x, y))
		{
			 AppConstants.GetEngine().SetCannonRotaion(x, y);
		}
		
		AppConstants.GetEngine().SetLastTouch(event.getX(), event.getY());
	}


	private boolean GetIfTouchInTheZone(int x, int y) 
	{
		return RotationHandler.CheckIfTouchIsInTheZone(x,y,  AppConstants.GetEngine().getCannon());
	}
	
	/*activates on touch up event*/
	private void OnActionUp(MotionEvent event) 
	{
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		if(GetIfTouchInTheZone(x, y))
		{
			 AppConstants.GetEngine().SetCannonRotaion(x, y);
			
			 AppConstants.GetEngine().SetLastTouch(FingerAim.DO_NOT_DRAW_X,
					FingerAim.DO_NOT_DRAW_Y);
			
			 AppConstants.GetEngine().CreateNewBubble(x,y);
		}
	}
	/*activates on touch down event*/
	private void OnActionDown(MotionEvent event) 
	{
		 AppConstants.GetEngine().SetLastTouch(event.getX(), event.getY());
	}

}
