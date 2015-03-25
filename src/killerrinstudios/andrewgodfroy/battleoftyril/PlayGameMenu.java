package killerrinstudios.andrewgodfroy.battleoftyril;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;

@SuppressWarnings("unused")
public class PlayGameMenu
{
    private PlayMode playMode;

    private Menu pauseScreen;

    private Texture2D playership01;
    private Texture2D playership02;
    private Texture2D enemyship01;
    private Texture2D thumbstick;
    private Texture2D plasmaBoltTexture;
    
    private MediaPlayer gameSong;
    private MediaPlayer pauseSound;
    //private Song pausedSong;

    private boolean isMusicPlaying;

    private RenderText menuitem_pause_Resume;
    private RenderText menuitem_pause_EndGame;
    private RenderText menuitem_pause_ExitGame;
    //private RenderText menuitem_pause_optionsandhelp;
    //private RenderText menuitem_pause_options;

    public Level currentLevel;
    private ArrayList<Bullet> playerBullets;
    private ArrayList<AIShip> aiShips;
    private Weapon plasmaBolt;
    public PlayerShip player;

    double CRASH_HEALTH_CONST = 10;

    float aiSpawnTimer;
    public int wave = 0;
    public float totalTimeAlive = 0f;
    float MAX_AI_SPAWN_TIMER = 5000;
    public Boolean CloseGame;

    public PlayGameMenu()
    {
        currentLevel = GameView.arcadeLevel001;
        PlayMode playMode = PlayMode.Playing;
        
        CloseGame = false;
        isMusicPlaying = false;

        wave = 0;
        aiSpawnTimer = 5000f;
        totalTimeAlive = 0f;

        ///-- Audio --\\\
        //pauseSound = Assets.PauseSoundEffect;//Content.Load<SoundEffect>("Music/Pause Ambience");
        //pauseSound.setLooping(true);
        
        //gameSong = Assets.GameMusic;//Content.Load<Song>("Music/Flight of the Crow");
        //gameSong.setLooping(true);


        ///-- Ships ---\\\
        playership01 = Assets.PlayerShip01Texture;//Content.Load<Texture2D>("Images/Ships/Players/001");
        playership02 = Assets.PlayerShip02Texture;//Content.Load<Texture2D>("Images/Ships/Players/002");
        enemyship01 = Assets.EnemyShip01Texture;//Content.Load<Texture2D>("Images/Ships/Enemies/001");

        ///-- Weapons ---\\\
        plasmaBoltTexture = Assets.PlasmaBoltTexture;//Content.Load<Texture2D>("Images/Weapons/PlasmaBolt/Plasma");

        ///-- Images --\\\
        thumbstick = Assets.ThumbsticksTexture;//Content.Load<Texture2D>("Images/ect/thumbstick");

        ///-- Menus --\\\
        pauseScreen = new Menu("Pause", Assets.Level001BackgroundTexture); // blankTexture

        ///-- Text --\\\
        menuitem_pause_Resume = new RenderText("Resume", new Vector2(200, 220), XNAPaint.LimeGreen());
        menuitem_pause_EndGame = new RenderText("End Level", new Vector2(50, 300), XNAPaint.LimeGreen());
        menuitem_pause_ExitGame = new RenderText("Exit Game", new Vector2(170, 380), XNAPaint.LimeGreen());
        //menuitem_pause_optionsandhelp = new RenderText("How to Play", new Vector2(300, 300), XNAPaint.LimeGreen()); //Help & Options
        //menuitem_pause_options = new RenderText("Options", new Vector2(550, 400), XNAPaint.LimeGreen());

        ///-- Weapons --\\\
        ///                         NAME       SPEED DAM CTR       TEXTURE    
        plasmaBolt = new Weapon("Plasma Bolt", 30.0, 1.0, 3, plasmaBoltTexture);

        ///-- Player --\\\
        player = new PlayerShip(new Vector2(Screen.Width / 2, Screen.Height / 2), 0.03f, playership01, XNAPaint.White());
        player.MainWeapon = plasmaBolt;

        ///-- Other --\\\
        playerBullets = new ArrayList<Bullet>();
        aiShips = new ArrayList<AIShip>();
        //foreach (AIShip i in AIShip.SpawnX(enemyship01, 10))
        //{
        //    aiShips.add(i);
        //}
    }

    public void UpdateTouchPoints(MotionEvent e)
    {
        //-- Update the Thumbsticks
        VirtualThumbsticks.Update(e);
    }
    
    public void Update(GameTime gameTime)
    {
    	if (playMode == PlayMode.Playing) PlayingUpdate(gameTime);
    	else if (playMode == PlayMode.Paused) PausedUpdate(gameTime);
    	else if (playMode == PlayMode.HelpAndOptions) HelpAndOptionsUpdate(gameTime);
    	else if (playMode == PlayMode.Options) OptionsUpdate(gameTime);
    }

    private void HelpAndOptionsUpdate(GameTime gameTime)
    {
    	int i = 0;
        if (i == 2)//GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            playMode = PlayMode.Paused;
            GameView.touchPoints.ResetTouchpoints();
        }

        //if (menuitem_pause_options.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
        //{
        //    //playMode = PlayMode.Options;
        //    //GameView.touchPoints.ResetTouchpoints();
        //}
    }

    private void OptionsUpdate(GameTime gameTime)
    {
    	int i = 0;
        if (i == 2)//GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            playMode = PlayMode.HelpAndOptions;
            GameView.touchPoints.ResetTouchpoints();
        }
    }

    private void PausedUpdate(GameTime gameTime)
    {
    	int i = 0;
        if (i == 2)//GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            playMode = PlayMode.Playing;
            GameView.touchPoints.ResetTouchpoints();
            if (GameView.isMusicCurrentlyPlaying)
            {
                gameSong.start();//MediaPlayer.Resume();
                pauseSound.pause();//pauseSoundInstance.Stop();
            }
        }

        if (menuitem_pause_Resume.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
        {
            GameView.touchPoints.ResetTouchpoints();
            playMode = PlayMode.Playing;
            if (GameView.isMusicCurrentlyPlaying)
            {
                gameSong.start();//MediaPlayer.Resume();
                pauseSound.pause();//pauseSoundInstance.Stop();
            }
        }
        else if (menuitem_pause_EndGame.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
        {
            playMode = PlayMode.Playing;
            GameView.gameState = GameState.MainMenu;
            isMusicPlaying = false;
            if (GameView.isMusicCurrentlyPlaying) { gameSong.stop();pauseSound.stop(); }//MediaPlayer.Stop(); }
            
            GameView.touchPoints.ResetTouchpoints();
        }
        //else if (menuitem_pause_optionsandhelp.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
        //{
        //    playMode = PlayMode.HelpAndOptions;
        //    GameView.touchPoints.ResetTouchpoints();
        //}
        else if (menuitem_pause_ExitGame.CollideRectangle.Contains(GameView.touchPoints.LastTapped))
        {
        	CloseGame = true;
            GameView.touchPoints.ResetTouchpoints();
        }
    }

    private void PlayingUpdate(GameTime gameTime)
    {
        if (GameView.isMusicCurrentlyPlaying)
        {
            if (!isMusicPlaying)
            {
                isMusicPlaying = true;
                gameSong.start();//MediaPlayer.Resume();
                pauseSound.pause();//pauseSoundInstance.Stop();
            }
        }

        totalTimeAlive += gameTime.ElapsedGameTime.Milliseconds;
        aiSpawnTimer -= gameTime.ElapsedGameTime.Milliseconds;
        if (aiSpawnTimer <= 0)
        {
            aiSpawnTimer = MAX_AI_SPAWN_TIMER;
            aiShips.addAll(AIShip.SpawnX(enemyship01, 10)); // aiShips.AddRange(AIShip.SpawnX(enemyship01, 10));
            ++wave;
        }

        int i = 0;
        if (i == 2)//GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
        {
            //GameView.AdAdd = true;
            playMode = PlayMode.Paused;
            GameView.touchPoints.ResetTouchpoints();
            if (GameView.isMusicCurrentlyPlaying)
            {
                gameSong.pause();//MediaPlayer.Resume();
                pauseSound.start();//pauseSoundInstance.Stop();
            }
        }
        
        

        player.Update(gameTime);

        for (AIShip ais : aiShips)
        {
            ais.Update(gameTime);
        }

        if (VirtualThumbsticks.RightThumbstick() != Vector2.Zero())
        {
            Bullet bullet = player.Shoot();
            GameView.debugtext = "Shooting Bullet";

            if (bullet.DeathCounter == 0)
            {
                GameView.debugtext = "Bullet Added to List";
                playerBullets.add(bullet);
            }
        }
        
        for (Bullet pb : playerBullets)
        {
            pb.Update(gameTime);
        }

        
        while (true)
        {
            boolean exit = true;
            for (int ais = 0; ais < aiShips.size(); ais++)
            {
                if (aiShips.get(ais).CheckPixelPerfectColision(player))
                {
                    exit = false;
                    player.Health -= CRASH_HEALTH_CONST;
                    aiShips.remove(ais);
                    break;
                }
                if (exit == false) { break; }
                for (int x = 0; x < playerBullets.size(); x++)
                {
                    if (aiShips.get(ais).CheckPixelPerfectColision(playerBullets.get(x)))
                    {

                        exit = false;
                        playerBullets.remove(x);
                        aiShips.remove(ais);
                        break;
                    }
                    if (exit == false) { break; }
                }
            }

            if (exit == true) { break;}
        }

        if (player.Health <= 0)
        {
            playMode = PlayMode.Playing;
            GameView.gameState = GameState.GameOver;
            isMusicPlaying = false;
            if (GameView.isMusicCurrentlyPlaying) { gameSong.stop(); pauseSound.stop(); }
        }
    }

    private void PlayingDraw(Canvas spriteBatch)
    {
        currentLevel.Draw(spriteBatch);

        DrawThumbsticks(spriteBatch);
        player.Draw(spriteBatch);

        for (AIShip ais : aiShips)
        {
            ais.Draw(spriteBatch);
        }

        for (Bullet pb : playerBullets)
        {
            pb.Draw(spriteBatch);
        }

        if (GameView.developmentMode)
        {
            RenderText.Draw(spriteBatch, "Health \n    " + player.Health, new Vector2(Screen.Width / 2.5f, 25f), XNAPaint.Green());
        }
        else
        {
            RenderText.Draw(spriteBatch, "Health \n    " + player.Health, new Vector2(35, 25f), XNAPaint.Green());
        }
        RenderText.Draw(spriteBatch, "Next Wave \n    " + (aiSpawnTimer/1000), new Vector2(Screen.Width / 1.5f, 25f), XNAPaint.Green());
    
    
    }

    private void PausedDraw(Canvas spriteBatch)
    {
        RenderText.Draw(spriteBatch, "GAME PAUSED", new Vector2(150, 100), XNAPaint.Silver());
        menuitem_pause_Resume.Draw(spriteBatch);
        menuitem_pause_EndGame.Draw(spriteBatch);
        menuitem_pause_ExitGame.Draw(spriteBatch);
        //menuitem_pause_optionsandhelp.Draw(spriteBatch);
    }

    private void HelpAndOptionsDraw(Canvas spriteBatch)
    {
        //menuitem_pause_options.Draw(spriteBatch);

        if (GameView.fixedthumbstick == true)
        {
            RenderText.Draw(spriteBatch, "Press, hold and rotate the left thumbstick to move", new Vector2(50, 100), XNAPaint.White());
            RenderText.Draw(spriteBatch, "Press, hold and rotate the right thumbstick to shoot", new Vector2(50, 120), XNAPaint.White());
        }
        else
        {
            RenderText.Draw(spriteBatch, "Press and hold on the left/right sides of the screen to bring up", new Vector2(50, 100), XNAPaint.White());
            RenderText.Draw(spriteBatch, "the thumbsticks. LeftThumbstick to move. RightThumbstick to shoot", new Vector2(50, 120), XNAPaint.White());
        }
        //RenderText.Draw(spriteBatch, "Pick up powerups for temperary upgrades by flying into them", new Vector2(50, 150), XNAPaint.White(), GameView.segoeFont);
        //RenderText.Draw(spriteBatch, "Kill enemies for Credits", new Vector2(50, 180), XNAPaint.White(), GameView.segoeFont);
        //RenderText.Draw(spriteBatch, "Use Credits to upgrade your ship in the level selection menu.", new Vector2(50, 210), XNAPaint.White(), GameView.segoeFont);
        //RenderText.Draw(spriteBatch, "Above all else... DONT DIE!", new Vector2(200, 240), XNAPaint.White(), GameView.segoeFont);
    }

    private void OptionsDraw(Canvas spriteBatch)
    {
        //GameView.optionsMenu.optionScreen.Draw(spriteBatch);
    }

    private void DrawThumbsticks(Canvas spriteBatch)
    {
    	VirtualThumbsticks.Draw(spriteBatch);
//        if (VirtualThumbsticks.LeftThumbstickCenter != null)
//        {
//            spriteBatch.Draw(
//                thumbstick,
//                VirtualThumbsticks.LeftThumbstickCenter.Value - new Vector2(thumbstick.Width / 2f, thumbstick.Height / 2f),
//                XNAPaint.White());
//        }
//
//        if (VirtualThumbsticks.RightThumbstickCenter != null)
//        {
//            spriteBatch.Draw(
//                thumbstick,
//                VirtualThumbsticks.RightThumbstickCenter.Value - new Vector2(thumbstick.Width / 2f, thumbstick.Height / 2f),
//                XNAPaint.White());
//        }
    }

    public void Draw (Canvas spriteBatch)
    {
    	if (playMode == PlayMode.Playing) PlayingDraw(spriteBatch);
    	else if (playMode == PlayMode.Paused) PausedDraw(spriteBatch);
    	else if (playMode == PlayMode.HelpAndOptions) HelpAndOptionsDraw(spriteBatch);
    	else if (playMode == PlayMode.Options) OptionsDraw(spriteBatch);
    }
}
