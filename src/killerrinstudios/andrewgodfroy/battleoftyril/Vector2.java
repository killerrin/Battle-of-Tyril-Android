package killerrinstudios.andrewgodfroy.battleoftyril;
import android.graphics.Point;
import android.graphics.Rect;

public class Vector2 {
	public float X;
	public float Y;
	
	public Vector2()
	{
		X = 0f;
		Y = 0f;
	}
	public Vector2(float f)
	{
		X = f;
		Y = f;
	}
	public Vector2(float x, float y)
	{
		X = x;
		Y = y;
	}
	
	public double LengthSquared() { return Magnitude(); }
	public double Magnitude()
	{
		return Math.sqrt((X*X)+(Y*Y));
	}
	
	public void Normalize()
	{
		double mag = Magnitude();
		X /= (float)mag;
		Y /= (float)mag;
	}
	
	public Point GetPoint()
	{
		return new Point ((int)X,
						  (int)Y);
	}
	
	public Rect GetRectangle()
	{
		return new Rect((int)X,
						(int)Y,
						(int)1,
						(int)1);
	}
	
	public static boolean HasValue(Vector2 v1)
	{
		if (v1 == null) return true;
		return false;
	}
	
	public static Vector2 Zero()
	{
		return new Vector2();
	}
	
	public static Vector2 Add(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.X + v2.X,
						   v1.Y + v2.Y);
	}
	public static Vector2 Add(Vector2 v1, float f)
	{
		return new Vector2(v1.X + f,
						   v1.Y + f);
	}
	
	public static Vector2 Subtract(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.X - v2.X,
						   v1.Y - v2.Y);
	}
	
	public static Vector2 Subtract(Vector2 v1, float f)
	{
		return new Vector2(v1.X - f,
						   v1.Y - f);
	}
	
	public static Vector2 Multiply(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.X * v2.X,
						   v1.Y * v2.Y);
	}
	
	public static Vector2 Multiply(Vector2 v1, float f)
	{
		return new Vector2(v1.X * f,
						   v1.Y * f);
	}
	
	public static Vector2 Divide(Vector2 v1, Vector2 v2)
	{
		return new Vector2(v1.X / v2.X,
						   v1.Y / v2.Y);
	}
	
	public static Vector2 Divide(Vector2 v1, float f)
	{
		return new Vector2(v1.X / f,
						   v1.Y / f);
	}
}
