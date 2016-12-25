import java.lang.Math;
import java.util.ArrayList;

public class Individual implements Evolving<Double>
{
	Double x,y,z;
	Double fitness;
	
	
	
	Individual(double initx,double inity,double initz)
	{
		x = initx * 40 - 20;
		y = initx * 40 - 20;
		z = initx * 40 - 20;
		fitness = fitnessCalc();
		
	}
	
	private double fitnessCalc()
	{
		return 0.01*x*x + 0.02*y*y + 0.03*z*z - Math.cos(x)*Math.cos(y)*Math.cos(z);
	}
	
	public	double getX()
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
	public double getFitness()
	{
		return fitness;
	}
	public ArrayList<Double> getArgs()
	{
		ArrayList<Double> a = new ArrayList<Double>();
		a.add(x); a.add(y); a.add(z);
		return a;
	}
}
