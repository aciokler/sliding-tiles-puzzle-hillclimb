package slidingtiles;

import algorithms.hillclimbsearch.EvaluationFunction;

public class TilesEvaluationFunction implements EvaluationFunction<TilesState, Integer> {

	@Override
	public Integer evaluate(TilesState state, TilesState finalState) {
		return evaluationFunction(state, finalState);
	}

	private Integer evaluationFunction(TilesState state, TilesState finalState) {
		int sum = 0;
		for (int row = 0; row < finalState.getHeight(); row++) {
			for (int col = 0; col < finalState.getWidth(); col++) {
				Tile tile = state.getTileAt(row, col);
				Tile finalStateTile = finalState.getTileFromValue(tile.getValue());
				sum += Math.abs(finalStateTile.getRow() - tile.getRow())
						+ Math.abs(finalStateTile.getCol() - tile.getCol());
			}
		}

		return sum;
	}

	// private Integer trivialEval(TilesState state, TilesState finalState) {
	// Tile swappedTile = state.generateSwappedTile();
	// Tile finalStateTile =
	// finalState.getTileFromValue(swappedTile.getValue());
	// return Math.abs(finalStateTile.getRow() - swappedTile.getRow())
	// + Math.abs(finalStateTile.getCol() - swappedTile.getCol());
	// }
}
