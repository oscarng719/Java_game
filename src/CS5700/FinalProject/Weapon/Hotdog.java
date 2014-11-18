package CS5700.FinalProject.Weapon;

import CS5700.FinalProject.Status;

public class Hotdog extends Weapon
{
	static Status effect = new Status(-5, -40, 30);
	static String name = "Hotdog";
	
	public Hotdog() 
	{
		super(name, effect);
	}
}
