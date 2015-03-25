package killerrinstudios.andrewgodfroy.battleoftyril;

import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {
	static Random random = new Random();
	static int Width;
	static int Height;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        Assets.MainMenuMusic = MediaPlayer.create(getApplicationContext(), R.raw.risetotheking);
    	Assets.GameMusic = MediaPlayer.create(getApplicationContext(), R.raw.flightofthecrow);
    	Assets.PauseSoundEffect = MediaPlayer.create(getApplicationContext(), R.raw.pauseambience);
    	
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        Width = dimension.widthPixels;
        Height = dimension.heightPixels; 
        Screen.SetScreen(Width, Height);
        
        setContentView(new GameView(this));      
	}

	@Override
	protected void onResume() {		
		super.onResume();
		
    	Assets.MainMenuMusic = MediaPlayer.create(getApplicationContext(), R.raw.risetotheking);
    	Assets.GameMusic = MediaPlayer.create(getApplicationContext(), R.raw.flightofthecrow);
    	Assets.PauseSoundEffect = MediaPlayer.create(getApplicationContext(), R.raw.pauseambience);
	}
	
	@Override
	protected void onPause() {
	   super.onPause();
	   
		//Assets.MainMenuMusic.release();
	   	//Assets.GameMusic.release();
	   	//Assets.PauseSoundEffect.release();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
