package CS5700.FinalProject;

import java.util.Random;

import CS5700.FinalProject.Command.Command;

public class Game 
{
	private Invoker invoker = new Invoker();
	private Character[] player = new Character[2];
	private Character[] enemy = new Character[3];
	int enemyNumber;
	private String[] enemieName ={"John","David","Ashlie",
									"Owen","Noah","Chloe"};
	
	private int level;
	
	public Game()
	{
		this.level = 1;
		for(int i=0; i<2; i++)
			player[i] = new Character("Player"+(i+1),new Status(100,100,100));
		
		for(int i=0; i<3; i++)
			enemy[i] = new Character("Enemie"+(i+1),new Status(100,100,100));
		
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
		enemyNumber = rand.nextInt(3) + 1;
		int nameRand, healthR, magikaR, staminaR;
		
		for(int i=0; i<enemyNumber; i++)
		{
			nameRand = rand.nextInt(6);
			for(int j=0; i<j; i++)
			{
				if (enemy[j].getName().equals(nameRand))
					nameRand = rand.nextInt(6);
			}
			enemy[i].setName(enemieName[nameRand]);
			
			healthR = rand.nextInt(80) + 70;
			magikaR = rand.nextInt(80) + 70;
			staminaR = rand.nextInt(80) + 70;
			enemy[i].setStatus(new Status(healthR, magikaR, staminaR));
		}
	}
	
	public Character getCharacter(String name)
	{
		for(int i=0; i<2; i++)
		{
			if(player[i].getName().equals(name))
				return player[i];
		}
		
		for(int i=0; i<enemyNumber; i++)
		{
			if(enemy[i].getName().equals(name))
				return enemy[i];
		}
		
		return null;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public Character getEnemy(int i)
	{
		return enemy[i];
	}
	
	public Character getPlayer(int i)
	{
		return player[i];
	}

	public int getEnemyNumber() {
		return enemyNumber;
	}

	public void setEnemyNumber(int enemyNumber) {
		this.enemyNumber = enemyNumber;
	}
	
	public void addCommand(Command c)
	{
		invoker.addCommand(c);
	}
	
	public void doCommand()
	{
		Command c = invoker.getDoCommand();
		if (c != null) {
			c.execute();
		}
	}
	
	public void undoCommand()
	{
		Command c = invoker.getUndoCommand();
		if (c != null) {
			c.unexecute();
		}
	}
	
	public Invoker getInvoker()
	{
		return invoker;
	}
}
