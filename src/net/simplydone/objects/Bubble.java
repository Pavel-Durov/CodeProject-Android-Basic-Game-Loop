package net.simplydone.objects;

import net.simplydone.handlers.RotationHandler;

public class Bubble 
{
	public static final int DIAMETER = 50;
	double _x, _y,_deltaX, _deltaY;

	/**
	 * Initiate the x and y parameters to Cannon x and y coordinates
	 * */
	public Bubble(int cannon_x, int cannon_y, float angle, int touch_x, int touh_y)
	{
		_x = cannon_x;
		_y = cannon_y;
		
		//Called once in the constructor, for deltas initialization
		CalculateDeltas(angle , touch_x, touh_y);
	}
	
	/**
	 * Sets the deltas for x and y bubble coordinates
	 * 
	 * @param angle
	 * 			angle of the bubble direction
	 * @param cannon_x
	 * 		 	x coodinate of the cannon
	 * @param cannon_y
	 * 			y coodinate of the cannon
	 * @param touch_x
	 * 			x coordinate of the touch event
	 * @param touch_y
	 * 			y coordinate of the touch event
	 * */
	private void CalculateDeltas(float angle, float touch_x, float touch_y) 
	{	
		if(angle > RotationHandler.ANGLE_360)
		{
			angle -= RotationHandler.ANGLE_360;
		}
		
		if(angle >= RotationHandler.ANGLE_0 && angle < RotationHandler.ANGLE_90)
		{//if on the right side of the screen
			double m = 1;
			
			if(touch_x - _x  != 0)
			{
				//calculation the deltaY
				m = (touch_y  - _y) / (touch_x - _x);
			}
			
			_deltaX = 1;
			_deltaY = m;
		}
		else if(angle >= RotationHandler.ANGLE_270 && angle <= RotationHandler.ANGLE_360)
		{//if on the left side of the screen
			double m = 1;
			
			if(_x - touch_x  != 0)
			{
				//calculation the deltaY
				m = (_y - touch_y) / (_x - touch_x );
			}

			_deltaX = -1;
			_deltaY = -m;
		}
	}

	/**
	 * Returns the x coordinate
	 */
	public double GetX()
	{
		return _x;
	}
	
	/**
	 * Returns the y coordinate
	 */
	public double GetY()
	{
		return _y;
	}
	
	/**
	 * Advances the x and y parameters of the object by given value + calculated delta
	 * @param bounce
	 * 		  advance amount
	 */
	public void Advance(int bounce)
	{
		_x += _deltaX * bounce;
		_y += _deltaY * bounce;
	}
	
}
