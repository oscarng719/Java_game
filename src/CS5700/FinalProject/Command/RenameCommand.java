package CS5700.FinalProject.Command;

import CS5700.FinalProject.Character;

public class RenameCommand implements Command
{
	private String oldName;
	private String rename;
	private Character target;
	
	public RenameCommand(String rename, Character target)
	{
		this.rename = rename;
		this.target = target;
	}
	
	@Override
	public String toString()
	{
		return target.getName() + " change the name to " +rename;
	}


	@Override
	public void execute() 
	{
		oldName = target.getName();
		target.setName(rename);
	}


	@Override
	public void unexecute() 
	{
		target.setName(oldName);
	}
}
