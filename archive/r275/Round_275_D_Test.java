package archive.r275;

import junit.framework.TestCase;

public class Round_275_D_Test extends TestCase {
	public void test1() {
		Round_275_D t = new Round_275_D();
		t.n = 1;
		t.p = new int[1];
		int res = t.solve();
		assertEquals(1, res);
	}

	public void test2() {
		Round_275_D t = new Round_275_D();
		t.n = 2;
		t.p = new int[2];
		int res = t.solve();
		assertEquals(2, res);
	}

	public void test3() {
		Round_275_D t = new Round_275_D();
		t.n = 3;
		t.p = new int[3];
		int res = t.solve();
		assertEquals(5, res);
	}

}