package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;
import android.media.MediaPlayer;

public class MainMenu
{
    //private Texture2D titleScreenTexture;
    //public MediaPlayer song;

    public boolean isMusicPlaying;

    private Menu titleScreen;

    private RenderText menuitem_startmenu_Play;
    private RenderText menuitem_startmenu_ExitGame;

    public Boolean CloseGame;

    public MainMenu(Texture2D tex)
    {
        //titleScreenTexture = tex;
        //song = Assets.MainMenuMusic; //Content.Load<Song>("Music/Rise to the King");

        titleScreen = new Menu("Title Screen", tex, true);

        menuitem_startmenu_Play = new RenderText("Play", new Vector2(50.0f, 140.0f), XNAPaint.LimeGreen());
        menuitem_startmenu_ExitGame = new RenderText("Exit Game", new Vector2(50.0f, 220.0f), XNAPaint.LimeGreen()); //(50.0f, 300.0f)

        CloseGame = false;
        isMusicPlaying = false;
    }

    public void Update(GameTime gameTime)
    {
    	synchronized(GameView.holder) {
	        if (GameView.isMusicCurrentlyPlaying)
	        {
	            if (!isMusicPlaying)
	            {
	                isMusicPlaying = true;
	                //MediaPlayer.IsRepeating = true;
	                Assets.MainMenuMusic.setLooping(true);
	                Assets.MainMenuMusic.start();
	            }
	        }
	
	        if (menuitem_startmenu_Play.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
	        {
	        	isMusicPlaying = false;
	            GameView.gameState = GameState.PlayGame; //GameState.ArcadeSelection; // Go to Mode Select Screen
	            GameView.touchPoints.ResetTouchpoints();
	        }
	        else if (menuitem_startmenu_ExitGame.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
	        {
	            CloseGame = true;
	        }
    	}
    }

    public void Draw (Canvas spriteBatch)
    {
    	synchronized(GameView.holder) {
	        titleScreen.Draw(spriteBatch);
	        menuitem_startmenu_Play.Draw(spriteBatch);
	        menuitem_startmenu_ExitGame.Draw(spriteBatch);
    	}
    }
}