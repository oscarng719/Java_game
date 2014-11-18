package CS5700.FinalProject.Command;

import CS5700.FinalProject.Character;

public class RenameCommand implements Command
{
	private String oldName;
	private String rename;
	
	public RenameCommand(String rename)
	{
		this.rename = rename;
	}
	
	@Override
	public String toString()
	{
		return "Rename command";
	}


	@Override
	public void execute(Character c) 
	{
		oldName = c.getName();
		c.setName(rename);
	}


	@Override
	public void unexecute(Character c) 
	{
		c.setName(oldName);
	}
}
