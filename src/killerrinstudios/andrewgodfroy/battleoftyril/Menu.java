package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Menu
{
    public String Name;
    public boolean Activated;
    public Texture2D Texture;

    public Menu()
    {
        Name = "";
        Activated = false;
    }
    public Menu(String menuName, Texture2D texture)
    {
        Name = menuName;
        Activated = false;
        Texture = texture;
    }
    public Menu(String menuName, Texture2D texture, boolean activated)
    {
        Name = menuName;
        Activated = activated;
        Texture = texture;
    }

    public void Draw(Canvas spriteBatch)
    {
    	Paint p = new Paint();
    	p.setColor(Color.WHITE);
    	
        spriteBatch.drawBitmap(Texture.bitmap,0f,0f,p);
    }
    public void Draw(Canvas spriteBatch, RenderText text)
    {
    	//Paint p = new Paint();
    	//p.setColor(Color.WHITE);
        //spriteBatch.drawBitmap(Texture, new Vector2(0, 0), Color.White);
        
    	Draw(spriteBatch);
        text.Draw(spriteBatch);
    }
}
