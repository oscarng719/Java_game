package CS5700.FinalProject.Command;

import CS5700.FinalProject.Weapon.Weapon;
import CS5700.FinalProject.Character;

public class WeaponCommand implements Command
{
	private Character from;
	private Weapon weapon;
	private Character target;
	
	public WeaponCommand(Weapon weapon, Character from, Character target)
	{
		this.weapon = weapon;
		this.from = from;
		this.target = target;
	}
	
	public void execute()
	{
		target.receiveDamage(weapon);
	}

	@Override
	public void unexecute() {
		target.recoverDamage(weapon);
	}
	
	@Override
	public String toString()
	{
		return from.getName() +" attack "+target.getName()+" with " + weapon.getName();
	}
}
