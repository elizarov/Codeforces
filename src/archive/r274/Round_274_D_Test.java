package archive.r274;

import junit.framework.TestCase;

import java.util.Random;

/**
 * @author Roman Elizarov
 */
public class Round_274_D_Test extends TestCase {
	public void testMax() {
		Round_274_D t = new Round_274_D();
		t.n = 500;
		t.S = 1000;
		t.boxes = new Round_274_D.Box[t.n];
		Random r = new Random(1);
		for (int i = 0; i < t.n; i++) {
			int in = r.nextInt(500);
			int out = r.nextInt(500) + 500;
			int w = r.nextInt(100);
			int s = r.nextInt(1000);
			int v = r.nextInt(1000);
			t.boxes[i] = new Round_274_D.Box(in, out, w, s, v);
		}
		long time = System.currentTimeMillis();
		System.out.println("Result: " + t.solve() + " in " + (System.currentTimeMillis() - time) + " ms");
	}
}
