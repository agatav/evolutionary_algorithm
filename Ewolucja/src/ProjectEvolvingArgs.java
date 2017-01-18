import java.util.ArrayList;

public class ProjectEvolvingArgs implements Cloneable
{
	ArrayList<Double> args;
	
	
	public ProjectEvolvingArgs(double x, double y, double z)
	{
		args = new ArrayList<Double>();
		args.add(x);
		args.add(y);
		args.add(z);
	}
	public ProjectEvolvingArgs(ArrayList<Double> a)
	{
		args = new ArrayList<Double>();
		for(Double e: a)
		{
			Double z = new Double(e);
			args.add(z);
		}
	}

	public ArrayList<Double> getArgs()
	{
		ArrayList<Double> zwrot = new ArrayList<Double>();
		for (Double e: args)
		{
			Double a = new Double(e);
			zwrot.add(a);
		}
		return zwrot;
	}
}