package CS5700.FinalProject;

import java.util.concurrent.ConcurrentLinkedDeque;

import CS5700.FinalProject.Command.Command;

public class Invoker 
{
	private ConcurrentLinkedDeque<Command> commandList = new ConcurrentLinkedDeque<Command>();
	private ConcurrentLinkedDeque<Command> undoList = new ConcurrentLinkedDeque<Command>();
	private ConcurrentLinkedDeque<Command> enemyAttackList = new ConcurrentLinkedDeque<Command>();
	
	public void clearCommand()
	{
		commandList.clear();
		undoList.clear();
		enemyAttackList.clear();
	}
	
	public void addCommand(Command c) 
	{
		commandList.add(c);
	}
	
	public void addEnemyCommand(Command c) 
	{
		enemyAttackList.add(c);
	}

	public Command getDoCommand() 
	{
		Command c = commandList.peek();
		
		if (c != null)
		{
			undoList.push(c);
			commandList.pop();
		}
		
		return c;
	}

	public Command getUndoCommand() 
	{
		Command c = undoList.peek();
		if (c != null)
		{
			undoList.pop();
			commandList.push(c);
		}
		
		return c;
	}
	
	public Command getEnemyDoCommand() 
	{
		Command c = enemyAttackList.peek();
		
		if (c != null)
		{
			enemyAttackList.pop();
		}
		
		return c;
	}

	public Command getEnemyCommand() 
	{
		Command c = undoList.peek();
		if (c != null)
		{
			undoList.pop();
			commandList.push(c);
		}
		
		return c;
	}
	public boolean checkCmdList()
	{
		return commandList.isEmpty();
	}
	
	public boolean checkUnDoList()
	{
		return undoList.isEmpty();
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Commands to do:\n");
		Command[] list = commandList.toArray(new Command[0]);
		for (int i = 0; i < list.length; i++)
		{
			sb.append("\t" + list[i].toString());
			sb.append("\n");
		}
		
		sb.append("Commands to undo:\n");
		Command[] ulist = undoList.toArray(new Command[0]);
		for (int i = 0; i < ulist.length; i++)
		{
			sb.append("\t" + ulist[i].toString());
			sb.append("\n");
		}
		
		sb.append("Enemy command:\n");
		Command[] elist = enemyAttackList.toArray(new Command[0]);
		for (int i = 0; i < elist.length; i++)
		{
			sb.append("\t" + elist[i].toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
