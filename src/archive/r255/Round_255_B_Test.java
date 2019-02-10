package archive.r255;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_255_B_Test extends TestCase {
	public void test1() {
		Round_255_B t = new Round_255_B();
		t.n = 2;
		t.m = 2;
		t.k = 2;
		t.p = 2;
		t.a = new int[][] {
				{ 1, 3 },
				{ 2, 4 }};
		assertEquals(11, t.solve());
	}

	public void test2() {
		Round_255_B t = new Round_255_B();
		t.n = 2;
		t.m = 2;
		t.k = 5;
		t.p = 2;
		t.a = new int[][] {
				{ 1, 3 },
				{ 2, 4 }};
		assertEquals(11, t.solve());
	}

	public void test2_plus() {
		Round_255_B t = new Round_255_B();
		t.n = 3;
		t.m = 3;
		t.k = 2;
		t.p = 1000;
		t.a = new int[][] {
				{ 3, 6, 3 },
				{ 5, 5, 5 },
				{ 5, 5, 5 }};
		assertEquals(30, t.solve());
	}

	public void test3() {
		Round_255_B t = new Round_255_B();
		t.n = 2;
		t.m = 3;
		t.k = 1;
		t.p = 2;
		t.a = new int[][] {
				{ 1, 3, 2 },
				{ 2, 4, 3 }}; // -2
		assertEquals(9, t.solve());
	}

	public void test4() {
		Round_255_B t = new Round_255_B();
		t.n = 2;
		t.m = 3;
		t.k = 2;
		t.p = 2;
		t.a = new int[][] {
				{ 1, 3, 2 },  // -2
				{ 2, 4, 3 }}; // -2
		assertEquals(15, t.solve());
	}

	public void test5() {
		Round_255_B t = new Round_255_B();
		t.n = 2;
		t.m = 3;
		t.k = 3;
		t.p = 2;
		t.a = new int[][] {
				//  -2
				{ 1, 3, 2 },  // -2
				{ 2, 4, 3 }}; // -2
		assertEquals(18, t.solve());
	}

	public void test6() {
		Round_255_B t = new Round_255_B();
		t.n = 2;
		t.m = 3;
		t.k = 4;
		t.p = 2;
		t.a = new int[][] {
				//  -2  -2
				{ 1, 3, 2 },  // -2
				{ 2, 4, 3 }}; // -2
		assertEquals(19, t.solve());
	}

	public void test7() {
		Round_255_B t = new Round_255_B();
		t.n = 2;
		t.m = 3;
		t.k = 5;
		t.p = 2;
		t.a = new int[][] {
				//-2 -2 -2
				{ 1, 3, 2 },  // -2
				{ 2, 4, 3 }}; // -2
		assertEquals(19, t.solve());
	}

	public void test8() {
		Round_255_B t = new Round_255_B();
		t.n = 1;
		t.m = 1;
		t.k = 1;
		t.p = 100;
		t.a = new int[][] {
				{ 33 }};
		assertEquals( 33, t.solve());
	}

	public void test9() {
		Round_255_B t = new Round_255_B();
		t.n = 1;
		t.m = 1;
		t.k = 2;
		t.p = 100;
		t.a = new int[][] {
				{ 33 }};
		assertEquals( -34, t.solve());
	}

	public void test10() {
		Round_255_B t = new Round_255_B();
		t.n = 1;
		t.m = 1;
		t.k = 1000_000;
		t.p = 100;
		t.a = new int[][] {
				{ 1 }};
		assertEquals( 1 * 1000_000L - 1000_000L * (1000_000L - 1) / 2 * 100, t.solve());
	}

	public void test11() {
		Round_255_B t = new Round_255_B();
		t.n = 1;
		t.m = 1000;
		t.k = 1;
		t.p = 1;
		t.a = new int[1][1000];
		for (int i = 0; i < t.m; i++)
			t.a[0][i] = i + 1;
		assertEquals( 500500, t.solve());
	}

	public void test12() {
		Round_255_B t = new Round_255_B();
		t.n = 1;
		t.m = 1000;
		t.k = 1;
		t.p = 1;
		t.a = new int[1][1000];
		for (int i = 0; i < t.m; i++)
			t.a[0][i] = -(i + 1);
		assertEquals( -1, t.solve());
	}

	public void test13() {
		Round_255_B t = new Round_255_B();
		t.n = 1;
		t.m = 1000;
		t.k = 1000;
		t.p = 1000;
		t.a = new int[1][1000];
		for (int i = 0; i < t.m; i++)
			t.a[0][i] = -(i + 1);
		assertEquals( -500500, t.solve());
	}

	public void testMax() {
		Round_255_B t = new Round_255_B();
		t.n = 1000;
		t.m = 1000;
		t.k = 1000_000;
		t.p = 100;
		t.a = new int[t.n][t.m];
		for (int i = 0; i < t.n; i++)
			for (int j = 0; j < t.m; j++)
				t.a[i][i] = 1000;
		long time = System.currentTimeMillis();
		long ans = t.solve();
		time = System.currentTimeMillis() - time;
		System.out.println(ans + " in " + time + " ms");
	}
}
