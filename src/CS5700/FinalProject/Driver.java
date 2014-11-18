package CS5700.FinalProject;

import CS5700.FinalProject.Command.Command;
import CS5700.FinalProject.Command.RenameCommand;
import CS5700.FinalProject.Command.WeaponCommand;
import CS5700.FinalProject.Weapon.Weapon;
import CS5700.FinalProject.Weapon.WeaponFactory;

import java.util.Scanner;

public class Driver 
{	
	private Character steve = new Character("Steve", new Status(100,100,100));
	public static void main(String[] args)
	{
		
		
		Weapon test = WeaponFactory.buildWeapon("Sword");
		System.out.println(test);
		Driver d = new Driver();
		d.loopDriver();
		//System.out.println("Enter your name:");
		
		
		//Character player = new Character(next, new Status(100,100,100));
	
		//System.out.println(player);
	
		
	}
	
	private void loopDriver()
	{
		Scanner input = new Scanner(System.in);
		
		String next = input.nextLine();
		
		while(next.equals("quit") == false)
		{
			action(next);
			next = input.nextLine();
		}
		
		input.close();
	}
	
	private void action(String s)
	{
		
		if (s.equals("hotdog"))
		{
			Command c = new WeaponCommand(WeaponFactory.buildWeapon("Hotdog"));
			steve.addCommand(c);
		}
		else if (s.equals("sword"))
		{
			Command c = new WeaponCommand(WeaponFactory.buildWeapon("Sword"));
			steve.addCommand(c);
		}
		else if (s.equals("fireball"))
		{
			Command c = new WeaponCommand(WeaponFactory.buildWeapon("Fireball"));
			steve.addCommand(c);
		}
		else if (s.equals("do"))
		{
			steve.doCommand();
			System.out.println(steve);
		}
		else if (s.equals("undo"))
		{
			steve.undoCommand();
			System.out.println(steve);
		}
		else if (s.equals("rename"))
		{
			Command c = new RenameCommand("SissyPants");
			steve.addCommand(c);
		}
		else if (s.equals("queue"))
		{
			System.out.println(steve.getInvoker());
		}
		else if (s.equals("help"))
		{
			System.out.println("redo - redo the command(only work after undo)");
			System.out.println("undo - undo the command");
			System.out.println("fireball - use fireball");
			System.out.println("hotdog - use hotdog");
			System.out.println("sword - use sword");
			System.out.println("queue - see the list of command");
		}
	}
}