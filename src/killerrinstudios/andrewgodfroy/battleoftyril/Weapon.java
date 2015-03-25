package killerrinstudios.andrewgodfroy.battleoftyril;

public class Weapon {
	public String Name;
	public double Speed;
	public double Damage;
	public int WeaponCounter;
	public Texture2D Texture;
	public Vector2 SpriteOrigin;
	
	public Weapon (String name, double speed, double damage, int weaponCtr, Texture2D texture)
	{
		Name = name;
		Speed = speed;
		Damage = damage;
		WeaponCounter = weaponCtr;
		Texture = texture;
		SpriteOrigin = new Vector2(Texture.GetWidth() / 2,
								   Texture.GetHeight() / 2);
	}
}
