import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;


public class Population
{
	ArrayList<Individual> jednostki;
	double multiplier1, multiplier2;
	Random generator;
	int mi, lambda, between;
	boolean milambda;
	int fail,success;
	
	
	
	//aktualne dla mi,lambda
	Population(int mi, int lambda, Random generator)
	{
		this.generator = generator;
		this.mi = mi;
		this.lambda = lambda;
		milambda = true;
		jednostki = new ArrayList<Individual>();
		for (int i=0;i<mi;i++)
		{
			jednostki.add(new Individual(generator));
		}
		
		Collections.sort(jednostki, new Comparator<Individual>()
				{
					@Override
					public int compare(Individual i1, Individual i2)
					{
						return Double.compare(i1.getFitness(), i2.getFitness());
					}
				});
	}
	//aktualne dla 1+1
	Population(int between, double m1, double m2, Random generator)
	{
		this.generator = generator;
		this.between = between;
		multiplier1 = m1;
		multiplier2 = m2;
		fail = 0;
		success = 0;
		jednostki = new ArrayList<Individual>();
		
		jednostki.add(new Individual(generator));
	}
	
	Population(Random generator, Population prev)
	{
		this.generator = generator;
		if (prev.getMiLambda())
			createNewGenMiLambda(prev);
		else
			createNewGen1Plus1(prev);
	}
	
	public Individual getBest()
	{
		return jednostki.get(0);
	}
	
	public ArrayList<Evolving<ProjectEvolvingArgs>> getPop()
	{
		ArrayList<Evolving<ProjectEvolvingArgs>> pop = new ArrayList<Evolving<ProjectEvolvingArgs>>();
		for (Individual i : jednostki)
		{
			pop.add(i);
		}
		return pop;
	}
	
	public double getMulti1()
	{
		return multiplier1;
	}
	
	public double getMulti2()
	{
		return multiplier2;
	}
	
	public ArrayList<Individual> getJednostki()
	{
		return jednostki;
	}
	
	public int getMi()
	{
		return mi;
	}
	
	public int getLambda()
	{
		return lambda;
	}
	
	public int getBetween()
	{
		return between;
	}
	
	public boolean getMiLambda()
	{
		return milambda;
	}
	
	public void createNewGenMiLambda(Population prev)
	{
		mi = prev.getMi();
		lambda = prev.getLambda();
		
		milambda = true;
		
		// w³aœciwy algorytm
		
	
		ArrayList<Individual> rodzice = new ArrayList<Individual>();
		jednostki = new ArrayList<Individual>();
				
		for(int i=0;i<lambda;i++)
		{
			rodzice.add(prev.getJednostki().get(generator.nextInt(mi)));
		}
		//TODO tu ma byæ czesc algorytmu z indywidual
		ProjectEvolvingArgs val1,val2,newArgs;
		double x,y,z,sigmaX,sigmaY,sigmaZ,a;
		double tau,tauprime;
		int currgen;
		for (int i=0;i<lambda;i++)
		{
			val1 = rodzice.get(i).getArgs();
			val2 = rodzice.get((i+1)%lambda).getArgs();
			currgen = rodzice.get(i).getGen();
			a = generator.nextDouble();
			
			x = a*val1.getX() + (1-a)*val2.getX();
			y = a*val1.getY() + (1-a)*val2.getY();
			z = a*val1.getZ() + (1-a)*val2.getZ();
			
			sigmaX = a*val1.getSigmaX() + (1-a)*val2.getSigmaX();
			sigmaY = a*val1.getSigmaY() + (1-a)*val2.getSigmaY();
			sigmaZ = a*val1.getSigmaZ() + (1-a)*val2.getSigmaZ();
			
			tau = 1/Math.sqrt(2*Math.sqrt(3));
			tauprime = 1/Math.sqrt(6);
			sigmaX = sigmaX*Math.exp(tau*generator.nextGaussian() + tauprime*generator.nextGaussian());
			sigmaY = sigmaY*Math.exp(tau*generator.nextGaussian() + tauprime*generator.nextGaussian());
			sigmaZ = sigmaZ*Math.exp(tau*generator.nextGaussian() + tauprime*generator.nextGaussian());
		
			x = x + sigmaX*generator.nextGaussian();
			y = y + sigmaY*generator.nextGaussian();
			z = z + sigmaZ*generator.nextGaussian();
			
			if (x>20) x = 20.0;
			if (x<-20) x = -20.0;
			if (y>20) y = 20.0;
			if (y<-20) y = -20.0;
			if (z>20) z = 20.0;
			if (z<-20) z = -20.0;
			
			newArgs = new ProjectEvolvingArgs(x,y,z,sigmaX,sigmaY,sigmaZ);
			jednostki.add(new Individual(newArgs,currgen+1));
		}
			
		//endTODO
		Collections.sort(jednostki, new Comparator<Individual>()
		{
			@Override
			public int compare(Individual i1, Individual i2)
			{
				return Double.compare(i1.getFitness(), i2.getFitness());
			}
		});
		
		while (jednostki.size()>mi)
		{
			jednostki.remove(mi);
		}
	}
	
	public void createNewGen1Plus1(Population prev)
	{
		multiplier1 = prev.getMulti1();
		multiplier2 = prev.getMulti2();
		between = prev.getBetween();
		double x,y,z,sigmaX,sigmaY,sigmaZ;
		jednostki = new ArrayList<Individual>();
		milambda = false;
		
		// w³aœciwy algorytm
		
		ProjectEvolvingArgs prevArgs = prev.getBest().getArgs();
		ProjectEvolvingArgs nextArgs;
		Individual next;
		
		
		x = prevArgs.getX() * prevArgs.getSigmaX()*generator.nextGaussian();
		y = prevArgs.getY() * prevArgs.getSigmaY()*generator.nextGaussian();
		z = prevArgs.getZ() * prevArgs.getSigmaZ()*generator.nextGaussian();
		sigmaX = prevArgs.getSigmaX();
		sigmaY = prevArgs.getSigmaY();
		sigmaZ = prevArgs.getSigmaZ();
		
		if((prev.getBest().getGen()+1)%between == 0)
		{
			double ratio = (double)success / (success + fail);
			if(ratio > 0.2)
			{
				sigmaX = sigmaX*multiplier2;
				sigmaY = sigmaY*multiplier2;
				sigmaZ = sigmaZ*multiplier2;
			}
			if(ratio < 0.2)
			{
				sigmaX = sigmaX*multiplier1;
				sigmaY = sigmaY*multiplier1;
				sigmaZ = sigmaZ*multiplier1;
			}
			success = 0;
			fail = 0;
		}
		
		nextArgs = new ProjectEvolvingArgs(x,y,z,sigmaX,sigmaY,sigmaZ);
		next = new Individual(nextArgs,prev.getBest().getGen()+1);
		
		if (prev.getBest().getFitness() < next.getFitness())
		{
			fail++;
			nextArgs = new ProjectEvolvingArgs(prevArgs.getX(),prevArgs.getY(),prevArgs.getZ(),sigmaX,sigmaY,sigmaZ);
			next = new Individual(nextArgs,prev.getBest().getGen()+1);
		}
		else
		{
			success++;
		}
		
		jednostki.add(next);
	}
}