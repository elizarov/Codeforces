package archive.r275;

import junit.framework.TestCase;

import java.util.Random;

public class Round_275_C_Test extends TestCase {
	public void testMaxFull() {
		Round_275_C t = new Round_275_C();
		t.n = 50;
		t.m = 20;
		t.s = new int[t.n][t.m];
		Random rnd = new Random(1);
		for (int i = 0; i < t.n; i++)
			for (int j = 0; j < t.m; j++)
				t.s[i][j] = rnd.nextInt(Round_275_C.ALPHABET);
		long time = System.currentTimeMillis();
		System.out.println(t.solve());
		System.out.println(System.currentTimeMillis() - time + " ms");
	}

	public void testMaxAB() {
		Round_275_C t = new Round_275_C();
		t.n = 50;
		t.m = 20;
		t.s = new int[t.n][t.m];
		Random rnd = new Random(1);
		for (int i = 0; i < t.n; i++)
			for (int j = 0; j < t.m; j++)
				t.s[i][j] = rnd.nextInt(2);
		long time = System.currentTimeMillis();
		System.out.println(t.solve());
		System.out.println(System.currentTimeMillis() - time + " ms");
	}
}