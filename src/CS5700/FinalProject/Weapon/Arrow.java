package CS5700.FinalProject.Weapon;

import CS5700.FinalProject.Status;

public class Arrow extends Weapon
{
	static Status effect = new Status(-5, -40, 30);
	static String name = "Arrow";
	
	public Arrow() 
	{
		super(name, effect);
	}
}
