package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class VirtualThumbsticks
{
	public static Texture2D Texture;
	public static XNAPaint _Paint;
	
    // the distance in screen pixels that represents a thumbstick value of 1f.
	private static float maxThumbstickDistance = 50f;

	// the current positions of the physical touches
	private static Vector2 leftPosition;
	private static Vector2 rightPosition;

	// the IDs of the touches we are tracking for the thumbsticks
	public static int leftId = -1;
	public static int rightId = -1;

	/// <summary>
	/// Gets the center position of the left thumbstick.
	/// </summary>
	public static Vector2 LeftThumbstickCenter;

	/// <summary>
	/// Gets the center position of the right thumbstick.
	/// </summary>
	public static Vector2 RightThumbstickCenter;

    public static int LeftID()
    {
    	return leftId;
    }

    /// <summary>
	/// Gets the value of the left thumbstick.
	/// </summary>
	public static Vector2 LeftThumbstick()
	{
		// if there is no left thumbstick center, return a value of (0, 0)
		if (LeftThumbstickCenter == null) //!LeftThumbstickCenter.HasValue)
			return Vector2.Zero();

		// calculate the scaled vector from the touch position to the center,
		// scaled by the maximum thumbstick distance
		Vector2 l = Vector2.Divide(Vector2.Subtract(leftPosition, LeftThumbstickCenter), maxThumbstickDistance);

		// if the length is more than 1, normalize the vector
		if (l.LengthSquared() > 1f)
			l.Normalize();

		return l;
	}

	/// <summary>
	/// Gets the value of the right thumbstick.
	/// </summary>
	public static Vector2 RightThumbstick()
	{
		// if there is no left thumbstick center, return a value of (0, 0)
		if (RightThumbstickCenter == null) //!RightThumbstickCenter.HasValue)
			return Vector2.Zero();

		// calculate the scaled vector from the touch position to the center,
		// scaled by the maximum thumbstick distance
		Vector2 r = Vector2.Divide(Vector2.Subtract(rightPosition, RightThumbstickCenter), maxThumbstickDistance);

		// if the length is more than 1, normalize the vector
		if (r.LengthSquared() > 1f)
			r.Normalize();

		return r;
	}

	/// <summary>
	/// Updates the virtual thumbsticks based on current touch state. This must be called every frame.
	/// </summary>
	@SuppressWarnings({ "static-access", "unused" })
	public static void Update(MotionEvent e)
	{
		Vector2 leftTouch = null;
		Vector2 rightTouch = null;
		Vector2 earliestTouch = null;
		int lID = -999;
		int rID = -999;
		int ctr = 0;
		
		int numberOfTouches = e.getPointerCount();
		
		if (e.getAction() == e.ACTION_UP)
		{		
			if (e.getY(e.getActionIndex()) < (Screen.Width / 2 )) 
			{
				LeftThumbstickCenter = null;
				leftId = -1;
			}
			else if (e.getY(e.getActionIndex()) >= (Screen.Width / 2))
			{
				// otherwise reset our values to not track any touches
				// for the right thumbstick
				RightThumbstickCenter = null;
				rightId = -1;
			}
		}		
		else {
		    for (int i = 0; i < numberOfTouches; i++) {
		        try
		        {
		        	if (e.getPointerId(i) == leftId) {
		        		leftTouch = new Vector2(e.getX(e.getPointerId(i)),
		        								e.getY(e.getPointerId(i)));
		        		
		        		lID = i;
		        		continue;
		        	}
		        	
		        	if (e.getPointerId(i) == rightId) {
		        		rightTouch = new Vector2(e.getX(e.getPointerId(i)),
		        								e.getY(e.getPointerId(i)));
		        		
		        		rID = i;
		        		continue;
		        	}
		        	
		        	// Get earliest touch
		        	earliestTouch = new Vector2(e.getX(e.getPointerId(i)),
		    									e.getY(e.getPointerId(i)));
		        	// Create new touch
		        	
		        	if (leftId == -1)
		              {
		                  // if we are not currently tracking a left thumbstick and this touch is on the left
		                  // half of the screen, start tracking this touch as our left stick
		                  if (earliestTouch.Y < (Screen.Width / 2))
		                  {
		                      leftTouch = earliestTouch;
		                      lID = i;
		                      continue;
		                  }
		              }
		  
		              if (rightId == -1)
		              {
		                  // if we are not currently tracking a right thumbstick and this touch is on the right
		                  // half of the screen, start tracking this touch as our right stick
		                  if (earliestTouch.Y >= (Screen.Width / 2))
		                  {
		                      rightTouch = earliestTouch;
		                      rID = i;
		                      continue;
		                  }
		              }
		            ++ctr;
		        }
		        catch (IndexOutOfBoundsException ex)
		        {
		            break;
		        }
		    }   
			
		    //if we have a left touch
			if (leftTouch != null)
			{
				// if we have no center, this position is our center
				if (LeftThumbstickCenter == null) //!LeftThumbstickCenter.HasValue)
					LeftThumbstickCenter = leftTouch;
		
				// save the position of the touch
				leftPosition = leftTouch;
		
				// save the ID of the touch
				leftId = lID;
			}
			else
			{
				// otherwise reset our values to not track any touches
				// for the left thumbstick
				LeftThumbstickCenter = null;
				leftId = -1;
			}
		
			// if we have a right touch
			if (rightTouch != null)
			{
				// if we have no center, this position is our center
				if (RightThumbstickCenter == null) //!RightThumbstickCenter.HasValue)
					RightThumbstickCenter = rightTouch;
		
				// save the position of the touch
				rightPosition = rightTouch;
		
				// save the ID of the touch
				rightId = rID;
			}
			else
			{
				// otherwise reset our values to not track any touches
				// for the right thumbstick
				RightThumbstickCenter = null;
				rightId = -1;
			}
		}
	}
	
	public static void Draw(Canvas spriteBatch)
	{
        if (LeftThumbstickCenter != null)
        {
        	Vector2 position = Vector2.Subtract(LeftThumbstickCenter, Texture.GetOrigin());
            spriteBatch.drawBitmap(Texture.bitmap, position.X, position.Y, _Paint.paint);
        }

        if (RightThumbstickCenter != null)
        {
        	Vector2 position = Vector2.Subtract(RightThumbstickCenter, Texture.GetOrigin());
            spriteBatch.drawBitmap(Texture.bitmap, position.X, position.Y, _Paint.paint);
        }
	}
}