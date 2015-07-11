package net.simplydone.objects;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Cannon 
{
	public Cannon()
	{
		_width = 100;
		_height = 100;
		_rotation = 0;
	}
	
	static int _width;
	static int _height;
	static float _rotation;
	static int _x, _y;
	static Rect _rect;
	
	/**
	 * Sets x coordinate to new value
	 * @param x new x value
	 * */
	public void SetX(int x)
	{
		_x = x;
	}
	
	/**
	 * Sets y coordinate to new value
	 * @param y new y value
	 * */	
	public void SetY(int y)
	{
		_y = y;
	}
	
	/**
	 * @return x coordinate
	 * */
	public int GetX()
	{
		return _x;
	}
	
	/**
	 * @return y coordinate
	 * */
	public int GetY()
	{
		return _y;
	}
	
	/**
	 * @return cannons width
	 * */
	public int GetWidth()
	{
		return _width;
	}
	/**
	 * @return cannons height
	 * */
	public int GetHeight()
	{
		return _height;
	}

	/**
	 * @return cannon rotaion
	 * */
	public float GetRotation()
	{
		return _rotation;
	}
	/**
	 * Sets cannon's rotation value
	 * @param cannonRotationByTouch
	 * 			new rotation value 
	 * */
	public void SetRotation(float cannonRotationByTouch) 
	{
		_rotation = cannonRotationByTouch;
	}
	/***
	 * Calculates bitmap Rect object according to passed screen dimensions
	 * 
	 * @param screen_width
	 * 			width of the device screen
	 * @param screen_height
	 * 			height of the device screen
	 * 
	 * @return Rect object suited for device screen
	 * */
	public Rect GetRect(int screen_width, int screen_height, Bitmap b) 
	{
		if(_rect == null)
		{
			int left = screen_width / 2 - (GetWidth() / 2);
			int bottom = screen_height - GetHeight();
			int top = bottom - GetHeight();
			int right = left + GetWidth();
			
			SetX(left);
			SetY(top);
			_rect = new Rect( left , top, right, bottom);  
		}
		
		return _rect;
	}
}
