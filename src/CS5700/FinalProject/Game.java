package CS5700.FinalProject;

import java.util.Random;

public class Game 
{
	private Character[] player = new Character[2];
	private Character[] enemie = new Character[3];
	int enemieNumber;
	private String[] enemieName ={"John","David","Ashlie",
									"Owen","Noah","Chloe"};
	
	private int level;
	
	public Game()
	{
		this.level = 1;
		for(int i=0; i<2; i++)
			player[i] = new Character("p"+i,new Status(100,100,100));
		
		for(int i=0; i<3; i++)
			enemie[i] = new Character("e"+i,new Status(100,100,100));
		
		RandomEnemie();
	}
	
	public void levelUP()
	{
		Status p = new Status(this.level*5,this.level*3,this.level*5);
		
		for(int i=0; i<2; i++)
		{
			player[i].getStatus().update(p);;
		}
		
		RandomEnemie();
	}
	
	public void RandomEnemie()
	{
		Random rand = new Random();
		enemieNumber = rand.nextInt(3) + 1;
		int nameRand, healthR, magikaR, staminaR;
		
		for(int i=0; i<enemieNumber; i++)
		{
			nameRand = rand.nextInt(6);
			enemie[i].setName(enemieName[nameRand]);
			
			healthR = rand.nextInt(160) + 100;
			magikaR = rand.nextInt(130) + 100;
			staminaR = rand.nextInt(140) + 100;
			enemie[i].setStatus(new Status(healthR, magikaR, staminaR));
		}
	}
	
	public Character getCharacter(String name)
	{
		for(int i=0; i<2; i++)
		{
			if(player[i].getName().equals(name))
				return player[i];
		}
		
		for(int i=0; i<enemieNumber; i++)
		{
			if(enemie[i].getName().equals(name))
				return enemie[i];
		}
		
		return null;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
