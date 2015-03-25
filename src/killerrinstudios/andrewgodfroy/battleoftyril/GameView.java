package killerrinstudios.andrewgodfroy.battleoftyril;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressWarnings("unused")
public class GameView extends SurfaceView {    
    public static SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private GameTime gameTime;
    
    private float splashScreenTimer = 0;
    private float SPLASH_SCREEN_TIMER = 500f; // 1000 = 1 second
    
    // statics
   
    public static boolean developmentMode = true;
    public static boolean isMusicCurrentlyPlaying = false;
    public static boolean fixedthumbstick = false;
    public static boolean AdAdd = true; // Only here to delete errors
    
    public static String debugtext = "";
    
    public static Random random;
    public static Touchpoints touchPoints;
    public static GameState gameState;
    
    public static MainMenu mainMenu;
    //public static ArcadeModeMenu arcadeModeMenu;
    public static PlayGameMenu playGameMenu;
    public static GameOverMenu gameOverMenu;
    
    public static Level arcadeLevel001;
    //public static Level arcadeLevel002;
    
    public GameView(Context context) {
          super(context);
          
          LoadContent();
          
          gameLoopThread = new GameLoopThread(this);
          holder = getHolder();
          
          holder.addCallback(new SurfaceHolder.Callback() {
             @Override
             public void surfaceDestroyed(SurfaceHolder holder) {
                    boolean retry = true;
                    gameLoopThread.setRunning(false);
                    while (retry) {
                           try {
                                 gameLoopThread.join();
                                 retry = false;
                           } catch (InterruptedException e) {
                           }
                    }
             }
             @Override
             public void surfaceCreated(SurfaceHolder holder) {
                    gameLoopThread.setRunning(true);
                    gameLoopThread.start();
             }
             @Override
             public void surfaceChanged(SurfaceHolder holder, int format,
                           int width, int height) {
             }
          });
    }
    
    public void LoadContent()
    {
    	Assets.TouchPointsTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.touch));  	
    	Assets.ThumbsticksTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.thumbstick));
    	Assets.KillerrinStudiosLogo = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.killerrinstudios));
    	Assets.KillerrinStudiosLogo.Position = Vector2.Subtract(Screen.GetCenter(), Assets.KillerrinStudiosLogo.GetOrigin());
    	
    	Assets.PlayerShip01Texture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.ship001));
    	Assets.PlayerShip02Texture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.ship002));
    	Assets.EnemyShip01Texture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.enemy001));
    	
    	Assets.PlasmaBoltTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.plasma));
    	
    	//Assets.Level001BackgroundTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level1));
    	//Assets.Level001GameoverTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level1gameoverdeath));
    	//Assets.Level001WinTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level1gameovertimeup));
    	
    	//Assets.Level002BackgroundTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level2));
    	//Assets.Level002GameoverTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level2gameoverdeath));
    	//Assets.Level002WinTexture = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level2gameovertimeup));
    	
    	//-------------------------------------------------
    	//-------------------------------------------------
    	random = new Random();
    	gameTime = new GameTime(0.33f);
    	gameState = GameState.MainMenu;
    	
    	touchPoints = new Touchpoints(Assets.TouchPointsTexture); //touchPoints = new Touchpoints(new XNAPaint(255,0,255,0), new Vector2(50,50));
    	
    	VirtualThumbsticks.Texture = Assets.ThumbsticksTexture;
    	VirtualThumbsticks._Paint = XNAPaint.White();
    	
    	//--Levels--\\
        arcadeLevel001 = new Level("Tyril", new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level1)), new Vector2(0, 0), XNAPaint.White());
        arcadeLevel001.GameOverBackground = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level1gameoverdeath));
        arcadeLevel001.GameWinBackground = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level1gameovertimeup));
        //arcadeLevel001.ThumbOutline = blankTexture;

        //arcadeLevel002 = new Level("Sal'Dero", new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level2)), new Vector2(0, 0), XNAPaint.White());
        //arcadeLevel002.GameOverBackground = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level2gameoverdeath));
        //arcadeLevel002.GameWinBackground = new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.level2gameovertimeup));;
        //arcadeLevel002.ThumbOutline = blankTexture;
    	
        System.out.println("LoadContent: Assets Loaded");
    	
    	//--Menus--\\
        mainMenu = new MainMenu(new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.titlescreen)));
        System.out.println("LoadContent: MainMenu Loaded");
        
        playGameMenu = new PlayGameMenu();
        System.out.println("LoadContent: PlayGameMenu Loaded"); 
        
        //arcadeModeMenu = new ArcadeModeMenu(new Texture2D(BitmapFactory.decodeResource(getResources(), R.drawable.playerselection)));
        System.out.println("LoadContent: ArcadeModeMenu Loaded");
        
        gameOverMenu = new GameOverMenu();
        System.out.println("LoadContent: GameOverMenu Loaded");
    }
    
    @SuppressLint("WrongCall")
	@Override
    protected void onDraw(Canvas canvas) {
    	Update(gameTime);
    	Draw(canvas);
    }
    
    @Override 
    public boolean onTouchEvent(MotionEvent event) { 
    	synchronized(getHolder()) {
    		if (gameState == GameState.SplashScreen) { gameState = GameState.MainMenu; }
    		
    		int numberOfTouches = event.getPointerCount();
            int ctr = 0;
            
            
            //Clear the coordinates
            touchPoints.RectCoords.clear();
            
            // Loop through every touch event and process it
            for (int i = 0; i < numberOfTouches; i++) {
            	int xTouch = (int) event.getX(event.getPointerId(i));
            	int yTouch = (int) event.getY(event.getPointerId(i));
            	Vector2 touch = new Vector2(xTouch, yTouch);
       
            	touchPoints.Update(touch);
            } 
            
        	VirtualThumbsticks.Update(event);
    	}
    	
    	return true;
    }
    
    protected void Update(GameTime _gameTime)
    {
    	synchronized(getHolder()) {
    		if (gameState == GameState.None) {}
    		else if (gameState == GameState.SplashScreen) {
    			UpdateSplash();
    		}
    		else if (gameState == GameState.MainMenu) { 
                //if (mainMenu.CloseGame) { }
                mainMenu.Update(gameTime);
    		}
    		//else if (gameState == GameState.ArcadeSelection) {
    		//	arcadeModeMenu.Update(gameTime);
    		//}
    		else if (gameState == GameState.PlayGame) {
    			//if (playGameMenu.CloseGame) { }
                playGameMenu.Update(gameTime);
    		}
    		else if (gameState == GameState.GameOver) { 
    			gameOverMenu.Update(gameTime);
    		}
    		
            if (developmentMode == true)
            {
                
            }
    	}
    }
    
    protected void Draw(Canvas canvas)
    {
    	synchronized(getHolder()) {
        	// Clear the screen
        	canvas.drawColor(Color.BLACK);
        	
        	//canvas.rotate(90);
        	
        	if (gameState == GameState.None) {}
    		else if (gameState == GameState.SplashScreen) { 
    			DrawSplash(canvas);
    		}
    		else if (gameState == GameState.MainMenu) { 
    			mainMenu.Draw(canvas);
    		}
    		//else if (gameState == GameState.ArcadeSelection) { 
    		//	arcadeModeMenu.Draw(canvas);
    		//}
    		else if (gameState == GameState.PlayGame) { 
    			playGameMenu.Draw(canvas);
    		}
    		else if (gameState == GameState.GameOver) { 
    			gameOverMenu.Draw(canvas);
    		}
        	
        	if (developmentMode) {
            	touchPoints.Draw(canvas);
            	
        		RenderText.Draw(canvas,
        						"Touch X: "+ touchPoints.LastTappedX + " Touch Y: " + touchPoints.LastTappedY,
        						new Vector2(10,10),
        						new XNAPaint(255,255,0,0));
        		
        		if (VirtualThumbsticks.LeftThumbstickCenter != null) {
	        		RenderText.Draw(canvas,
							"Left Thumbstick ID: "+ VirtualThumbsticks.leftId,
							new Vector2(10,30),
							new XNAPaint(255,255,0,0));
	        		RenderText.Draw(canvas,
							" Left Touch Center: " + VirtualThumbsticks.LeftThumbstickCenter.X + " , " + VirtualThumbsticks.LeftThumbstickCenter.Y ,
							new Vector2(10,40),
							new XNAPaint(255,255,0,0));
	        		RenderText.Draw(canvas,							
							"Left Touch Location: " + VirtualThumbsticks.LeftThumbstick().X + " , " + VirtualThumbsticks.LeftThumbstick().Y,
							new Vector2(10,50),
							new XNAPaint(255,255,0,0));
        		}
        		if (VirtualThumbsticks.RightThumbstickCenter != null) {
	        		RenderText.Draw(canvas,
							"Right Thumbstick ID: "+ VirtualThumbsticks.rightId,
							new Vector2(10,70),
							new XNAPaint(255,255,0,0));
	        		RenderText.Draw(canvas,
							" Right Touch Center: " + VirtualThumbsticks.RightThumbstickCenter.X + " , " + VirtualThumbsticks.RightThumbstickCenter.Y,
							new Vector2(10,80),
							new XNAPaint(255,255,0,0));
	        		RenderText.Draw(canvas,
							" Right Touch Location: " + VirtualThumbsticks.RightThumbstick().X + " , " + VirtualThumbsticks.RightThumbstick().Y,
							new Vector2(10,90),
							new XNAPaint(255,255,0,0));
        		}
        	}
    	}
    }
    
    public void UpdateSplash ()
    {
    	synchronized(getHolder()) {
    		splashScreenTimer += 0.33f; //gameTime.ElapsedGameTime.Milliseconds;
        	
    		if (splashScreenTimer >= SPLASH_SCREEN_TIMER) {
        		gameState = GameState.None;
        		splashScreenTimer = 0;
        	}
    	}
    }
    public void DrawSplash (Canvas spriteBatch)
    {
    	synchronized(getHolder()) {
    		Assets.KillerrinStudiosLogo.Draw(spriteBatch);
    		
    		//Vector2 position = Vector2.Subtract(Screen.GetCenter(),Assets.KillerrinStudiosLogo.GetOrigin());
    		//spriteBatch.drawBitmap(Assets.KillerrinStudiosLogo.bitmap, position.X, position.Y, XNAPaint.White().paint);
    	}
    }
}