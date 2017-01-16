import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;


public class Individual implements Evolving<ProjectEvolvingArgs>
{
	ProjectEvolvingArgs args;
	Double fitness;
	int generacja;
	Object sigma;
	static int wymiary = 3;
	
	
	//testowe i pierwsze
	public Individual(Random generator,Object sigma)
	{
		this.sigma = sigma;
		double x = generator.nextDouble() * 40 - 20;
		double y = generator.nextDouble() * 40 - 20;
		double z = generator.nextDouble() * 40 - 20;
		/*
		double sigma[] = new double[3];
		sigma[0] = generator.nextDouble() *3;
		sigma[1] = generator.nextDouble() *3;
		sigma[2] = generator.nextDouble() *3;
		*/
		generacja = 0;
		args = new ProjectEvolvingArgs(x,y,z);
		fitness = fitnessCalc();
	}
	
	public Individual(ProjectEvolvingArgs args, int gen,Object sigma)
	{
		this.args = args;
		this.sigma = sigma;
		generacja = gen;
		fitness = fitnessCalc();
	}
	public Individual(ArrayList<Double> args, int gen,Object sigma)
	{
		this.args = new ProjectEvolvingArgs(args);
		this.sigma = sigma;
		generacja = gen;
		fitness = fitnessCalc();
	}
	
	private double fitnessCalc()
	{
		return 0.01*args.getArgs().get(0)*args.getArgs().get(0)
				+ 0.02*args.getArgs().get(1)*args.getArgs().get(1)
				+ 0.03*args.getArgs().get(2)*args.getArgs().get(2)
				- Math.cos(args.getArgs().get(0)) * Math.cos(args.getArgs().get(1)) * Math.cos(args.getArgs().get(2))
				+ kara();
	}
	
	private double kara()
	{
		double kara = 0;
		for (Double e : args.getArgs())
		{
			if (Math.abs(e) > 20)
				kara+= Math.abs(e) - 20;
		}
		return kara;
	}
	/*
	public	double getX()
	{
		return args.getX();
	}
	public double getY()
	{
		return args.getY();
	}
	public double getZ()
	{
		return args.getZ();
	}
	*/
	
	public double getFitness()
	{
		return fitness;
	}
	public ProjectEvolvingArgs getArgs()
	{
		return args;
	}
	public int getGen()
	{
		return generacja;
	}
	
	public Object getSigma()
	{
		return sigma;
	}
	public ArrayList<Double> getArrayArgs()
	{
		return args.getArgs();
	}
	public Evolving<ProjectEvolvingArgs> create(ProjectEvolvingArgs args, Object sigma)
	{
		Individual nowy = new Individual(args,0,sigma);
		return nowy;
	}
}