package slidingtiles;

import algorithms.hillclimbsearch.HillClimbSearch;

public class SlidingTiles {

	private static final int WIDTH = 3;
	private static final int HEIGHT = 3;

	public static void main(String[] args) {
		Integer[] initialValues = { -1, 2, 1, 6, 7, 4, 3, 8, 5 };
		Integer[] finalValues = { 1, 2, 3, 8, -1, 4, 7, 6, 5 };

		TilesState initialState = new TilesState(WIDTH, HEIGHT, initialValues);
		TilesState finalState = new TilesState(WIDTH, HEIGHT, finalValues);

		HillClimbSearch<TilesState, TilesSwap, Integer, TilesEvaluationFunction> search = new HillClimbSearch<>(
				new TilesEvaluationFunction(), new TilesSwapper());

		boolean value = search.generalSearch(initialState, finalState, TilesSwap.getAllPossibleSwaps(), 4403);

		System.out.println("found it? " + value);
	}

}
