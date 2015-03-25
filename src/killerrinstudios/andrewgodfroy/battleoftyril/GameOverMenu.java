package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;

public class GameOverMenu
{
    //private Texture2D modeSelectionTexture;
    private Menu modeScreen;

    private RenderText menuitem_gameOver_GoHome;

    public GameOverMenu()
    {
        //modeSelectionTexture = tex; //Assets.ModeSelectionTexture;
        //modeScreen = new Menu("Mode Selection", GameView.arcadeLevel001.GameOverBackground);

        menuitem_gameOver_GoHome = new RenderText("Main Menu", new Vector2(Screen.Width / 3f, 120f), XNAPaint.LimeGreen());
    }

    public void Update(GameTime gameTime)
    {
    	int i = 0;
        if (i == 2)//GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            GameView.gameState = GameState.MainMenu;
            GameView.touchPoints.ResetTouchpoints();
        }

        if (menuitem_gameOver_GoHome.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
        {
            GameView.gameState = GameState.MainMenu;
            GameView.touchPoints.ResetTouchpoints();
        }
    }

    public void Draw(Canvas spriteBatch)
    {
        //modeScreen.Draw(spriteBatch);
        spriteBatch.drawBitmap(GameView.arcadeLevel001.GameOverBackground.bitmap, 0, 0, XNAPaint.White().paint);
    	
    	RenderText.Draw(spriteBatch, "Game Over", new Vector2(Screen.Width / 4f, 20f), XNAPaint.DarkRed());
        
        menuitem_gameOver_GoHome.Draw(spriteBatch);
        RenderText.Draw(spriteBatch, "You Survived " + (GameView.playGameMenu.totalTimeAlive/1000f) + " Seconds\nfor a total of " + GameView.playGameMenu.wave + " Waves.", new Vector2(Screen.Width/6f, 200f), XNAPaint.Green());
    }
}