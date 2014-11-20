package CS5700.FinalProject;

import CS5700.FinalProject.Weapon.Weapon;

public class Character 
{
	private Status statusData;
	private String name;
	
	public Character(String name, Status status)
	{
		this.name = name;
		this.statusData = status;
	}
	
	public void receiveDamage(Weapon w)
	{
		statusData.update(w.getEffect());
	}
	
	public void recoverDamage(Weapon w)
	{
		statusData.update(w.getEffect().getReverse());
	}
	
	
	
	@Override
	public String toString()
	{
		return "Name: " + name + "; " + statusData.toString();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String s)
	{
		this.name = s;
	}

	public Status getStatus() {
		return statusData;
	}

	public void setStatus(Status status) {
		this.statusData = status;
	}
	
	
}
