package killerrinstudios.andrewgodfroy.battleoftyril;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Canvas;

public class AIShip extends Ship
{
    enum AIState
    {
        Spawning,
        RandomMovement,
        Chasing
    }

    enum RandomMovementState
    {
        GetRandomPosition,
        MoveToRandomPosition
    }

    private AIState state;
    private RandomMovementState movementState;
    
    @SuppressWarnings("unused")
	private int shootctr;

    private float range;
    private Rectangle sightRange;

    private Vector2 randMovement;
    private boolean pathFindX;
    private boolean pathFindY;

    public int EnemyType;
           // Position = new Vector2(0, 0);
           // Acceleration = 0.75f;
           // _Paint = Color.White;
           // CollideRectangle = new Rectangle((int)Position.X, (int)Position.Y, texture.Width, texture.Height);

    public AIShip(Texture2D texture, Random r)
    {
    	super (texture);
    	
        random = r;

        //int temp = random.Next(0, 3);
        switch (random.nextInt(3))
        {
            case 0:
                //_Paint = new Color(100f, 100f, 90f, 100f);
                _Paint = XNAPaint.Silver();
                Acceleration = 0.40f;// 0.70f
                Health = 80.0f;
                range = 100f;
                EnemyType = 0;
                break;
            case 1:
                //_Paint = new Color(56f, 120f, 204f, 100f);
                _Paint = XNAPaint.Sienna();
                Acceleration = 0.35f;//0.65f
                Health = 70.0f;
                range = 50f;
                EnemyType = 1;
                break;
            case 2:
                //_Paint = new Color(169f, 19f, 19f, 100f);
                _Paint = XNAPaint.BlanchedAlmond();
                Acceleration = 0.20f;//0.60f
                Health = 50.0f;
                range = 40f;
                EnemyType = 2;
                break;
            default:
                _Paint = new XNAPaint(100, 255, 242, 67);
                Acceleration = 0.15f;//0.55f
                Health = 50.0f;
                range = 40f;
                EnemyType = 3;
                break;
        }

        //Acceleration = 25f;

        //temp = random.Next(0, 4);
        switch (random.nextInt(4))
        {
            case 0:
                Position = new Vector2(random.nextInt(226), random.nextInt((480 - texture.Height)));
                break;
            case 1:
                Position = new Vector2(574 + random.nextInt((800-texture.Width)), random.nextInt((480-texture.Height)));
                break;
            case 2:
                Position = new Vector2(random.nextInt((800 - texture.Width)), random.nextInt(140));
                break;
            case 3:
                Position = new Vector2(random.nextInt((800 - texture.Width)), 340 + random.nextInt((480-texture.Height)));
                break;
            default:
                Position = new Vector2(0, 0);
                break;
        }

        sightRange = new Rectangle ((int)(Position.X - range), (int)(Position.Y - range), (int)(range + Texture.Width + range), (int)(range + Texture.Height + range));
        CollideRectangle = new Rectangle((int)Position.X, (int)Position.Y, texture.Width, texture.Height);
        
        shootctr = 0;
        pathFindX = true;
        pathFindY = true;

        randMovement = Vector2.Zero();

        state = AIState.RandomMovement;
        movementState = RandomMovementState.GetRandomPosition;
    }

    @Override
    public void Update(GameTime gameTime)
    {
        sightRange.X = (int)(Position.X - range);
        sightRange.Y = (int)(Position.Y - range);

        if (state == AIState.Spawning) { SpawningUpdate(gameTime); }
        else if (state == AIState.RandomMovement) { RandomMovementUpdate(gameTime, GameView.playGameMenu.player); }
        else if (state == AIState.Chasing) { ChasePlayerUpdate(gameTime, GameView.playGameMenu.player); }

        super.Update(gameTime);
    }

    @Override
    public void Draw(Canvas spriteBatch)
    {
    	spriteBatch.drawBitmap(Texture.bitmap, Position.X, Position.Y,  _Paint.paint);
        //spriteBatch.Draw(Texture, Position, _Paint);
    }

    private void SpawningUpdate(GameTime gameTime)
    {
        /// To be added at a later date.
    }

    private void ChasePlayerUpdate(GameTime gameTime, PlayerShip player)
    {

        sightRange = new Rectangle((int)(Position.X - range), (int)(Position.Y - range), (int)(range + Texture.Width + range), (int)(range + Texture.Height + range));
        if (!sightRange.Contains(player.CollideRectangle)) { state = AIState.RandomMovement; RandomMovementUpdate(gameTime, player); return; }

        Vector2 temp = Vector2.Zero();

        float x = 0f;
        if (Position.X <= player.Position.X) { x = +Acceleration; }
        else if (Position.X >= player.Position.X) { x = -Acceleration; }
        temp.X = x;

        float y = 0f;
        if (Position.Y <= player.Position.Y) { y = +Acceleration; }
        else if (Position.Y >= randMovement.Y) { y = -Acceleration; }
        temp.Y = y;

        Velocity = temp;
    }

    private void RandomMovementUpdate(GameTime gameTime, PlayerShip player)
    {
        float movementPointRadiusCheck = 25f;

        sightRange = new Rectangle((int)(Position.X - range), (int)(Position.Y - range), (int)(range + Texture.Width + range), (int)(range + Texture.Height + range));
        if (sightRange.Contains(player.CollideRectangle))
        {
            state = AIState.Chasing;
            movementState = RandomMovementState.GetRandomPosition;
            ChasePlayerUpdate(gameTime, player);
            return;
        }

        if (movementState == RandomMovementState.GetRandomPosition) {
            randMovement = new Vector2(GameView.random.nextInt(Screen.Width), GameView.random.nextInt(Screen.Height));
            pathFindX = true;
            pathFindY = true;
            movementState = RandomMovementState.MoveToRandomPosition;
        }
        else if (movementState == RandomMovementState.MoveToRandomPosition) { 
        	if (sightRange.Contains(player.CollideRectangle)) { state = AIState.Chasing; ChasePlayerUpdate(gameTime, player); return; }

            Vector2 temp= Vector2.Zero();
            if (pathFindX)
            {
                float x = 0f;
                if (Position.X <= randMovement.X) { x = +Acceleration; }
                else if (Position.X >= randMovement.X) { x = -Acceleration; }

                temp.X = x;

                if (Position.X <= (randMovement.X + movementPointRadiusCheck) && Position.X >= randMovement.X - movementPointRadiusCheck) { pathFindX = false; }
            } else { temp.X = 0f; }
            if (pathFindY)
            {
                float y = 0f;
                if (Position.Y <= randMovement.Y) { y = +Acceleration; }
                else if (Position.Y >= randMovement.Y) { y = -Acceleration; }

                temp.Y = y;

                if (Position.Y <= (randMovement.Y + movementPointRadiusCheck) && Position.Y >= randMovement.Y - movementPointRadiusCheck) { pathFindY = false; }
            } else { temp.Y = 0f; }

            Velocity = temp;
            if (!pathFindX && !pathFindY) { movementState = RandomMovementState.GetRandomPosition; }
        }
    }

    //------------------- Draws -------------------\\
    @SuppressWarnings("unused")
	private void SpawningDraw(Canvas spriteBatch)
    {

    }

    @SuppressWarnings("unused")
	private void ChasePlayerDraw(Canvas spriteBatch)
    {

    }

    @SuppressWarnings("unused")
	private void RandomMovementDraw(Canvas spriteBatch)
    {

    }

    public static ArrayList<AIShip> SpawnX(Texture2D texture, int numberOfShips)
    {
    	ArrayList<AIShip> temp = new ArrayList<AIShip>();

        for (int i = 0; i < numberOfShips; i++)
        {
            temp.add(new AIShip(texture, GameView.random));
        }

        return (temp);
    }

	public boolean CheckPixelPerfectColision(PlayerShip player) {
		if (CheckCollision(player.CollideRectangle)) { 
			return true;
		}
		
		return false;
	}

	public boolean CheckPixelPerfectColision(Bullet bullet) {
		if (CheckCollision(bullet.CollideRectangle)) { 
			return true;
		}
		
		return false;
	}
}
