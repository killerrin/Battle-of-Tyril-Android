package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;

public class OptionsMenu
{
    private Texture2D optionsTexture;

    public Menu optionScreen;

    public OptionsMenu(Texture2D tex)
    {
        optionsTexture = tex; //Assets.OptionsMenuTexture;
        optionScreen = new Menu("Options", optionsTexture);
    }

	public void Update(GameTime gameTime)
    {
		int i = 0;
        if (i == 2)//(GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            GameView.gameState = GameState.MainMenu;
            GameView.touchPoints.ResetTouchpoints();
        }
    }

    public void Draw (Canvas spriteBatch)
    {
        optionScreen.Draw(spriteBatch);
    }
}
