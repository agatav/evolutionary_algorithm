import java.lang.Math;
import java.util.Random;

public class Individual implements Evolving<ProjectEvolvingArgs>
{
	ProjectEvolvingArgs args;
	Double fitness;
	int generacja;
	
	
	//testowe i pierwsze
	public Individual(Random generator)
	{
		double x = generator.nextDouble() * 40 - 20;
		double y = generator.nextDouble() * 40 - 20;
		double z = generator.nextDouble() * 40 - 20;
		double sigma[] = new double[3];
		sigma[0] = generator.nextDouble() *3;
		sigma[1] = generator.nextDouble() *3;
		sigma[2] = generator.nextDouble() *3;
		generacja = 0;
		args = new ProjectEvolvingArgs(x,y,z,sigma[0],sigma[1],sigma[2]);
		fitness = fitnessCalc();
	}
	
	public Individual(ProjectEvolvingArgs args, int gen)
	{
		this.args = args;
		generacja = gen;
		fitness = fitnessCalc();
	}
	
	private double fitnessCalc()
	{
		return 0.01*args.getX()*args.getX()
				+ 0.02*args.getY()*args.getY()
				+ 0.03*args.getZ()*args.getZ()
				- Math.cos(args.getX()) * Math.cos(args.getY()) * Math.cos(args.getZ());
	}
	
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
	
	public double getSigma(int i)
	{
		if (i < 0 || i > 2)
			return 0;
		double sigma[] = new double[3];
		sigma[0] = args.getSigmaX();
		sigma[1] = args.getSigmaY();
		sigma[2] = args.getSigmaZ();
		return sigma[i];
	}
	public Evolving<ProjectEvolvingArgs> create(ProjectEvolvingArgs args)
	{
		Individual nowy = new Individual(args,0);
		return nowy;
	}
}