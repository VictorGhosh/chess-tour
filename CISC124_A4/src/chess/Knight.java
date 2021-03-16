package chess;

import java.util.ArrayList;
import java.util.List;

public class Knight {

	/**
	 * The current location of the knight
	 */
	private Location cur;

	/**
	 * List of past locations of knight
	 */
	private List<Location> past;
	
	private Board board;

	/**
	 * Make knight object at ({@code x}, {@code y})
	 * 
	 * @param x x coordinate of knight
	 * @param y y coordinate of knight
	 */
	public Knight(Location loc, Board b) {
		this.past = new ArrayList<>();
		this.board = b;
		move(loc);
	}
	
	/**
	 * Returns the current location of the knight
	 * 
	 * @return The current location of the knight
	 */
	public Location getCurrentLoc() {
		return this.cur;
	}

	/**
	 * Returns the location of the best move for the knight to move. In other words
	 * the move with the least number of possible moves following it and that the knight
	 * has not been to.
	 * 
	 * @return Location of best move
	 */
	public Location bestMove() {
		List<Location> allMoves = moves(this.cur);

		System.out.println(allMoves);
		
		int leastMoves = moves(allMoves.get(0)).size();
		Location res = allMoves.get(0);
		for (int i = allMoves.size() - 1; i >= 0; i--) {
			if (moves(allMoves.get(i)).size() < leastMoves) {
				leastMoves = moves(allMoves.get(i)).size();
				res = allMoves.get(i);
			}
		}

		//TODO: This is a cleaner way to do it but its broken for some reason
//		int numNextMoves = moves(allMoves.get(0)).size();
//		Location move = allMoves.get(0);
//		for (Location i : allMoves) {
//			if (moves(i).size() < numNextMoves) {
//				move = i;
//				numNextMoves = moves(i).size();
//			}
//		}
		return res;
	}

	/**
	 * Returns a list of all possible moves for knight from {@code loc}. List
	 * does not include illegal moves or moves the knight has already made.
	 * 
	 * @return List of possible moves
	 */
	public List<Location> moves(Location loc) {
		List<Location> res = new ArrayList<>();

		int[][] trans = {
				{-2,-1}, {-2,1},
				{-1,-2}, {-1,2},
				{1,-2}, {1,2},
				{2,-1}, {2,1}
		};
		
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
	
	//TODO: update the javaDoc
	/**
	 * Update the list of past location with the current location of the knight and
	 * move knight to {@code loc}
	 */
	//TODO: Evaluate this one. its kind of pointless I think
	//TODO: add null shit for when location not ok
	public Location move(Location loc) {
		this.cur = loc;
		this.past.add(this.cur);
		return loc;
	}

}
