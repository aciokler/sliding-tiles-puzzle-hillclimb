package algorithms.hillclimbsearch;

public interface EvaluationFunction<T extends State, K extends Comparable<K>> {

	public K evaluate(T currentState, T finalState);

}
