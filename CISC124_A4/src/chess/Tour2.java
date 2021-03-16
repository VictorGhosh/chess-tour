package chess;

import java.util.ArrayList;
import java.util.List;

public class Tour2 {

	/**
	 * List of past locations
	 */
	private List<Location> past;

	/**
	 * Size of rectangular board
	 */
	private int height;
	private int width;

	/**
	 * Current Location of player
	 */
	private static Location cur;

	public Tour2() {
		this(8, 8);
	}

	public Tour2(int x, int y) {
		this.past = new ArrayList<>();
		this.height = y;
		this.width = x;
	}

	/**
	 * Moves knight to {@code loc} and marks all board locations as unvisited with
	 * the exception of {@code loc}.
	 * 
	 * @param loc Location to start knight at
	 */
	public void startTour(Location loc) {
		this.past.clear();
		this.cur = loc;
		this.past.add(this.cur);
	}

	/**
	 * Returns true if there is a legal square the knight can move to that it has
	 * not been to yet. Returns false otherwise.
	 * 
	 * @return True if valid move exists and false otherwise.
	 */
	public boolean hasNext() {
		if (knightMoves(this.cur).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Returns the location of the next move of the tour and moves the knight to
	 * that location. Returns {@code null} if the knight cannot move to an unvisited
	 * location from its current location.
	 * 
	 * @return Location knight was moved to or {@code null} if no move is possible
	 */
	public Location next() {
		List<Location> moves = knightMoves(this.cur);

		if (!hasNext()) {
			return null;
		}

		System.out.println(moves);
		
		int leastMoves = knightMoves(moves.get(0)).size();
		Location res = moves.get(0);
		// TODO: Remove comment, its to include in explanation thing.
		// The strange loop is to account for changing moves.size()
		// without the need of another variable.
		for (int i = moves.size() - 1; i >= 0; i--) {
			if (knightMoves(moves.get(i)).size() < leastMoves) {
				leastMoves = knightMoves(moves.get(i)).size();
				res = moves.get(i);
			}
		}
		this.cur = res;
		this.past.add(this.cur);
		return res;
	}

	/**
	 * Returns all possible moves from {@code loc}. This does not include squares
	 * that the knight has already been on.
	 * 
	 * @param loc location to look for moves from
	 * @return List of all possible locations to move to
	 */
	private List<Location> knightMoves(Location loc) {
		List<Location> res = new ArrayList<>();
		
		int[][] trans = {
				{-2,-1}, {-2,1},
				{-1,-2}, {-1,2},
				{1,-2}, {1,2},
				{2,-1}, {2,1}
		};
		
		for (int[] i : trans) {
			Location newLoc = new Location(loc.x() + i[0], loc.y() + i[1]);
			if (locationOK(newLoc)) {
				res.add(newLoc);
			}
		}
		return res;
	}

	/**
	 * Returns true if the square at {@code loc} is in the board and the knight has
	 * not been on it yet. Returns false otherwise.
	 * 
	 * @param loc location to check
	 * @return true if location is valid and false otherwise
	 */
	private boolean locationOK(Location loc) {
		if (loc.x() < 1 || loc.y() < 1 || loc.x() > this.width || loc.y() > this.height || this.past.contains(loc)) {
			return false;
		}
		return true;
	}

}
