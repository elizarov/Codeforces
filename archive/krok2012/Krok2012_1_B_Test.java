package archive.krok2012;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Krok2012_1_B_Test extends TestCase {
	public void testMax() {
		Krok2012_1_B instance = new Krok2012_1_B();
		int n = 1000;
		instance.n = n;
		instance.m = n;
		instance.a = new char[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(instance.a[i], '#');
		long time = System.currentTimeMillis();
		assertEquals(2, instance.solve());
		System.out.println(System.currentTimeMillis() - time);
	}

	public void testMaxChain() {
		Krok2012_1_B instance = new Krok2012_1_B();
		int n = 1000;
		instance.n = n;
		instance.m = n;
		instance.a = new char[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				instance.a[i][j] = (i == j || i - 1 == j) ? '#' : '.';
		long time = System.currentTimeMillis();
		assertEquals(2 * n - 2, instance.solve());
		System.out.println(System.currentTimeMillis() - time);
	}
}
