package archive.krok2012;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Krok2012_1_C_Test extends TestCase {
	public void testMax() {
		Krok2012_1_C instance = new Krok2012_1_C();
		int n = 500;
		instance.n = 500;
		instance.m = 500;
		instance.a = new int[500][500];
		for (int i = 0; i < n; i++)
			Arrays.fill(instance.a[i], 1000);
		long time = System.currentTimeMillis();
		assertEquals(124999000, instance.solve());
		System.out.println(System.currentTimeMillis() - time);
	}
}
