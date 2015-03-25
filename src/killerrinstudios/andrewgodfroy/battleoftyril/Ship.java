package killerrinstudios.andrewgodfroy.battleoftyril;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Matrix;

public class Ship
{
    //public Rectangle ScreenSize;
    public Rectangle CollideRectangle;

    public Texture2D Texture;

    public XNAPaint _Paint;

    public double MaxSpeed = 10.0;

    public float Rotation;
    public float Acceleration;
    public Vector2 Velocity;
    public Vector2 Position;

    public Vector2 SpriteOrigin;

    public Weapon MainWeapon;
    public double Health;

    public Random random;

    public Ship(Texture2D texture)
    {
        Texture = texture;
        //ScreenSize = new Rectangle(0, 0, MainActivity.width, MainActivity.height);
        Rotation = 0.0f;

        SpriteOrigin = new Vector2(Texture.Width / 2, Texture.Height / 2);
    }
    public Ship(Vector2 location, Texture2D texture)
    {
        Position = location;
        Texture = texture;
        Acceleration = 0.75f;
        //ScreenSize = new Rectangle(0, 0, MainActivity.width, MainActivity.height);
        _Paint = XNAPaint.White();

        SpriteOrigin = new Vector2(Texture.Width / 2, Texture.Height / 2);

        Rotation = 0.0f;
        CollideRectangle = new Rectangle((int)location.X, (int)location.Y, texture.Width, texture.Height);
    }
    public Ship(Vector2 location, float acceletation, Texture2D texture) 
    {
        Position = location;
        Texture = texture;
        Acceleration = acceletation;
        //ScreenSize = new Rectangle(0, 0, MainActivity.width, MainActivity.height);
        _Paint = XNAPaint.White();

        SpriteOrigin = new Vector2(Texture.Width / 2, Texture.Height / 2);

        Rotation = 0.0f;
        CollideRectangle = new Rectangle((int)location.X, (int)location.Y, texture.Width, texture.Height);
    }
//    public Ship(Vector2 location, float acceletation, Texture2D texture, Rectangle screensize)
//    {
//        Position = location;
//        Texture = texture;
//        Acceleration = acceletation;
//        ScreenSize = screensize;
//        _Paint = XNAPaint.White();
//
//        SpriteOrigin = new Vector2(Texture.Width / 2, Texture.Height / 2);
//
//        Rotation = 0.0f;
//        CollideRectangle = new Rectangle((int)location.X, (int)location.Y, texture.Width, texture.Height);
//    }
    public Ship(Vector2 location, float acceletation, Texture2D texture, XNAPaint _paint)
    {
        Position = location;
        Texture = texture;
        Acceleration = acceletation;
        //ScreenSize = screensize;
        _Paint = _paint;

        SpriteOrigin = new Vector2(Texture.Width / 2, Texture.Height / 2);

        Rotation = 0.0f;
        CollideRectangle = new Rectangle((int)location.X, (int)location.Y, texture.Width, texture.Height);
    }

    public boolean CheckCollision(Rectangle rect)
    {
    	//UpdateCollisionRectangle();
    	
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
    
    public void ClampToScreen()
    {
        if (Position.X < 0)
        {
            //Position.X = -ScreenSize.Width / 2f;
            Position = new Vector2(0, Position.Y);
            if (Velocity.X < 0f)
            {
                //Velocity.X = 0f;
                Velocity = new Vector2(0f, Velocity.Y);
            }
        }

        if (Position.X > Screen.Width)
        {
            //Position.X = ScreenSize.Width / 2f;
            Position = new Vector2(Screen.Width, Position.Y);
            if (Velocity.X > 0f)
            {
                //Velocity.X = 0f;
                Velocity = new Vector2(0f, Velocity.Y);
            }
        }

        if (Position.Y < 0)
        {
            //Position.Y = -ScreenSize.Height / 2f;
            Position = new Vector2(Position.X, 0);
            if (Velocity.Y < 0f)
            {
                //Velocity.Y = 0f;
                Velocity = new Vector2(Velocity.X, 0f);
            }
        }

        if (Position.Y > Screen.Height)
        {
            //Position.Y = ScreenSize.Height / 2f;
            Position = new Vector2(Position.X, Screen.Height);
            if (Velocity.Y > 0f)
            {
                //Velocity.Y = 0f;
                Velocity = new Vector2(Velocity.X, 0f);
            }
        }
    }

    public void Update(GameTime gameTime)//GameTime gameTime)
    {
        // add our velocity to our position to move the ship
        Position = Vector2.Multiply(Velocity, gameTime.Time);//gameTime.ElapsedGameTime.Milliseconds;
        
        UpdateCollisionRectangle();
    }

    public Bullet Shoot()
    {
        return (new Bullet());
    }

    public void Draw(Canvas spriteBatch)
    {
    	Matrix matrix = new Matrix();
    	matrix.postRotate(Rotation);
    	matrix.postTranslate(Position.X, Position.Y);
    	
    	spriteBatch.drawBitmap(Texture.bitmap, matrix, _Paint.paint);
        //spriteBatch.Draw(Texture, Position, null, Colour, Rotation, new Vector2(Texture.Width / 2f, Texture.Height / 2f), 1f, SpriteEffects.None, 0f);
        //texture,
        //Position,
        //null,
        //Color.White,
        //Rotation,
        //new Vector2(texture.Width / 2f, texture.Height / 2f),
        //1f,
        //SpriteEffects.None,
        //0f);
    }
    
    public void Draw(Canvas spriteBatch, XNAPaint _paint)
    {
        spriteBatch.drawBitmap(Texture.bitmap, Position.X, Position.Y, _paint.paint);
    }

}
