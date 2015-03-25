package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;

public class Bullet {
	public Weapon MainWeapon;
	public Vector2 Position;
	public Vector2 Direction;
	public Rectangle CollideRectangle;
	public int DeathCounter;
	
	public Bullet()
	{
		DeathCounter = 1;
	}
	public Bullet(Weapon weapon, Vector2 position, Vector2 direction)
	{
		MainWeapon = weapon;
		Position = position;
		Direction = direction;
		
		DeathCounter = 0;
		CollideRectangle = new Rectangle((int)position.X, (int)position.Y, weapon.Texture.getWidth(), weapon.Texture.getHeight());   
	}
	
	public boolean CheckCollision(Rect rect)
    {
        if (CollideRectangle.Intersects(rect))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	
    public void UpdateCollisionRectangle()
    {
    	// Update the position
    	CollideRectangle.X = (int)Position.X;
    	CollideRectangle.Y = (int)Position.Y;
    }
	
	public void Update(GameTime gameTime)
    {
        if (DeathCounter == 0)
        {
            Position = new Vector2((Position.X + Direction.X * (float)MainWeapon.Speed),
                (Position.Y + Direction.Y * (float)MainWeapon.Speed));

            //CollideRectangle = new Rectangle((int)Position.X, (int)Position.Y,
                //(int)MainWeapon.Texture.getWidth(), (int)MainWeapon.Texture.getHeight());

            UpdateCollisionRectangle();
        }
        
    }


    public void Draw(Canvas spriteBatch)
    {
    	Paint paint = new Paint();
    	paint.setColor(Color.GREEN);
    	//paint.setARGB(255, 0, 255, 0);
    	
        //spriteBatch.drawBitmap(MainWeapon.Texture, Position, Color.Green);
        spriteBatch.drawBitmap(MainWeapon.Texture.bitmap, Position.X, Position.Y, paint);
    }
}
