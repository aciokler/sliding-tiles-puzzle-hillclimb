package slidingtiles;

import java.security.InvalidParameterException;

import algorithms.hillclimbsearch.State;
import slidingtiles.TilesSwap.SwapDirections;

public class TilesState implements State {

	private int width;
	private int height;
	private int emptyTilePos;
	private SwapDirections swapDirectionUsed;

	private Tile[] tiles;

	public TilesState(int width, int height, Integer[] values) {
		if (values.length != width * height) {
			throw new InvalidParameterException("number of values doesn't match with the width and height");
		}
		this.width = width;
		this.height = height;
		tiles = new Tile[width * height];
		for (int i = 0; i < (width * height); i++) {
			// System.out.println("pos: " + i + ", row: " + ((i - (i % width)) /
			// height) + ", col: " + i % width);
			tiles[i] = new Tile(values[i], (i - (i % width)) / height, i % width);
		}
		findEmptyTilePos();
	}

	public TilesState(int width, int height, Integer[] values, SwapDirections swapDirectionUsed) {
		this(width, height, values);
		this.swapDirectionUsed = swapDirectionUsed;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTileAt(int row, int col) {
		if (row < 0 || row >= height || col < 0 || col >= width) {
			return null;
		}
		return tiles[row * width + col];
	}

	public Tile getTileFromValue(Integer value) {
		for (int row = 0; row < height; row++)
			for (int col = 0; col < width; col++)
				if (tiles[row * width + col].getValue() == value)
					return tiles[row * width + col];
		return null;
	}

	private void findEmptyTilePos() {
		for (int row = 0; row < height; row++)
			for (int col = 0; col < width; col++)
				if (tiles[row * width + col].isEmpty()) {
					emptyTilePos = row * width + col;
					break;
				}
	}

	public int getEmptyTilePos() {
		return this.emptyTilePos;
	}

	public Tile getEmptyTile() {
		return tiles[emptyTilePos];
	}

	private Tile[] getTiles() {
		return this.tiles;
	}

	public TilesState generateNewStateWithSwappedTiles(Tile emptyTile, Tile swappedTile,
			SwapDirections swapDirectionUsed) {
		Tile[] tiles = getTiles();
		Integer[] newStateValues = new Integer[width * height];

		for (int i = 0; i < tiles.length; i++) {
			newStateValues[i] = tiles[i].getValue();
		}

		newStateValues[emptyTile.getRow() * this.getHeight() + emptyTile.getCol()] = emptyTile.getValue();
		newStateValues[swappedTile.getRow() * this.getHeight() + swappedTile.getCol()] = swappedTile.getValue();
		return new TilesState(this.getWidth(), this.getHeight(), newStateValues, swapDirectionUsed);
	}

	public Tile generateSwappedTile() {
		if (swapDirectionUsed == null) {
			return null;
		}

		Tile emptyTile = getEmptyTile();
		SwapDirections reverseDirection = SwapDirections.reverseDirection(swapDirectionUsed);
		return getTileAt(emptyTile.getRow() + reverseDirection.getRowDelta(),
				emptyTile.getCol() + reverseDirection.getColDelta());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj instanceof TilesState) {
			TilesState state = (TilesState) obj;
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i].getValue() != state.getTileAt(tiles[i].getRow(), tiles[i].getCol()).getValue())
					return false;
			}
		}

		return true;
	}

	@Override
	public int hashCode() {
		int dir = 1;
		int hashCode = 0;

		// alternate between negative and positive values and sum
		for (int i = 0; i < tiles.length; i++) {
			hashCode += tiles[i].getValue() * (dir * -1);
		}
		return hashCode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				sb.append(tiles[row * width + col].getValue() + ",");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		return sb.toString();
	}
}
