import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;


public class Population
{
	ArrayList<Individual> jednostki;
	Random generator;
	
	
	
	Population()
	{
		jednostki = new ArrayList<Individual>();
		generator = new Random();
		for (int i=0;i<10;i++)
		{
			jednostki.add(new Individual(generator.nextDouble(),generator.nextDouble(),generator.nextDouble()));
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
	public ArrayList<Individual> createNewGen()
	{//TODO
		return jednostki;
	}
}
