package slidingtiles;

import algorithms.hillclimbsearch.SearchOperatorApplier;
import slidingtiles.TilesSwap.SwapDirections;

public class TilesSwapper implements SearchOperatorApplier<TilesState, TilesSwap> {

	@Override
	public TilesState applySearchOperator(TilesState inState, TilesSwap searchOperator) {
		return applyDirection(searchOperator.getDirection(), inState);
	}

	private TilesState applyDirection(SwapDirections dir, TilesState inState) {

		Tile emptyTile = inState.getEmptyTile();
		Tile swappingTile = inState.getTileAt(emptyTile.getRow() + dir.getRowDelta(),
				emptyTile.getCol() + dir.getColDelta());

		if (swappingTile == null) {
			return inState;
		}

		return inState.generateNewStateWithSwappedTiles(new Tile(swappingTile.getRow(), swappingTile.getCol()),
				new Tile(swappingTile.getValue(), emptyTile.getRow(), emptyTile.getCol()), dir);
	}

}
