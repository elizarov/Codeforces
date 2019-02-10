package archive.krok2012;

import java.util.Random;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Krok2012_2_B_Test extends TestCase {
	public void testMaxA() {
		Krok2012_2_B t = new Krok2012_2_B();
		int n = 1000;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
			sb.append('a');
		t.a = sb.toString();
		t.b = sb.toString();
		t.k = 100000;
		long time = System.currentTimeMillis();
		t.solve();
		System.out.println(System.currentTimeMillis() - time);
	}

	public void testMaxRndDiff() {
		Krok2012_2_B t = new Krok2012_2_B();
		t.a = rndString(1000, 1);
		t.b = rndString(1000, 2);
		t.k = 100000;
		long time = System.currentTimeMillis();
		t.solve();
		System.out.println(System.currentTimeMillis() - time);
	}

	public void testMaxRndSame() {
		Krok2012_2_B t = new Krok2012_2_B();
		t.a = rndString(1000, 3);
		t.b = rndString(1000, 3);
		t.k = 100000;
		long time = System.currentTimeMillis();
		t.solve();
		System.out.println(System.currentTimeMillis() - time);
	}

	private static String rndString(int n, int seed) {
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random(seed);
		for (int i = 0; i < n; i++)
			sb.append((char)('a' + rnd.nextInt(26)));
		return sb.toString();
	}
}
