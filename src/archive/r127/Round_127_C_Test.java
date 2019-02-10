package archive.r127;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_127_C_Test extends TestCase {
	public void testMax() {
		long time = System.currentTimeMillis();
		Round_127_C t = new Round_127_C();
		t.n = 100000;
		t.allocate();
		t.solve();
		System.out.println(System.currentTimeMillis() - time);
	}

	public void testSome() {
		assertEquals(1, solve(1));
		assertEquals(2, solve(2));
		assertEquals(3, solve(3));
		assertEquals(4, solve(4));
		assertEquals(5, solve(5));
		assertEquals(6, solve(6));
		assertEquals(6, solve(6));

		assertEquals(2, solve(1, 1));
		assertEquals(3, solve(1, 1, 1));
		assertEquals(4, solve(1, 1, 1, 1));
		assertEquals(5, solve(1, 1, 1, 1, 1));

		assertEquals(5, solve(2, 1, 2, 1));
	}

	private long solve(int... a) {
		Round_127_C t = new Round_127_C();
		t.n = a.length + 1;
		t.allocate();
		System.arraycopy(a, 0, t.a, 1, a.length);
		return t.solve();
	}
}
