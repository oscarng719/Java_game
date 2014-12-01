package CS5700.FinalProject.Weapon;

public class WeaponFactory 
{
	public static Weapon buildWeapon(String s)
	{
		Weapon w = null;
		switch (s) {
        case "Fireball":
            w = new Fireball();
            break;
 
        case "Sword":
            w = new Sword();
            break;
 
        case "Arrow":
           w = new Arrow();
            break;
        }
		
        return w;
	}
}
