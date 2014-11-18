package CS5700.FinalProject.Command;

import CS5700.FinalProject.Character;

public interface Command 
{
	public void execute(Character c);
	public void unexecute(Character c);
}
