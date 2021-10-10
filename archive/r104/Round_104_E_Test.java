package archive.r104;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_104_E_Test extends TestCase {

	private static final int MAX = 100000;

	public void testMax() {
		Round_104_E sol = new Round_104_E();
		sol.n = MAX;
		sol.k = MAX;
		sol.a = new int[MAX];
		for (int i = 0; i < MAX; i++)
			sol.a[i] = 1000000000 - i;
		assertEquals(1, sol.solve());
	}

	public void testMax_Half() {
		Round_104_E sol = new Round_104_E();
		sol.n = MAX;
		sol.k = MAX / 2;
		sol.a = new int[MAX];
		for (int i = 0; i < MAX; i++)
			sol.a[i] = 1000000000 - i;
		assertEquals(149033233, sol.solve());
	}

	public void testMax2() {
		Round_104_E sol = new Round_104_E();
		sol.n = MAX;
		sol.k = MAX;
		sol.a = new int[MAX];
		for (int i = 0; i < MAX; i++)
			sol.a[i] = 800000000 + i; // unhappy
		assertEquals(1, sol.solve());
	}

	public void testMax2_Half() {
		Round_104_E sol = new Round_104_E();
		sol.n = MAX;
		sol.k = MAX / 2;
		sol.a = new int[MAX];
		for (int i = 0; i < MAX; i++)
			sol.a[i] = 800000000 + i; // unhappy
		assertEquals(149033233, sol.solve());
	}

	public void testMax3() {
		Round_104_E sol = new Round_104_E();
		sol.n = MAX;
		sol.k = MAX;
		sol.a = new int[MAX];
		// 256 happy
		for (int i = 0; i < 256; i++) {
			int x = 0;
			for (int j = 0; j < 8; j++)
				x = x * 10 + (((i >> j) & 1) == 0 ? 4 : 7);
			sol.a[i] = x;
		}
		// rest -- unhappy
		for (int i = 256; i < MAX; i++)
			sol.a[i] = 800000000 + i;
		assertEquals(1, sol.solve());
	}

	public void testMax3_Half() {
		Round_104_E sol = new Round_104_E();
		sol.n = MAX;
		sol.k = MAX / 2;
		sol.a = new int[MAX];
		// 256 happy
		for (int i = 0; i < 256; i++) {
			int x = 0;
			for (int j = 0; j < 8; j++)
				x = x * 10 + (((i >> j) & 1) == 0 ? 4 : 7);
			sol.a[i] = x;
		}
		// rest -- unhappy
		for (int i = 256; i < MAX; i++)
			sol.a[i] = 800000000 + i;
		assertEquals(149033233, sol.solve());
	}
}
