package archive.r119;

import java.util.Random;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_119_C_Test extends TestCase {
	public void testMax() {
		Round_119_C t = new Round_119_C();
		t.n = 100000;
		t.m = 200000;
		t.k = 10;
		t.allocate();
		Random r = new Random(1234);
		for (int i = 0; i < t.m; i++) {
			int u;
			int v;
			do {
				u = r.nextInt(t.n);
				v = r.nextInt(t.n);
			} while (u == v);
			t.add(u, v);
			t.add(v, u);
		}
		t.s = 0;
		t.t = t.n - 1;
		t.good[0] = true;
		for (int i = 1; i < t.k; i++) {
			int u;
			do {
				u = r.nextInt(t.n);
			} while (t.good[u]);
			t.good[u] = true;
		}
		long time = System.currentTimeMillis();
		System.out.println(t.solve());
		System.out.println(System.currentTimeMillis() - time);
	}
}
