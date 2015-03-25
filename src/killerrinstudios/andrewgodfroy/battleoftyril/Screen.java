package killerrinstudios.andrewgodfroy.battleoftyril;

public class Screen {
	public static int Width = 0;
	public static int Height = 0;
	public static Rectangle ScreenRectangle = new Rectangle();
	
	public static void SetScreen(int width, int height)
	{
		Width = width;
		Height = height;
		
		ScreenRectangle = new Rectangle(0,0,width, height);
	}
	
	public static Vector2 GetCenter() { return new Vector2(Width/2, Height/2); }
	public static Vector2 ClampToScreen(Vector2 Position)
	{
		Vector2 v1 = new Vector2();
		
		if (Position.X < 0) { v1.X = 0; }
		else if (Position.X > Screen.Width) { v1.Y = Width; }
		else { v1.X = Position.X; }
		
        if (Position.Y < 0) { v1.Y = 0; }
        else if (Position.Y > Screen.Height) { v1.Y = Height; }
        else { v1.Y = Position.Y; }
        
        return v1;
	}
}
