package chess;

/**
 * A class that represents a tour by a {@code Knight} object on a {@code Board}
 * object
 */
public class Tour {

	/**
	 * The Board the tour is to be played on
	 */
	private Board board;

	/**
	 * The Knight on the board
	 */
	private Knight player;

	/**
	 * Creates a tour object with board size 8x8
	 */
	public Tour() {
		this(8, 8);
	}

	/**
	 * Creates tour object with board size {@code x} x {@code y}
	 * 
	 * @param x horizontal dimension of board
	 * @param y vertical dimension of board
	 */
	public Tour(int x, int y) {
		this.board = new Board(x, y);
	}

	/**
	 * Moves knight to {@code loc} and marks all board locations as unvisited with
	 * the exception of {@code loc}.
	 * 
	 * @param loc Location to start knight at
	 */
	public void startTour(Location loc) {
		this.player = new Knight(loc, this.board);
	}

	/**
	 * Returns true if there is a legal square the knight can move to that it has
	 * not been to yet. Returns false otherwise.
	 * 
	 * @return True if valid move exists and false otherwise.
	 */
	public boolean hasNext() {
		return this.player.hasMove();
	}

	/**
	 * Returns the location of the next move of the tour and moves the knight to
	 * that location. Returns {@code null} if the knight cannot move to an unvisited
	 * location from its current location.
	 * 
	 * @return Location knight was moved to or {@code null} if no move is possible
	 */
	public Location next() {
		if (!hasNext()) {
			return null;
		}

		return this.player.move();
	}

}
