package archive.krok2012;

import java.util.*;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Krok2012_1_D_Test extends TestCase {
	static class Edge {
		int a;
		int b;

		Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	public void testStress() {
		int n = 30;
		Random rnd = new Random(1234);
		for (int rep = 0; rep < 10000; rep++) {
			Krok2012_1_D t = new Krok2012_1_D();
			t.n = n;
			t.init();
			List<Edge> all = new ArrayList<Edge>();
			int k = 0;
			int[] s = new int[n];
			for (int i = 0; i < n; i++)
				s[i] = rnd.nextInt(2);
			for (int i = 0; i < n; i++)
				for (int j = i + 1; j < n; j++)
					if (s[i] != s[j])
						all.add(new Edge(i, j));
			Collections.shuffle(all, rnd);
			int m = all.size() == 0 ? 0 : rnd.nextInt(all.size());
			for (int i = 0; i < m; i++) {
				Edge e = all.get(i);
				t.e.get(e.a).add(e.b);
				t.e.get(e.b).add(e.a);
			}
			t.solve();
		}
	}
}
