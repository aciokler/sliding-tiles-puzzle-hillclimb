package slidingtiles;

public class Tile {

	private int value;
	private int row;
	private int col;

	public Tile(int row, int col) {
		this.row = row;
		this.col = col;
		this.value = -1;
	}

	public Tile(int value, int row, int col) {
		this(row, col);
		this.value = value;
	}

	public boolean isEmpty() {
		return this.value == -1;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
