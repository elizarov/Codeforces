package archive.r118;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_118_C_Test extends TestCase {
	public void testMax() {
		Round_118_C t = new Round_118_C();
		t.n = 50;
		long time = System.currentTimeMillis();
		t.alloc();
		t.solve();
		System.out.println(System.currentTimeMillis() - time);
	}

	public void testStrange() {
		//6
		//1 1 2 2 1 1
		//1 1 2 2 1 1
		//2 4 2 4 2
		//2 2 2 2
		//4 10 4
		//4 4
		//8
		Round_118_C t = new Round_118_C();
		t.n = 6;
		t.a = new int[]
		    { 1, 1, 2, 2, 1, 1};
		t.w = new int[][] {
			{ 1, 1, 2, 2, 1, 1 },
			  { 2, 4, 2, 4, 2 },
			   { 2, 2, 2, 2 },
			     { 4,10, 4 },
			      { 4, 4 },
			        { 8 }};
		boolean result = t.solve();
		assertFalse(result);
	}
}
