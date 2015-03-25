package killerrinstudios.andrewgodfroy.battleoftyril;

public class GameTime {
	float Time;
	EllapsedGameTime ElapsedGameTime;
	
	public GameTime(float time)
	{
		Time = time;
		
	}
	
	protected class EllapsedGameTime
	{
		public float Milliseconds;
		
		public EllapsedGameTime(float time)
		{
			Milliseconds = time;
		}
	}
}
