package net.simplydone.objects;

import java.util.LinkedList;
import java.util.List;

import net.simplydone.handlers.BitmapBank;
import net.simplydone.handlers.RotationHandler;
import net.simplydone.main.AppConstants;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
/*
 * Stores all object references that relevant for the game display
 * Calls objects business logic methods, and draw them to the given Canvas from DisplayThread
 * */
public class GameEngine 
{
	/*MEMBERS*/
	static final int BUBBLE_BOUNCE = 1; 
	static Cannon _cannon;
	static List<Bubble> _bubles;
	static final Object _sync = new Object();
	static float _lastTouchedX, _lastTouchedY;
		
	public GameEngine()
	{
		_bubles = new LinkedList<Bubble>();
		_paint = new Paint();
		_cannon = new Cannon();
	}
	
	Paint _paint;
	
	/**
	 * Updates all relevant objects business logic
	 * */
	public void Update()
	{
		AdvanceBubbles();
	}
	
	/**
	 * Iterates through the Bubble list and advances them
	 * */
	private void AdvanceBubbles() 
	{	
		synchronized (_sync) 
		{
			for(Bubble b : _bubles)
			{
				b.Advance(BUBBLE_BOUNCE);
			}
		}
	}

	/**
	 * Draws all objects according to their parameters
	 * @param canvas
	 * 			canvas on which will be drawn the objects
	 * */
	public void Draw(Canvas canvas)
	{
		DrawCanon(canvas);
		DrawBubles(canvas);
		DrawAim(canvas);
	}
	
	/**
	 * Draws Aim bitmap
	 * @param canvas
	 * 			canvas on which will be drawn the bitmap 
	 **/
	private void DrawAim(Canvas canvas) 
	{
		//Doesn't draws on touch ACTION_UP event, only on ACTION_DOWN or ACTION_MOVE
		if(_lastTouchedX != FingerAim.DO_NOT_DRAW_X 
				&& _lastTouchedY != FingerAim.DO_NOT_DRAW_Y)
		{
			Bitmap bitmap = AppConstants.GetBitmapsBank().GetFingerAim();
			canvas.drawBitmap(bitmap, _lastTouchedX, _lastTouchedY, _paint);
		}
	}
	/**
	 * Draws bubble bitmaps
	 * @param canvas
	 * 			canvas on which will be drawn the bitmap 
	 * */
	private void DrawBubles(Canvas canvas) 
	{
		synchronized (_sync) 
		{
			for(Bubble buble : _bubles)
			{
				//Drawing bubble
				Bitmap bitmap = AppConstants.GetBitmapsBank().GetRedBubble();
				canvas.drawBitmap(bitmap, (float)buble.GetX(), (float)buble.GetY(), _paint);
			}
		}
	}

	/**
	 * Draws cannon bitmap
	 * @param canvas
	 * 			canvas on which will be drawn the bitmap 
	 * */
	private void DrawCanon(Canvas canvas) 
	{
		Bitmap cannon = BitmapBank.RotateBitmap( AppConstants.GetBitmapsBank().GetAndroid(),
				_cannon.GetRotation());
		
		Rect rect = _cannon.GetRect(AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT, cannon);
		canvas.drawBitmap(cannon, null, rect, _paint);
	}
	
	/**
	 * Sets cannon bitmap rotation accordingly to touch event
	 * @param touch_x
	 * 			x coordinate of the touch event
	 * @param touch_y
	 * 			y coordinate of the touch event
	 * */
	public void SetCannonRotaion(int touch_x, int touch_y) 
	{
		float cannonRotation = RotationHandler
				.CannonRotationByTouch(touch_x, touch_y, _cannon);
		
		_cannon.SetRotation(cannonRotation);
	}

	/**
	 * Crates a new bubble on touch event
	 * @param touchX
	 * 			x coordinates of touch event
	 * @param touchY
	 * 			y coordinates of touch event
	 * */
	public void CreateNewBubble(int touchX, int touchY) 
	{
		synchronized (_sync) 
		{
			_bubles.add
			(
					new Bubble
					(
							_cannon.GetX(), 
							_cannon.GetY(),
							_cannon.GetRotation(), 
							touchX, 
							touchY
					)
			);
		}
	}
	/**
	 * @return cannon object
	 * */
	public Cannon getCannon() 
	{
		return _cannon;
	}
	
	/**
	 * Sets previous touch coordinates
	 * @param x
	 * 		current touch x coordinate
	 * @param y
	 * 		current touch y coordinate
	 * */
	public void SetLastTouch(float x, float y)
	{
		_lastTouchedX = x;
		_lastTouchedY = y;
	}
}
