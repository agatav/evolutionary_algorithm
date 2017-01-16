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
	/* OLD
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getZ()
	{
		return z;
	}
	public double getSigmaX()
	{
		return sigmaX;
	}
	public double getSigmaY()
	{
		return sigmaY;
	}
	public double getSigmaZ()
	{
		return sigmaZ;
	}
	public int getSuccess() { return success;}
	public int getFail() {return fail;}	
	 */
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