package CS5700.FinalProject.Weapon;
import CS5700.FinalProject.Status;

public abstract class Weapon 
{

	public abstract Status getStatus();
	public abstract String getName();
	public abstract Weapon getReverse();

	@Override
	public String toString() 
	{
		return "Weapon [getStatus()=" + getStatus() + ", getName()="
				+ getName() + "]";
	}
	
}
