package archive.r131;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_131_B_Test extends TestCase {
	public void testMax() {
		long time = System.currentTimeMillis();
		Round_131_B t = new Round_131_B();
		t.n = 100;
		Arrays.fill(t.a, 9);
		System.out.println(t.solve());
		System.out.println(System.currentTimeMillis() - time);
	}
}
