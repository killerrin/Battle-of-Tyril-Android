package killerrinstudios.andrewgodfroy.battleoftyril;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class Level
{
    public String Name;
    public boolean Activated;
    public Texture2D Texture;
    public Texture2D GameBackground;
    public Texture2D GameOverBackground;
    public Texture2D GameWinBackground;
    public Texture2D ThumbOutline;
    public Vector2 Position;
    public XNAPaint _Paint;
    public Rectangle ThumbRectangle;

    public Level(String name, Texture2D texture, Vector2 position, XNAPaint paint)
    {
        Name = name;
        Texture = texture;
        GameBackground = texture;
        Position = position;
        _Paint = paint;
        Activated = false;
        

        ThumbRectangle = new Rectangle(0,0,0,0);
    }
    public Level(String name, Texture2D texture, Vector2 position, XNAPaint paint, boolean activated)
    {
        Name = name;
        Texture = texture;
        Position = position;
        _Paint = paint;
        Activated = activated;
    }

    public void Draw(Canvas spriteBatch)
    {    	
    	spriteBatch.drawBitmap(Texture.bitmap, Position.X, Position.Y, _Paint.paint);
        //spriteBatch.Draw(Texture, Position, null, Colour, 0f, new Vector2(0,0), 0.63f, SpriteEffects.None, 0f);
    }
    public void DrawThumbnail(Canvas spriteBatch, Vector2 position, float scale)
    {
        ThumbRectangle = new Rectangle((int)position.X-96, (int)position.Y-58, (int)(Texture.GetWidth() * scale), (int)(Texture.GetHeight() * scale));

        if (Activated == true)
        {
        	Paint p = new Paint();
        	p.setColor(Color.GREEN);
        	
            //spriteBatch.drawBitmap(ThumbOutline,
            //					   new Rect((int)(ThumbRectangle.left - 2), (int)(ThumbRectangle.top - 2), (int)ThumbRectangle.width() + 4, (int)ThumbRectangle.height() + 4),
            //					   Position.get
            //					   p);
        	
            spriteBatch.drawRect(new Rect((int)(ThumbRectangle.X - 2), (int)(ThumbRectangle.Y - 2), (int)ThumbRectangle.Width + 4, (int)ThumbRectangle.Height + 4),
            					 p);
        }
        else
        {
        	Paint p = new Paint();
        	p.setColor(Color.GRAY);
        	
            //spriteBatch.drawBitmap(ThumbOutline, new Rect((int)(ThumbRectangle.left - 2), (int)(ThumbRectangle.top - 2), (int)ThumbRectangle.width() + 4, (int)ThumbRectangle.height() + 4), p);
            spriteBatch.drawRect(new Rect((int)(ThumbRectangle.X - 2), (int)(ThumbRectangle.Y - 2), (int)ThumbRectangle.Width + 4, (int)ThumbRectangle.Height + 4),
					             p);
        }

        Matrix matrix = new Matrix();

        matrix.preTranslate(position.X, position.Y);
        matrix.setScale(scale, scale);
        
        spriteBatch.drawBitmap(Texture.bitmap, matrix, _Paint.paint);
        //spriteBatch.Draw(Texture, position, null, Colour, 0f, new Vector2(Texture.Width/2, Texture.Height/2), scale, SpriteEffects.None, 0f);
    }


}