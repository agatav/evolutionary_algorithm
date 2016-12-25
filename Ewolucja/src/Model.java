import java.util.ArrayList;

public class Model
{
	ArrayList<Population> byt1;
	
	Model()
	{
		byt1 = new ArrayList<Population>();
		byt1.add(new Population());
	}
	
	private void start()
	{
		System.out.println(byt1.get(0).getBest().getFitness());
		
		addGen(); addGen(); addGen();
		
		System.out.println("4 generacje");
		for (Evolving<Double> e : getBests())
		{
			System.out.println(e.getFitness());
		}
		
		System.out.println("cala 1 generacja");
		for (Evolving<Double> e : getPop(1))
		{
			System.out.println(e.getFitness());
		}
	}
	
	public void addGen()
	{//TODO using createNewGen()
		byt1.add(new Population());
	}
	
	public ArrayList<Evolving<Double>> getBests()
	{
		ArrayList<Evolving<Double>> bests = new ArrayList<Evolving<Double>>();
		for (Population a : byt1)
		{
			bests.add(a.getBest());
		}
		return bests;
	}
	
	public ArrayList<Evolving<Double>> getPop(int nr)
	{
		if (byt1.size()<=nr)
			return new ArrayList<Evolving<Double>>();		//jak nie ma takiej generacji to zwraca puste
		return byt1.get(nr).getPop();
		
	}
	
	
	
	public static void main(String[] args)
	{
		Model model = new Model();
		model.start();
		
		return;
	}
}
