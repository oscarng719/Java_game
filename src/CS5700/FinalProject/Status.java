package CS5700.FinalProject;

public class Status 
{
	int health;
	int magika;
	int stamina;
	
	public Status(int health, int magika, int stamina)
	{
		if(health < 1)
			this.health = 1;
		else
			this.health = health;
		
		if(magika < 1)
			this.magika = 1;
		else
			this.magika = magika;
		
		if(stamina < 1)
			this.stamina = 1;
		else
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
	
}
