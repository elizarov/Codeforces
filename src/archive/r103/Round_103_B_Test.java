package archive.r103;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author Roman Elizarov
 */
public class Round_103_B_Test extends TestCase {
	public void testMax() {
		Round_103_B a = new Round_103_B();
		int n = 1000;
		a.n = n;
		a.xa = -1000;
		a.ya = -1000;
		a.xb = 1000;
		a.yb = 1000;
		a.x = new int[n];
		a.y = new int[n];
		a.r = new int[n];
		Arrays.fill(a.r, 1000);
		long time = System.currentTimeMillis();
		assertEquals(a.solve(), 7996);
		System.out.println(System.currentTimeMillis() - time);
	}

}
