package killerrinstudios.andrewgodfroy.battleoftyril;

import android.graphics.Canvas;

public class ArcadeModeMenu
{
    //private Texture2D arcadeModeTexture;

    private Menu arcadeScreen;
    private RenderText menuitem_arcadeselection_StartGame;

    public ArcadeModeMenu(Texture2D arcTex)
    {
        //arcadeModeTexture = arcTex; //Assets.ArcadeModeTexture;//Content.Load<Texture2D>("Images/Backgrounds/Menus/Player Selection");

        arcadeScreen = new Menu("Arcade", arcTex);
        menuitem_arcadeselection_StartGame = new RenderText("Start Game", new Vector2(350.0f, 400.0f), XNAPaint.Green());

        GameView.playGameMenu.currentLevel = GameView.arcadeLevel001;// new Level("Tyril", level001Background, new Vector2(0, 0), Color.White, false);
    }

    public void Update(GameTime gameTime)
    {
    	int i = 0;
        if (i == 2)//GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            GameView.gameState = GameState.MainMenu; // Put mode back to ModeSelect
            GameView.touchPoints.ResetTouchpoints();
        }

        if (menuitem_arcadeselection_StartGame.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
        {
        	GameView.gameState = GameState.PlayGame;
        	GameView.touchPoints.ResetTouchpoints();

        	GameView.playGameMenu = new PlayGameMenu();

            if (GameView.arcadeLevel001.Activated)
            {
            	GameView.playGameMenu.currentLevel = GameView.arcadeLevel001;
            }
            //else if (GameView.arcadeLevel002.Activated)
            //{
            //	GameView.playGameMenu.currentLevel = GameView.arcadeLevel002;
            //}
            else// if (BoT.playGameMenu.currentLevel == null)
            {
                //Debug.WriteLine("No Level Selected... Defaulting.");
            	GameView.playGameMenu.currentLevel = GameView.arcadeLevel001;
            }

            GameView.arcadeLevel001.Activated = false;
            //GameView.arcadeLevel002.Activated = false;

            //Debug.WriteLine("Loading Level: " + BoT.playGameMenu.currentLevel.Name.ToString());
            GameView.mainMenu.isMusicPlaying = false;
            
            //if (GameView.isMusicCurrentlyPlaying) { song.Stop(); }
            //GameView.AdRemove = true;
        }
        else if (GameView.arcadeLevel001.ThumbRectangle.Contains(GameView.touchPoints.LastTapped))
        {
        	GameView.playGameMenu.currentLevel = GameView.arcadeLevel001;
        	GameView.arcadeLevel001.Activated = true;
        	//GameView.arcadeLevel002.Activated = false;
            //Debug.WriteLine(BoT.playGameMenu.currentLevel.Name.ToString() + " Selected");
        }
        //else if (GameView.arcadeLevel002.ThumbRectangle.Contains(GameView.touchPoints.LastTapped))
        //{
        //	GameView.playGameMenu.currentLevel = GameView.arcadeLevel002;
        //	GameView.arcadeLevel001.Activated = false;
        // 	//GameView.arcadeLevel002.Activated = true;
        //     //Debug.WriteLine(BoT.playGameMenu.currentLevel.Name.ToString() + " Selected");
        //}
    }

    public void Draw (Canvas spriteBatch)
    {
        arcadeScreen.Draw(spriteBatch);
        GameView.arcadeLevel001.DrawThumbnail(spriteBatch, new Vector2(200, 200), 0.15f);
        //GameView.arcadeLevel002.DrawThumbnail(spriteBatch, new Vector2(400, 200), 0.15f);
        menuitem_arcadeselection_StartGame.Draw(spriteBatch);
    }
}