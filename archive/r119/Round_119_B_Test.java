package archive.r119;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_119_B_Test extends TestCase {
	public void testMax() {
		Round_119_B t = new Round_119_B();
		long time = System.currentTimeMillis();
		t.n = 60;
		t.m = 60;
		t.r = 0;
		t.allocate();
		t.solve();
		System.out.println(System.currentTimeMillis() - time);
	}
}
