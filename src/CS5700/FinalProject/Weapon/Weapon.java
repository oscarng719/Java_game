package CS5700.FinalProject.Weapon;
import CS5700.FinalProject.Status;

public abstract class Weapon 
{
	private Status effect;
	private String name;
	
	public Weapon(String n, Status e)
	{
		this.effect =e;
		this.name = n;
	}
	
	public Status getEffect()
	{
		return effect;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString() 
	{
		return "Weapon : " + name + " - " + effect.toString();
	}
	
}
