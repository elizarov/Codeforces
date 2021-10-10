package archive.r275;

import junit.framework.TestCase;

import java.util.Random;

public class Round_275_B_Test extends TestCase {
	public void testMaxRnd() {
		Round_275_B t = new Round_275_B();
		t.n = 100000;
		t.m = 100000;
		t.cs = new Round_275_B.Cons[t.m];
		Random rnd = new Random(1);
		for (int i = 0; i < t.m; i++) {
			int l = rnd.nextInt(t.n);
			int r = l + rnd.nextInt(t.n - l);
			int q = rnd.nextInt(1 << 30);
			t.cs[i] = new Round_275_B.Cons(l, r, q);
		}
		long time = System.currentTimeMillis();
		System.out.println(t.solve());
		System.out.println(System.currentTimeMillis() - time);
	}

	public void testMaxMax() {
		Round_275_B t = new Round_275_B();
		t.n = 100000;
		t.m = 100000;
		t.cs = new Round_275_B.Cons[t.m];
		Random rnd = new Random(1);
		for (int i = 0; i < t.m; i++) {
			int l = 0;
			int r = t.n - 1;
			int q = rnd.nextInt(1 << 30);
			t.cs[i] = new Round_275_B.Cons(l, r, q);
		}
		long time = System.currentTimeMillis();
		System.out.println(t.solve());
		System.out.println(System.currentTimeMillis() - time);
	}

	public void testMaxMaxMax() {
		Round_275_B t = new Round_275_B();
		t.n = 100000;
		t.m = 100000;
		t.cs = new Round_275_B.Cons[t.m];
		Random rnd = new Random(1);
		for (int i = 0; i < t.m; i++) {
			int l = 0;
			int r = t.n - 1;
			int q = (1 << 30) - 1;
			t.cs[i] = new Round_275_B.Cons(l, r, q);
		}
		long time = System.currentTimeMillis();
		System.out.println(t.solve());
		System.out.println(System.currentTimeMillis() - time);
	}

}