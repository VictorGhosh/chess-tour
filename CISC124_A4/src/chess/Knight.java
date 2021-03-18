package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a knight located on a {@code board} object.
 */
public class Knight {

	/**
	 * The current location of the knight
	 */
	private Location cur;

	/**
	 * List of past locations of knight
	 */
	private List<Location> past;

	/**
	 * The {@code Board} object the knight is on
	 */
	private Board board;

	/**
	 * Make knight object at Location {@code loc} and on Board {@code b}. This
	 * constructor assumes {@code loc} is valid for {@code b}.
	 * 
	 * @param loc location to create knight
	 * @param b   board for knight to be on
	 */
	public Knight(Location loc, Board b) {
		this.cur = loc;
		this.past = new ArrayList<>();
		this.board = b;
		this.past.add(loc);
	}

	/**
	 * Returns a list of all possible moves for knight from {@code loc}. List does
	 * not include illegal moves or moves the knight has already made.
	 * 
	 * @param loc location to find moves from
	 * @return List of possible moves for knight from {@code loc}
	 */
	private List<Location> moves(Location loc) {
		List<Location> res = new ArrayList<>();
		int[][] trans = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };

		for (int[] i : trans) {
			Location newLoc = new Location(loc.x() + i[0], loc.y() + i[1]);
			if (squareIsNew(newLoc) && this.board.realSquare(newLoc)) {
				res.add(newLoc);
			}
		}
		return res;
	}

	/**
	 * Returns true if {@code loc} has not been traveled yet and false otherwise.
	 * This method accepts squares out of bounds.
	 * 
	 * @param loc Location in question
	 * @return true if knight has been there or false otherwise
	 */
	private boolean squareIsNew(Location loc) {
		if (this.past.contains(loc)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the location of the best move for the knight to move. In other words
	 * the move with the least number of possible moves following it and that the
	 * knight has not been to.
	 * 
	 * @return Location of best move and null if there is no move.
	 */
	private Location bestMove() {
		if (!hasMove()) {
			return null;
		}

		List<Location> allMoves = moves(this.cur);

		int leastMoves = moves(allMoves.get(0)).size();
		Location res = allMoves.get(0);
		for (int i = allMoves.size() - 1; i >= 0; i--) {
			if (moves(allMoves.get(i)).size() < leastMoves) {
				leastMoves = moves(allMoves.get(i)).size();
				res = allMoves.get(i);
			}
		}
		return res;
	}

	/**
	 * Move this knight to the best next location. Assumes a next location exists.
	 * 
	 * @param loc Location to move to
	 * @return location that was moved to.
	 */
	public Location move() {
		this.cur = bestMove();
		this.past.add(this.cur);
		return this.cur;
	}

	/**
	 * Returns true if the knight has a possible move and false otherwise.
	 * 
	 * @return true if the knight can move and false if it cannot
	 */
	public boolean hasMove() {
		if (moves(this.cur).isEmpty()) {
			return false;
		}
		return true;
	}

}
