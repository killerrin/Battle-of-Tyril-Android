package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Texture2D {
	public Bitmap bitmap;

	public Vector2 Position;
	public XNAPaint _Paint;
	
	public int Width;
	public int Height;
	
	public Texture2D(Bitmap b)
	{
		bitmap = b;
		
		Width = b.getWidth();
		Height = b.getHeight();
		
		Position = new Vector2(0,0);
		_Paint = XNAPaint.White();
	}
	
	public Texture2D(Bitmap b, Vector2 _position, XNAPaint _paint)
	{
		bitmap = b;
		
		Width = b.getWidth();
		Height = b.getHeight();
		
		Position = _position;
		_Paint = _paint;
	}
	
	public void Draw(Canvas spriteBatch)
	{
		spriteBatch.drawBitmap(bitmap, Position.X, Position.Y, _Paint.paint);
	}
	
	public void Draw(Canvas spriteBatch, Vector2 position)
	{
		spriteBatch.drawBitmap(bitmap, position.X, position.Y, _Paint.paint);
	}
	
	public void Draw(Canvas spriteBatch, Vector2 position, XNAPaint _paint)
	{
		spriteBatch.drawBitmap(bitmap, position.X, position.Y, _paint.paint);
	}
	
	// Wrapping Methods to allow for faster porting from Windows Phone
	public int GetWidth() { return Width; }
	public int getWidth() {	return GetWidth(); }
	
	public int GetHeight() { return Height;	}
	public int getHeight() { return GetHeight(); }
	
	public Vector2 GetWidthHeight()	{ return new Vector2((float)Width, (float)Height); }
	public Vector2 GetOrigin() { return new Vector2(Width / 2f, Height / 2f); }
}
