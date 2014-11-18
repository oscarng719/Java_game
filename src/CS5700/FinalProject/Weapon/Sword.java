package CS5700.FinalProject.Weapon;

import CS5700.FinalProject.Status;

public class Sword extends Weapon
{
	static Status effect = new Status(-20, 0, -10);
	static String name = "Sword";
	
	public Sword() 
	{
		super(name, effect);
	}
}
