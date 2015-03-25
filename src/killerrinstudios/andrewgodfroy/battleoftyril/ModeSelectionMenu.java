package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;

public class ModeSelectionMenu
{
    //private Texture2D modeSelectionTexture;
    private Menu modeScreen;

    private RenderText menuitem_modeselection_Arcade;

    public ModeSelectionMenu()
    {
        //modeSelectionTexture = tex; //Assets.ModeSelectionTexture;
        modeScreen = new Menu("Mode Selection", Assets.ArcadeModeTexture);

        menuitem_modeselection_Arcade = new RenderText("Arcade", new Vector2(80.0f, 220.0f), XNAPaint.LimeGreen()); // (80.0f, 220.0f)
    }

    public void Update(GameTime gameTime)
    {
    	int i = 0;
        if (i == 2)//GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            GameView.gameState = GameState.MainMenu;
            GameView.touchPoints.ResetTouchpoints();
        }

        if (menuitem_modeselection_Arcade.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
        {
            GameView.gameState = GameState.ArcadeSelection;
            GameView.touchPoints.ResetTouchpoints();
        }
    }

    public void Draw (Canvas spriteBatch)
    {
        modeScreen.Draw(spriteBatch);
        menuitem_modeselection_Arcade.Draw(spriteBatch);
    }
}