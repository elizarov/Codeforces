package archive.r127;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_127_A_Test extends TestCase {
	public void testAll() {
		assertEquals(1, solve(1));
		assertEquals(3, solve(2));
		assertEquals(5, solve(3));
		assertEquals(3, solve(4));
		assertEquals(3, solve(5));
		assertEquals(5, solve(6));
		assertEquals(5, solve(7));
		assertEquals(5, solve(8));
		assertEquals(5, solve(9));
		assertEquals(5, solve(10));
		assertEquals(5, solve(11));
		assertEquals(5, solve(12));
		assertEquals(5, solve(13));
		assertEquals(7, solve(14));

	}

	private int solve(int x) {
		Round_127_A t = new Round_127_A();
		t.x = x;
		return t.solve();
	}
}
