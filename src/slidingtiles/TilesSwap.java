package slidingtiles;

import java.util.ArrayList;
import java.util.List;

import algorithms.hillclimbsearch.SearchOperator;

public class TilesSwap implements SearchOperator {

	private SwapDirections direction;

	public SwapDirections getDirection() {
		return direction;
	}

	public void setDirection(SwapDirections direction) {
		this.direction = direction;
	}

	public TilesSwap(SwapDirections direction) {
		this.direction = direction;
	}

	public static List<TilesSwap> getAllPossibleSwaps() {
		List<TilesSwap> swaps = new ArrayList<>();
		for (SwapDirections dir : SwapDirections.values()) {
			swaps.add(new TilesSwap(dir));
		}
		return swaps;
	}

	public enum SwapDirections {
		UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

		private int rowDelta;
		private int colDelta;

		private SwapDirections(int rowDelta, int colDelta) {
			this.rowDelta = rowDelta;
			this.colDelta = colDelta;
		}

		public int getRowDelta() {
			return rowDelta;
		}

		public int getColDelta() {
			return colDelta;
		}

		public static SwapDirections reverseDirection(SwapDirections dir) {
			switch (dir) {
			case UP:
				return DOWN;
			case DOWN:
				return UP;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			}
			return null;
		}

	}

}
