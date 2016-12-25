import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;

public class Individual implements Evolving<Double>
{
	Double x,y,z;
	Double fitness;
	int generacja;
	Double sigma[];
	
	
	//testowe i pierwsze
	public Individual(Random generator)
	{
		x = generator.nextDouble() * 40 - 20;
		y = generator.nextDouble() * 40 - 20;
		z = generator.nextDouble() * 40 - 20;
		sigma = new Double[3];
		sigma[0] = generator.nextDouble() *3;
		sigma[1] = generator.nextDouble() *3;
		sigma[2] = generator.nextDouble() *3;
		generacja = 0;
		fitness = fitnessCalc();
	}
	
	public Individual(Double[] init, int gen, Double[] s)
	{
		x = init[0];
		y = init[1];
		z = init[2];
		sigma = new Double[3];
		sigma[0] = s[0];
		sigma[1] = s[0];
		sigma[2] = s[0];
		generacja = gen;
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
	public int getGen()
	{
		return generacja;
	}
	
	public Double getSigma(int i)
	{
		if (i < 0 || i > 2)
			return null;
		return sigma[i];
	}
	public Individual makeNew(Individual parent,Random generator)
	{//TODO
		Double newVal[] = new Double[3];
		Double newSigma[] = new Double[3];
		double a = generator.nextDouble();
		
		newVal[0] = a*x+(1-a)*parent.getX();
		newVal[1] = a*y+(1-a)*parent.getY();
		newVal[2] = a*z+(1-a)*parent.getZ();
		
		newSigma[0] = a*sigma[0] + (1-a)*parent.getSigma(0);
		newSigma[1] = a*sigma[1] + (1-a)*parent.getSigma(1);
		newSigma[2] = a*sigma[2] + (1-a)*parent.getSigma(2);
		
		double tau,tauprime;
		tau = 1/Math.sqrt(2*Math.sqrt(3));
		tauprime = 1/Math.sqrt(6);
		
		for (int i=0;i<3;i++)
		{
			newSigma[i] = newSigma[i]*Math.exp(tau*generator.nextGaussian()
												+
												tauprime*generator.nextGaussian());
			newVal[i] = newVal[i] + newSigma[i]*generator.nextGaussian();
			if (newVal[i]>20)
				newVal[i] = 20.0;
			if (newVal[i]<-20)
				newVal[i] = -20.0;
		}
		
		Individual next;
		next = new Individual(newVal, generacja+1,newSigma);
		return next;
	}
}
