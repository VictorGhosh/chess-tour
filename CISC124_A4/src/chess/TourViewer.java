package chess;

import princeton.introcs.StdDraw;

public class TourViewer {

	/**
	 * Draws a regular rectangular chess board of the specified size.
	 * 
	 * <p>
	 * Students will need to modify this method to draw irregular boards if their
	 * solutions allows for irregular boards.
	 * 
	 * @param width  the number of squares in the width of the board
	 * @param height the number of squares in the height of the board
	 */
	private static void drawBoard(int width, int height) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException();
		}
		int max = Math.max(width, height);
		StdDraw.setScale(0.5, max + 0.5);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if ((i + j) % 2 == 0) {
					StdDraw.setPenColor(StdDraw.BLUE);
				} else {
					StdDraw.setPenColor(StdDraw.WHITE);
				}
				StdDraw.filledSquare(i + 1, j + 1, 0.5);
			}
		}
	}
	
	public static int runTest(int x, int y, Location start) {
		drawBoard(x, y);
		Tour t = new Tour(x, y);
		
		t.startTour(start);
		Location curr = start;
		int i = 2;
		while (t.hasNext()) {
			Location next = t.next();
			System.out.println(i + " : moving from " + curr + " to " + next);
			StdDraw.line(curr.x(), curr.y(), next.x(), next.y());
			StdDraw.filledCircle(next.x(), next.y(), 0.1);
			curr = new Location(next);
			// Thread.sleep(500);
			i++;
		}
		
		return i;
	}
	
	public static void main(String[] args) throws Exception {
		runTest(8,8,new Location(3,2));
		Thread.sleep(1000);
		runTest(8,16,new Location(3,2));
		Thread.sleep(1000);
		runTest(32,7,new Location(3,2));
		Thread.sleep(1000);
		
		// edit the next line to draw a board of the size that you are testing
		drawBoard(40, 40);

		StdDraw.setPenColor(StdDraw.BLACK);

		// create a Tour object on the next line
		Tour t = new Tour(40, 40);

		// depending on the structure of your solution you may have to make
		// some more objects here...

		Location start = new Location(3, 5);
		t.startTour(start);
		Location curr = start;
		int i = 2;
		while (t.hasNext()) {
			Location next = t.next();
			System.out.println(i + " : moving from " + curr + " to " + next);
			StdDraw.line(curr.x(), curr.y(), next.x(), next.y());
			StdDraw.filledCircle(next.x(), next.y(), 0.1);
			curr = new Location(next);
			// uncomment the next line to slow down the viewer; 500 is the pause time in
			// milliseconds
//			 Thread.sleep(500);
			i++;
		}
	}
}
