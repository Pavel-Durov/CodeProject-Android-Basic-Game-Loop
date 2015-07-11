package net.simplydone.handlers;

import net.simplydone.main.AppConstants;
import net.simplydone.objects.Cannon;

public class RotationHandler 
{
	/*Angle constants*/
	public static final int ANGLE_180 = 180;
	public static final int ANGLE_360 = 360;
	public static final int ANGLE_270 = 270;
	public static final int ANGLE_90 = 90;
	public static final int ANGLE_0 = 0;
	
	public static final int TOLERANCE = 35;
	
	/**
	 * Calculates cannon bitmap rotation
	 * @param touch_x
	 * 			x coordinates of the touch event
	 * @param touch_y
	 * 			y coordinates of ther touch event
	 * @param cannon
	 * 			cannon object
	 * @return
	 * 		new rotation value
	 * */
	public static float CannonRotationByTouch(int touch_x, int touch_y, Cannon cannon)
	{
		float result = cannon.GetRotation();
		
		if(CheckIfTouchIsInTheZone(touch_x, touch_y, cannon))
		{
			if(CheckIsOnLeftSideScreen(touch_x))
			{
				int Opposite = touch_x - cannon.GetX();
				int Adjacent  = cannon.GetY() - touch_y;
				
				double angle = Math.atan2(Opposite, Adjacent);
				result = (float)Math.toDegrees(angle);
			}
			else
			{
				int Opposite = cannon.GetX() - touch_x;
				int Adjacent  = cannon.GetY() - touch_y;
				
				double angle = Math.atan2(Opposite, Adjacent);
				result = ANGLE_360 - (float)Math.toDegrees(angle) ;
			}
		}
		
		return result;
	}
	/**
	 * Determines whether the touch was on the left half of the screen
	 * @param touch_x
	 * 		x coordinate of the touch
	 * @return
	 * 		true if on the right side, else returns false
	 * */
	private static boolean CheckIsOnLeftSideScreen(int touch_x) 
	{
		return touch_x > AppConstants.SCREEN_WIDTH / 2;
	}
	/**
	 * Determines whether the touch is in the relevant zone on the screen
	 * @param touch_x
	 * 		x coordinate of the touch event
	 * @param touch_y
	 * 		y coordinate of the touch event
 	 * @param cannon
	 * 			Cannon object
	 * 
	 * @return true if the touch is in the relevant zone
	 * */
	public static boolean CheckIfTouchIsInTheZone(int touch_x, int touch_y, Cannon cannon) 
	{
		return touch_y + TOLERANCE < cannon.GetY();
	}
}
