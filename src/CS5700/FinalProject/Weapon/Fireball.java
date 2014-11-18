package CS5700.FinalProject.Weapon;

import CS5700.FinalProject.Status;

public class Fireball extends Weapon
{
	static Status effect = new Status(-40, 30, -10);
	static String name = "FireBall";
	
	public Fireball() 
	{
		super(name, effect);
	}
	
}
