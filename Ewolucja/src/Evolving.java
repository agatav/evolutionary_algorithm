public interface Evolving<T>
{
	double getFitness();
	T getArgs();
	Evolving<T> create(T source);
}
