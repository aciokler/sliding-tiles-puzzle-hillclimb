package algorithms.hillclimbsearch;

public interface SearchOperatorApplier<T extends State, K extends SearchOperator> {

	public T applySearchOperator(T inState, K searchOperator);

}
