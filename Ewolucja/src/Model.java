import java.util.ArrayList;
import java.util.Random;

public class Model
{
	ArrayList<Population> byt1;
	Random generator;
	
	Model()
	{
		generator = new Random();
		byt1 = new ArrayList<Population>();
		byt1.add(new Population(generator));
	}
	//u¿ywaj tego - drugi bêdzie sta³y - w tym mo¿esz ustawiæ jaki maj¹ byæ te wspó³czynniki z algorytmu
	Model(double ratio,double multiplier1, double multiplier2, int mi, int lambda)
	{
		generator = new Random();
		byt1 = new ArrayList<Population>();
		byt1.add(new Population(ratio,multiplier1,multiplier2,mi,lambda,generator));
	}
	
	private void start()
	{
		System.out.println(byt1.get(0).getBest().getFitness());
		for (int i=0;i<100;i++)
		{
			addGen();
		}
		
		
		System.out.println("4 generacje");
		int i=0;
		for (Evolving<Double> e : getBests())
		{	
			System.out.print(i++);
			System.out.println(e.getFitness());
		}
		
		System.out.println("cala 1 generacja");
		System.out.print(getBests().get(getBests().size()-1).getArgs().get(0));
		System.out.print("  ");
		System.out.print(getBests().get(getBests().size()-1).getArgs().get(1));
		System.out.print("  ");
		System.out.print(getBests().get(getBests().size()-1).getArgs().get(2));
		System.out.print("  ");
		System.out.print(getBests().get(getBests().size()-1).getFitness());

	}
	
	//dodaje generacjê
	public void addGen()
	{//TODO using createNewGen()
		byt1.add(new Population(generator, byt1.get(byt1.size()-1)));
	}
	
	//zwraca listê najlepszych z generacji
	public ArrayList<Evolving<Double>> getBests()
	{
		ArrayList<Evolving<Double>> bests = new ArrayList<Evolving<Double>>();
		for (Population a : byt1)
		{
			bests.add(a.getBest());
		}
		return bests;
	}
	
	//zwraca 1 generacjê - jeœli nie ma to null
	public ArrayList<Evolving<Double>> getPop(int nr)
	{
		if (byt1.size()<=nr)
			return null;		//jak nie ma takiej generacji to zwraca puste
		return byt1.get(nr).getPop();
		
	}
	
	//zwraca najlepszego w generacji
	public Evolving<Double> getBest(int nr)
	{
		if (byt1.size()<=nr)
			return null;
		return byt1.get(nr).getBest();
	}

	
	
	public static void main(String[] args)
	{
		Model model = new Model();
		model.start();
		
		return;
	}
}
