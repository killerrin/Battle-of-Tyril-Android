package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;

public class CampaignModeMenu
{
    private Texture2D campaignModeTexture;

    private Menu campaignScreen;

    public CampaignModeMenu()
    {
        campaignModeTexture = Assets.ArcadeModeTexture;

        campaignScreen = new Menu("Campaign", campaignModeTexture);
    }

    public void Update(GameTime gameTime)
    {
    	int i = 0;
        if (i == 3)//(GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            GameView.gameState = GameState.MainMenu;
            GameView.touchPoints.ResetTouchpoints();
        }
    }

    public void Draw (Canvas spriteBatch)
    {
        campaignScreen.Draw(spriteBatch);
    }
}