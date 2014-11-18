package CS5700.FinalProject;

import CS5700.FinalProject.Command.Command;
import CS5700.FinalProject.Weapon.Weapon;

public class Character 
{
	private Status status;
	private String name;
	private Invoker invoker = new Invoker();
	
	public Character(String name, Status status)
	{
		this.name = name;
		this.status = status;
	}
	
	public void receiveDamage(Weapon w)
	{
		status.update(w.getEffect());
	}
	
	public void recoverDamage(Weapon w)
	{
		status.update(status.getReverse());
	}
	
	public void addCommand(Command c)
	{
		invoker.addCommand(c);
	}
	
	public void doCommand()
	{
		Command c = invoker.getDoCommand();
		if (c != null) {
			System.out.println("Doing: " + c);
			c.execute(this);
		}
	}
	
	public void undoCommand()
	{
		Command c = invoker.getUndoCommand();
		if (c != null) {
			System.out.println("Undoing: " + c);
			c.unexecute(this);
		}
	}
	
	@Override
	public String toString()
	{
		return "Name: " + name + "; " + status.toString();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String s)
	{
		this.name = s;
	}
	
	public Invoker getInvoker()
	{
		return invoker;
	}
}
