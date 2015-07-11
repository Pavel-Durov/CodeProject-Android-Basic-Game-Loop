package net.simplydone.handlers;

import net.simplydone.gameloop.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/*
 * Responsible for loading bitmap instances from resources and keeping references to them. 
 */
public class BitmapBank 
{
	Bitmap _android, _red_bubble, _finger_aim;
	
	/**
	 * Loads bitmaps from the resources
	 * @param res
	 * 		resources reference
	 * */
	public BitmapBank(Resources res)
	{
		_android = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
		_red_bubble = BitmapFactory.decodeResource(res, R.drawable.red_ball);
		_finger_aim = BitmapFactory.decodeResource(res, R.drawable.aim_ball);
	}
	/**
	 * @return Android Bitmap
	 * */
	public Bitmap GetAndroid()
	{
		return _android;
	}
	
	/**
	 * @return FingerAim Bitmap
	 * */
	public Bitmap GetFingerAim()
	{
		return _finger_aim;
	}
	
	/**
	 * @return red bubble Bitmap
	 * */
	public Bitmap GetRedBubble()
	{
		return _red_bubble;
	}
	
	/**
	 * Rotates given bitmap according to passed angle, using Metrix object
	 * @param source
	 * 			Bitmap that needed to be rotated
	 * @param angle
	 * 			Rotation angle
	 * 
	 * @return rotated bitmap
	 * */
	public static Bitmap RotateBitmap(Bitmap source, float angle)
	{
		Matrix matrix = new Matrix();
	    matrix.postRotate(angle);
	    
	    return Bitmap.createBitmap
	    		(
	    				source, 
	    				0, 0,
	    				source.getWidth(), 
	    				source.getHeight(), 
	    				matrix, 
	    				true
	    		);
	}
}
