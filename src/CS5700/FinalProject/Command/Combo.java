package CS5700.FinalProject.Command;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Command
{
	Command c1;
	Command c2;
	
	public Combo(Command c1, Command c2)
	{
		this.c1 = c1;
		this.c2 = c2;
	}
	
	@Override
	public void execute() 
	{
		c1.execute();
		c2.execute();
	}

	@Override
	public void unexecute() 
	{
		c1.unexecute();
		c2.unexecute();
	}
	
	@Override
	public String toString()
	{
		return c1.toString() +", and " + c2.toString();
	}
}
