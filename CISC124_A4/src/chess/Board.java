package chess;

/**
 * A class that represents a rectangular board object.
 */
public class Board {

	/**
	 * The dimensions of the board
	 */
	private int width;
	private int height;

	/**
	 * Create square 8x8 board
	 */
	public Board() {
		this(8, 8);
	}

	/**
	 * Create board with given dimensions
	 * 
	 * @param width  width of board
	 * @param height height of board
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns true if {@code loc} is within the bounds of this board. returns false
	 * otherwise.
	 * 
	 * @param loc Location to check
	 * @return true if {@code loc} if within the board. false if {@code loc} if out
	 *         of bounds.
	 */
	public boolean realSquare(Location loc) {
		if (loc.x() < 1 || loc.y() < 1 || loc.x() > this.width || loc.y() > this.height) {
			return false;
		}
		return true;
	}
}
