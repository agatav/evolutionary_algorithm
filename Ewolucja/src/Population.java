import java.util.*;


public class Population implements Runnable
{
	ArrayList<Individual> jednostki;
	double multiplier1, multiplier2;
	Random generator;
	int mi, lambda, between;
	boolean milambda;
	
	
	
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
			ArrayList<Double> sigma = new ArrayList<Double>();
			sigma.add(generator.nextDouble() *3);
			sigma.add(generator.nextDouble() *3);
			sigma.add(generator.nextDouble() *3);			
			jednostki.add(new Individual(generator,sigma));
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
		jednostki = new ArrayList<Individual>();
		init1Plus1();
	}
	
	Population(Random generator, Population prev)
	{
		this.generator = generator;
		if (prev.getMiLambda())
			createNewGenMiLambda(prev);
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

		ArrayList<Individual> rodzice = new ArrayList<Individual>();
		jednostki = new ArrayList<Individual>();
				
		for(int i=0;i<lambda;i++)
		{
			rodzice.add(prev.getJednostki().get(generator.nextInt(mi)));
		}
		
		Individual val1,val2;
		ArrayList<Double> sigma,newArgs;
		double x,y,z,sigmaX,sigmaY,sigmaZ,a;
		double tau,tauprime;
		int currgen;
		
		tau = 1/Math.sqrt(2*Math.sqrt(3));
		tauprime = 1/Math.sqrt(6);
		
		for (int i=0;i<lambda;i++)
		{
			sigma = new ArrayList<Double>();
			newArgs = new ArrayList<Double>();
			val1 = rodzice.get(i);
			val2 = rodzice.get((i+1)%lambda);
			currgen = rodzice.get(i).getGen();
			a = generator.nextDouble();
			
			Iterator<Double> it1 = val1.getArrayArgs().iterator();
			Iterator<Double> it2 = val2.getArrayArgs().iterator();
			Iterator<Double> its1 = ((ArrayList<Double>)val1.getSigma()).iterator();
			Iterator<Double> its2 = ((ArrayList<Double>)val2.getSigma()).iterator();
			Double temp_v;
			Double temp_s;
			
			while(it1.hasNext())
			{
				Double v1,v2,s1,s2;
				v1=it1.next();
				v2=it2.next();
				s1 = its1.next();
				s2 = its2.next();
				
				temp_v = a*v1 + (1-a)*v2;
				temp_s = a*s1 + (1-a)*s2;
				temp_s = temp_s*Math.exp(tau*generator.nextGaussian() + tauprime*generator.nextGaussian());
				temp_v = temp_v + temp_s*generator.nextGaussian();
	
				newArgs.add(temp_v);
				sigma.add(temp_s);
			}
			jednostki.add(new Individual(newArgs,currgen+1,sigma));

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

	public void run()
	{
		long stopTime = System.currentTimeMillis() + 2000;
		Individual newlyEvolved;
		while(System.currentTimeMillis()<stopTime)
		{
			newlyEvolved = OnePlusOneEvolve();
			checkAndAdd(newlyEvolved);
		}
		
	}
	
	private Individual OnePlusOneEvolve()
	{
		Double sigma = generator.nextDouble() * 3 ;		
		Individual evolved = new Individual(generator,sigma);
		Individual contender;
		ArrayList<Double> argumenty;
		int fail =0;
		int success = 0;
		int i =0;
		while (sigma>=0.01)
		{
			argumenty = new ArrayList<Double>();
			sigma = new Double((Double)(evolved.getSigma()));
			
			for (Double e: evolved.getArrayArgs())
			{
				argumenty.add(new Double(e * (1 + sigma * generator.nextGaussian())));
			}
			
			if((i+1) % between == 0)
			{
				double ratio = (double) success / (success + fail);
				if (ratio > 0.2) 
				{
						sigma = sigma * multiplier1;
				}
				if (ratio <= 0.2) 
				{
						sigma = sigma * multiplier2;
				}
				success = 0;
				fail = 0;
			}
			
			contender = new Individual(argumenty,i+1,sigma);
			
			if (evolved.getFitness() > contender.getFitness())
			{
				evolved = contender;
				success++;
			}
			else
			{
				evolved = new Individual(evolved.getArrayArgs(),i+1,sigma);
				fail++;
			}
			i++;
			if (i>10000)
			{
				System.out.println("przerwane, za du�o ");
				break;
			}
		}
		
		return evolved;
	}
	
	private synchronized void checkAndAdd(Individual e)
	{
		if (jednostki.size() == 0)
		{
			jednostki.add(e);
		}
		else
		{
			if(jednostki.get(jednostki.size()-1).getFitness() > e.getFitness())
				jednostki.add(e);
		}
	}
	
	private void init1Plus1()
	{
		ArrayList<Thread> watki = new ArrayList<Thread>();
		Thread watek;
		for (int i=0;i<10;i++)
		{
			watek = new Thread(this);
			watek.start();
			watki.add(watek);
		}
		for (Thread e : watki)
		{
			try {e.join();}
			catch(Exception e1) {}
		}
	}
}