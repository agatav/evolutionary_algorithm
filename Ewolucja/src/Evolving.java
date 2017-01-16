import java.util.ArrayList;

public interface Evolving<T>
{
	double getFitness();
	T getArgs();
	ArrayList<Double> getArrayArgs();
	Evolving<T> create(T source,Object sigma);
	Object getSigma();
}

