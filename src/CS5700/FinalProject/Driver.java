package CS5700.FinalProject;

import CS5700.FinalProject.Command.Command;
import CS5700.FinalProject.Command.RenameCommand;
import CS5700.FinalProject.Command.WeaponCommand;
import CS5700.FinalProject.GUI.GameBoard;
import CS5700.FinalProject.Weapon.Weapon;
import CS5700.FinalProject.Weapon.WeaponFactory;

import java.util.Scanner;

import javax.swing.JFrame;

public class Driver 
{	
	private Character steve = new Character("Steve"+1, new Status(100,100,100));
	public static void main(String[] args)
	{
		
		
		/*Weapon test = WeaponFactory.buildWeapon("Sword");
		System.out.println(test);
		Driver d = new Driver();
		d.loopDriver();
		System.out.println("Enter your name:");
		
		
		Character player = new Character(next, new Status(100,100,100));
	
		System.out.println(player);
	
		String s ="John attack AAA with fireball";
		String[] s2 = s.split(" ");
		
		for(int i=0; i< s2.length;i++)
		{
			System.out.println(s2[i]);
		}*/
		
		
		JFrame f = new GameBoard();
		f.setVisible(true);
	}
	
	/*private void loopDriver()
	{
		Scanner input = new Scanner(System.in);
		
		String next = input.nextLine();
		
		while(next.equals("quit") == false)
		{
			action(next);
			next = input.nextLine();
		}
		System.out.println("\n\nDONE");
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
		else
			System.out.println("Incorrect Input, try again!");
	}*/
}