import java.util.ArrayList;
import java.util.Random;

public class Model
{
	ArrayList<Population> byt1;
	Random generator;
	boolean milambda;
	
	//aktualne dla mi,lambda
	Model(int mi, int lambda)
	{
		generator = new Random();
		byt1 = new ArrayList<Population>();
		milambda = true;
		byt1.add(new Population(mi,lambda,generator));
	}
	
	//aktualne dla 1+1
	Model(int between, double multiplier1, double multiplier2)
	{
		generator = new Random();
		byt1 = new ArrayList<Population>();
		milambda = false;
		byt1.add(new Population(between,multiplier1,multiplier2,generator));
	}
	
	void start()
	{
		addGen();
		System.out.println("oki - przetestuj :P");
	}
	
	//dodaje generacjÃª
	public void addGen()
	{
		byt1.add(new Population(generator, byt1.get(byt1.size()-1)));
	}
	
//zwraca listê najlepszych z generacji
	public ArrayList<Evolving<ProjectEvolvingArgs>> getBests()
	{
		ArrayList<Evolving<ProjectEvolvingArgs>> bests = new ArrayList<Evolving<ProjectEvolvingArgs>>();
		for (Population a : byt1)
		{
			bests.add(a.getBest());
		}
		return bests;
	}
	
//zwraca 1 generacjê - jeœli nie ma to null
	public ArrayList<Evolving<ProjectEvolvingArgs>> getPop(int nr)
	{
		if (byt1.size()<=nr)
			return null;		//jak nie ma takiej generacji to zwraca puste
		return byt1.get(nr).getPop();
		
	}
	
	//zwraca najlepszego w generacji
	public Evolving<ProjectEvolvingArgs> getBest(int nr)
	{
		if (byt1.size()<=nr)
			return null;
		return byt1.get(nr).getBest();
	}
	
	//zmienia mi i lambde
	public boolean update(int mi, int lambda)
	{
		if(!milambda)
			return false;
		byt1.clear();
		byt1.add(new Population(mi,lambda,generator));
		return true;
	}
	
	//zmienia m, c1 i c2
	public boolean update(int between, double multiplier1, double multiplier2)
	{
		if(milambda)
			return false;
		byt1.clear();
		byt1.add(new Population(between,multiplier1,multiplier2,generator));
		return true;
		
	}

	
	
	/*public static void main(String[] args)
	{
		Model model = new Model(10,1.2,0.8);
		model.start();
		
		return;
	}*/
}