package CS5700.FinalProject.Command;

import CS5700.FinalProject.Weapon.Weapon;
import CS5700.FinalProject.Character;

public class WeaponCommand implements Command
{
	private Weapon weapon;
	
	public WeaponCommand(Weapon weapon)
	{
		this.weapon = weapon;
	}
	
	public void execute(Character receiver)
	{
		receiver.receiveDamage(weapon);
	}

	@Override
	public void unexecute(Character receiver) {
		receiver.recoverDamage(weapon);
	}
	
	@Override
	public String toString()
	{
		return weapon.getName() + " command";
	}
}
