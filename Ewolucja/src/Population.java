import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;


public class Population
{
	ArrayList<Individual> jednostki;
	double ratio, multiplier1, multiplier2;
	Random generator;
	int mi,lambda;
	
	
	
	Population(Random generator)
	{
		ratio = 0.2;
		multiplier1 = 0.8;
		multiplier2 = 1.2;
		mi = 20;
		lambda = 50;
		this.generator = generator;
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
	
	
	Population(double rt, double m1, double m2,int mi, int lambda, Random generator)
	{
		this.generator = generator;
		ratio = rt;
		multiplier1 = m1;
		multiplier2 = m2;
		this.mi = mi;
		this.lambda = lambda;
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
	
	Population(Random generator, Population prev)
	{
		this.generator = generator;
		createNewGen(prev);
	}
	
	public Individual getBest()
	{
		return jednostki.get(0);
	}
	
	public ArrayList<Evolving<Double>> getPop()
	{
		ArrayList<Evolving<Double>> pop = new ArrayList<Evolving<Double>>();
		for (Individual i : jednostki)
		{
			pop.add(i);
		}
		return pop;
	}
	
	public double getRatio()
	{
		return ratio;
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
	
	public void createNewGen(Population prev)
	{
		ratio = prev.getRatio();
		multiplier1 = prev.getMulti1();
		multiplier2 = prev.getMulti2();
		mi = prev.getMi();
		lambda = prev.getLambda();
		
		
		// w³aœciwy algorytm
		
	
		ArrayList<Individual> rodzice = new ArrayList<Individual>();
		jednostki = new ArrayList<Individual>();
				
		for(int i=0;i<lambda;i++)
		{
			rodzice.add(prev.getJednostki().get(generator.nextInt(mi)));
		}
		
		for (int i=0;i<lambda;i++)
		{
			jednostki.add(
							rodzice.get(generator.nextInt(lambda))
								.makeNew(rodzice.get(generator.nextInt(lambda)), generator));
		}
		
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
}
