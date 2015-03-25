package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;
import android.graphics.Rect;

public class RenderText
{
    public String Text;
    public Vector2 Location;
    public Rectangle CollideRectangle;
    public XNAPaint _Paint;
    //public SpriteFont Font;

    public RenderText(String text, Vector2 location, XNAPaint _paint)
    {
        Text = text;
        Location = location;
        _Paint = _paint;
        //Font = font;

        CollideRectangle = _paint.GetTextBounds(text);
        //Rect bounds = new Rect();
        //_Paint.paint.getTextBounds(Text, 0, Text.length(), bounds);
        //CollideRectangle = new Rectangle((int)Location.X, (int)Location.Y, (int)bounds.width(), (int)bounds.height());
    }

    public boolean Collides(Rectangle rect)
    {
    	return CollideRectangle.Intersects(rect);
    }
    public boolean Collides(Rect rect)
    {
    	return CollideRectangle.Intersects(rect);
    }

    public void Draw(Canvas spriteBatch)
    {
        spriteBatch.drawText(Text, Location.X, Location.Y, _Paint.paint);
    }
    public static void Draw(Canvas spriteBatch, String customText, Vector2 location, XNAPaint _paint)
    {
        spriteBatch.drawText(customText, location.X, location.Y, _paint.paint);
    }
    
    
}