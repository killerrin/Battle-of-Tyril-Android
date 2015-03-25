package killerrinstudios.andrewgodfroy.battleoftyril;

import java.util.ArrayList;

import android.graphics.Canvas;

public class Touchpoints
{
	public Texture2D Texture;
	public Vector2 WidthHeight;
	
    public double LastTappedX;
    public double LastTappedY;

    public Rectangle LastTapped;
    //public Rectangle[] RectCoords;
    public ArrayList<Rectangle> RectCoords;
    
    public XNAPaint _Paint;
    
    public Touchpoints(Texture2D texture)
    {
    	Texture = texture;
    	
    	_Paint = new XNAPaint(155,0,255,0);
    	WidthHeight = new Vector2(50,50);
        
        ResetTouchpoints();
    }
    
    public Touchpoints(XNAPaint _paint, Vector2 widthHeight)
    {
        _Paint = _paint;
    	WidthHeight = widthHeight;
        
        ResetTouchpoints();
    }

    public void ResetTouchpoints()
    {
        LastTappedX = 0;
        LastTappedY = 0;
        LastTapped = new Rectangle(0, 0, 1, 1);
        
        RectCoords = new ArrayList<Rectangle>();
        //RectCoords = new Rectangle[1];
        //RectCoords[0] = new Rectangle(0,0,(int)WidthHeight.X, (int)WidthHeight.Y);
        //for (int ctr = 0; ctr < 1000000; ctr++) { }
    }

    public void Update(Vector2 touch)
    {
    	LastTappedX = touch.X;
    	LastTappedY = touch.Y;
    	
    	RectCoords.add(new Rectangle((int)touch.X,
    								 (int)touch.Y,
    								 (int)WidthHeight.X,
    								 (int)WidthHeight.Y));
    	
    	LastTapped = new Rectangle((int)touch.X, (int)touch.Y, 1, 1);
    	
//        // Grab Touchpoints to allow for easier debugging.
//        int numberOfTouches = e.getPointerCount();
//        int ctr = 0;
//        
//        if (numberOfTouches == 0) { RectCoords = new Rectangle[1]; RectCoords[0] = new Rectangle();}
//        else { RectCoords = new Rectangle[numberOfTouches]; }
//        //RectCoords = new Rectangle[numberOfTouches];
//        
//        for (int i = 0; i < numberOfTouches; i++) {
//        	LastTappedX = (int) e.getX(e.getPointerId(i));
//        	LastTappedY = (int) e.getY(e.getPointerId(i));
//        	
//            try
//            {
//                RectCoords[ctr] = new Rectangle((int)(LastTappedX - (WidthHeight.X / 2)),
//                								(int)(LastTappedY - (WidthHeight.Y / 25)),
//						                		(int)WidthHeight.X,
//						                		(int)WidthHeight.Y);
//                ++ctr;
//            }
//            catch (IndexOutOfBoundsException ex)
//            {
//                break;
//            }
//        }        
//        
//        LastTapped = new Rectangle((int)LastTappedX, (int)LastTappedY, 1, 1);
    }
    
    public void Draw(Canvas spriteBatch)
    {
        for (int i = 0; i < RectCoords.size(); i++)
        {        	
        	spriteBatch.drawBitmap(Texture.bitmap, RectCoords.get(i).X, RectCoords.get(i).Y, _Paint.paint);
        }
    }
    public void DrawRect(Canvas spriteBatch)
    {
        for (int i = 0; i < RectCoords.size(); i++)
        {        	
        	spriteBatch.drawRect(RectCoords.get(i).GetRect(), _Paint.paint);
        }
    }
}