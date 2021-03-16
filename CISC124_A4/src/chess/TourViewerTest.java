package chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class TourViewerTest {
	
	@Test
	public void squares() {
		for (int i = 5; i < 100; i += 10) {
			int res = TourViewer.runTest(i, i, new Location(1,3));
			assertEquals(res, i * i);
		}
	}

	@Test
	public void smallRectangle() {
		int res = TourViewer.runTest(12, 5, new Location(1,3));
		assertEquals(res, 12 * 5);
	}
	
	@Test
	public void largeRectangle() {
		int res = TourViewer.runTest(100, 50, new Location(1,3));
		assertEquals(res, 100 * 50);
	}

}
