package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Rect;

public class Rectangle {
	int X;
	int Y;
	int Width;
	int Height;
	
	public Rectangle()
	{
		X = 0;
		Y = 0;
		Width = 0;
		Height = 0;
	}
	
	public Rectangle(Rect rect)
	{
		X = rect.left;
		Y = rect.top;
		Width = rect.right;
		Height = rect.bottom;
	}
	
	public Rectangle(int x, int y, int w, int h)
	{
		X = x;
		Y = y;
		Width = w;
		Height = h;
	}
	
	public Vector2 GetVectorXY() { return new Vector2(X, Y); }
	public Vector2 GetVectorWH() { return new Vector2(Width, Height); }
	
	public Rect GetRect() { return new Rect(X, Y, Width, Height); }
	
	boolean Intersects(Rectangle r) { return (GetRect().intersect(r.GetRect())); }
	boolean Intersects(Rect r) { return (GetRect().intersect(r)); }
	
	boolean Contains(Rectangle r) { return (GetRect().contains(r.GetRect())); }
	boolean Contains(Rect r) { return (GetRect().contains(r)); }
}
