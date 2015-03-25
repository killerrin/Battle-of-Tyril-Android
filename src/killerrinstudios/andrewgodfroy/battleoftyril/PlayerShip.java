package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;

public class PlayerShip extends Ship
{
    public int shootctr;

    public PlayerShip(Vector2 location, float acceleration, Texture2D texture, XNAPaint _paint)
    {
    	super(location, acceleration, texture, _paint);
        
        Health = 100.0;
        shootctr = 0;
    }

    @Override
    public void Update(GameTime gameTime)
    {
        // adjust our velocity base on our virtual thumbstick
        Velocity = Vector2.Add(Velocity,
        		               Vector2.Multiply(VirtualThumbsticks.LeftThumbstick(), Acceleration)); //Velocity += VirtualThumbsticks.LeftThumbstick * Acceleration;

        super.Update(gameTime);

        // decrease the velocity a little bit for some drag
        Velocity = Vector2.Multiply(Velocity, 0.99f);

        // update our ship's rotation based on the left thumbstick
        if (VirtualThumbsticks.LeftThumbstick().X != 0 && VirtualThumbsticks.LeftThumbstick().Y != 0)
        {
            Rotation = (float)Math.atan2(VirtualThumbsticks.LeftThumbstick().X, -VirtualThumbsticks.LeftThumbstick().Y);
        }

        // Clamp the player to the screen
        ClampToScreen();
        super.UpdateCollisionRectangle(); // CollideRectangle = new Rectangle((int)Position.X, (int)Position.Y, Texture.Width, Texture.Height);
    }

    @Override
    public Bullet Shoot()
    {
        Bullet shot;
        //shootctr = 0;
        //shot = new Bullet(MainWeapon, Position, VirtualThumbsticks.RightThumbstick, true);
        //return (shot);
        if (shootctr == MainWeapon.WeaponCounter || shootctr == -999)
        {
            shootctr = 0;
            shot = new Bullet(MainWeapon, Position, VirtualThumbsticks.RightThumbstick());//, true);
            return (shot);
        }
        else { ++shootctr; return (new Bullet()); }
    }
    
    @Override
    public void Draw (Canvas spriteBatch)
    {
        super.Draw(spriteBatch);
    }
}
