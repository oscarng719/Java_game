package CS5700.FinalProject;

public class Status 
{
	int health;
	int magika;
	int stamina;
	
	public Status(int health, int magika, int stamina)
	{
		this.health = health;
		
		this.magika = magika;
		
		this.stamina = stamina;
	}
	
	public void update(Status s)
	{
		health += s.getHealth();
		magika += s.getMagika();
		stamina += s.getStamina();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMagika() {
		return magika;
	}

	public void setMagika(int magika) {
		this.magika = magika;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	public Status getReverse()
	{
		return new Status(health * -1, magika * -1, stamina * -1);
	}
	
	public String toString()
	{
		return "(health " + health +", magika "+ magika +", stamina "+stamina +")";
	}
	
}
