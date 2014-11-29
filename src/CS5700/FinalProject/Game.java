package CS5700.FinalProject;

import java.util.Random;
import CS5700.FinalProject.Command.Command;
import CS5700.FinalProject.Command.WeaponCommand;
import CS5700.FinalProject.Weapon.WeaponFactory;

public class Game 
{
	private Invoker invoker = new Invoker();
	private Character[] player = new Character[2];
	private Character[] enemy = new Character[3];
	boolean[] playerDead = new boolean[2];
	boolean[] enemyDead = new boolean[3];
	int[] enemyTag = new int[3];
	boolean gameOver = false;
	boolean levelUp = false;
	
	int enemyNumber;
	private String[] enemyName ={"Chloe","Alice","David",
									"Owen","Noah","John"};
	
	private int level;
	
	public Game()
	{
		this.level = 1;
		for(int i=0; i<2; i++)
		{
			player[i] = new Character("Player"+(i+1),new Status(100,100,100));
			playerDead[i] = false;
		}
		
		for(int i=0; i<3; i++)
		{
			enemy[i] = new Character("Enemie"+(i+1),new Status(100,100,100));
			enemyDead[i] = true;
		}
		RandomEnemie();
	}
	
	public void levelUP()
	{
		Status p = new Status(100 + this.level*5,100 + this.level*3,100 + this.level*5);
		levelUp = false;
		
		for(int i=0; i<2; i++)
		{
			if(!playerDead[i])
				player[i].setStatus(p);
		}
		
		for(int i=0; i<3; i++)
			enemyDead[i] = true;
		
		RandomEnemie();
		invoker.clearCommand();
	}
	
	public void RandomEnemie()
	{
		Random rand = new Random();
		enemyNumber = rand.nextInt(3) + 1;
		int nameRand, healthR, magikaR, staminaR;
		boolean checkName = true;
		System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
		for(int i=0; i<enemyNumber; i++)
		{
			nameRand = rand.nextInt(6);
			
			if(i>0)
			{
				while(checkName)
				{
					checkName = false;
					
					for(int j=0; j<i; j++)
					{
						System.out.println(i+" "+j+" " + enemy[j].getName()+" "+enemyName[nameRand]);
						if (enemy[j].getName().equals(enemyName[nameRand]))
						{System.out.println(i+"@ "+j+" " + enemy[j].getName()+" "+enemyName[nameRand]);
							checkName = true;
						}
					}
					
					if(checkName)
					{
						nameRand = rand.nextInt(6);
					}
				}
				checkName = true;
			}
			
			enemyTag[i] = nameRand;
			enemy[i].setName(enemyName[nameRand]);
			System.out.println(i+":::: "+enemy[i].getName()+" "+enemyName[nameRand]);
			healthR = rand.nextInt(80) + 70;
			magikaR = rand.nextInt(80) + 70;
			staminaR = rand.nextInt(80) + 70;
			enemy[i].setStatus(new Status(healthR, magikaR, staminaR));
			enemyDead[i] = false;
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
	
	
	public boolean[] getPlayerDead() {
		return playerDead;
	}

	public boolean[] getEnemyDead() {
		return enemyDead;
	}
	
	public int getEnemyTag(int i) {
		return enemyTag[i];
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isLevelUp() {
		return levelUp;
	}

	public void enemyAttack()
	{
		Random rand = new Random();
		int pNumber = rand.nextInt(2);
		while(playerDead[pNumber] ==true)
			pNumber = rand.nextInt(2);
		int eNumber = rand.nextInt(enemyNumber);
		
		Command command1 = new WeaponCommand(WeaponFactory.buildWeapon("Sword"),enemy[eNumber],player[pNumber]);
		invoker.addEnemyCommand(command1);
		command1.execute();
	}
	
	public void enemyAttackUndo()
	{
		Command c = invoker.getEnemyDoCommand();
		if (c != null) {
			c.unexecute();
		}
	}
	
	public void checkStatus()
	{
		for(int i=0; i<2; i++)
		{
			if(player[i].getStatus().getHealth() <= 0)
				playerDead[i] = true;
		}
		
		for(int i=0; i< enemyNumber; i++)
		{
			if(enemy[i].getStatus().getHealth() <= 0)
				enemyDead[i] = true;
		}
		
		if(playerDead[0] && playerDead[1])
			gameOver = true;
		
		boolean checkLevelUp = true;
		for(int i=0; i< enemyNumber; i++)
		{
			if(!enemyDead[i])
				checkLevelUp = false;
		}
		
		if(checkLevelUp)
			levelUp = true;
	}
}
