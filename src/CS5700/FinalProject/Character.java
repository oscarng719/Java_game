package CS5700.FinalProject;

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
	
	public void updateStatus(Weapon w)
	{
		status.update(w.getData());
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
