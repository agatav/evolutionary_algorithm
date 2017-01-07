import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Model
{
	ArrayList<Population> byt1;
	Random generator;
	LineChart chart;
	
	Model()
	{
		generator = new Random();
		byt1 = new ArrayList<Population>();
		byt1.add(new Population(generator));
		
		chart = new LineChart("Test", "Test"); //nowe okno z wykresem
	}
	//uzywaj tego - drugi bedzie staly - w tym mozesz ustawic jaki maja byc te wspólczynniki z algorytmu <--super komentarz
	Model(double ratio,double multiplier1, double multiplier2, int mi, int lambda)
	{
		generator = new Random();
		byt1 = new ArrayList<Population>();
		byt1.add(new Population(ratio,multiplier1,multiplier2,mi,lambda,generator));
		
		chart = new LineChart("Test", "Test"); //nowe okno z wykresem
	}
	
	private void start()
	{
		try {
			PrintWriter zapis = new PrintWriter("wyniki.txt"); 
			System.out.println(byt1.get(0).getBest().getFitness()); //?
			zapis.println(byt1.get(0).getBest().getFitness());
			for (int i=0;i<100;i++)
			{
				addGen();
			}
			
			
			System.out.println("100 generacji");
			zapis.println("100 generacji");
			int i=0;
			for (Evolving<Double> e : getBests())
			{	
				System.out.print(i++);
				zapis.print(i);
				System.out.println(" " + e.getFitness());
				zapis.println(" " + e.getFitness());
				
				chart.createDataset(i, e.getFitness());//
			}
		    
			System.out.println("cala ostatnia best generacja");
			zapis.println("cala ostatnia best generacja");
			
			System.out.print(getBests().get(getBests().size()-1).getArgs().get(0));//x
			zapis.print(getBests().get(getBests().size()-1).getArgs().get(0));
			chart.setText("x", getBests().get(getBests().size()-1).getArgs().get(0), chart.labelx);
			
			System.out.print("  ");
			zapis.print("  ");
			
			System.out.print(getBests().get(getBests().size()-1).getArgs().get(1)); //y
			zapis.print(getBests().get(getBests().size()-1).getArgs().get(1));
			chart.setText("y", getBests().get(getBests().size()-1).getArgs().get(1), chart.labely);
			
			System.out.print("  ");
			zapis.print("  ");
			
			System.out.print(getBests().get(getBests().size()-1).getArgs().get(2));//z
			zapis.print(getBests().get(getBests().size()-1).getArgs().get(2));
			chart.setText("z", getBests().get(getBests().size()-1).getArgs().get(2), chart.labelz);
			System.out.print("  ");
			zapis.print("  ");
			
			System.out.print(getBests().get(getBests().size()-1).getFitness());
			zapis.print(getBests().get(getBests().size()-1).getFitness());
			chart.setText("Funkcja", getBests().get(getBests().size()-1).getFitness(), chart.labelf);
	
			zapis.close();
		} catch (IOException e) {}
	}
	
	//dodaje generacje
	public void addGen()
	{//TODO using createNewGen()
		byt1.add(new Population(generator, byt1.get(byt1.size()-1)));
	}
	
	//zwraca liste najlepszych z generacji
	public ArrayList<Evolving<Double>> getBests()
	{
		ArrayList<Evolving<Double>> bests = new ArrayList<Evolving<Double>>();
		for (Population a : byt1)
		{
			bests.add(a.getBest());
		}
		return bests;
	}
	
	//zwraca 1 generacje - jezeli nie ma to null
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