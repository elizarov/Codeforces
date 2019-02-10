package archive.r110;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_110_E_Test extends TestCase {
	public void testMax() {
		Round_110_E t = new Round_110_E();
		t.n = 10000;
		t.a = new long[t.n];
		Arrays.fill(t.a, 1000000000000000000L);
		t.m = 30000;
		t.d = new int[t.m];
		Arrays.fill(t.d, 10);
		t.s = new char[t.m][30];
		Arrays.fill(t.s, "??????????????????????????????".toCharArray());
		t.c = new long[t.m];
		Arrays.fill(t.c, 1000000000000000000L);

		long time = System.currentTimeMillis();
		t.calc();
		System.out.println(System.currentTimeMillis() - time);
	}
}
