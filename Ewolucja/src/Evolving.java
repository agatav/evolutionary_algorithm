import java.util.ArrayList;

public interface Evolving<T>
{
	double getFitness();
	ArrayList<T> getArgs();
}
