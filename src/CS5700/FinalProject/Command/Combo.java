package CS5700.FinalProject.Command;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Command
{
	private List<Command> cmds = new ArrayList<Command>();
	
	public void add(Command c)
	{
	    this.cmds.add(c); 
	}
	 
	@Override
	public void execute() 
	{
		for(Command c : cmds)
        {
            c.execute();
        }
	}

	@Override
	public void unexecute() 
	{
		for(Command c : cmds)
        {
            c.unexecute();
        }
	}
	
	public void clear()
	{
        this.cmds.clear();
    }
	
	@Override
	public String toString()
	{
		return cmds.get(0).toString() +", and " + cmds.get(1).toString();
	}
}
