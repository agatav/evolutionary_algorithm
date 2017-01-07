import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Model
{
	ArrayList<Population> byt1;
	Random generator;
	Chart chart;
	
	Model()
	{
		generator = new Random();
		byt1 = new ArrayList<Population>();
		byt1.add(new Population(generator));
	}
	//uzywaj tego - drugi bedzie staly - w tym mozesz ustawic jaki maja byc te wspólczynniki z algorytmu <--super komentarz
	Model(double ratio,double multiplier1, double multiplier2, int mi, int lambda)
	{
		generator = new Random();
		byt1 = new ArrayList<Population>();
		byt1.add(new Population(ratio,multiplier1,multiplier2,mi,lambda,generator));
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
}