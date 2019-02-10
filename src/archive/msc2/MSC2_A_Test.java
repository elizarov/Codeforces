package archive.msc2;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Roman Elizarov
 */
public class MSC2_A_Test extends TestCase {
	public void testMax() {
		MSC2_A t = new MSC2_A();
		t.a = new byte[MSC2_A.MAX_LEN + 1];
		t.b = new byte[MSC2_A.MAX_LEN + 1];
		int n = 100000;
		Arrays.fill(t.a, t.a.length - n, t.a.length, (byte)1);
		Arrays.fill(t.b, t.b.length - n, t.b.length, (byte)1);
		long time = System.currentTimeMillis();
		String s = t.solve();
		time = System.currentTimeMillis() - time;
		System.out.println(s + " in " + time + " ms");
		assertEquals("=", s);
	}

	public void testMax2() {
		MSC2_A t = new MSC2_A();
		t.a = new byte[MSC2_A.MAX_LEN + 1];
		t.b = new byte[MSC2_A.MAX_LEN + 1];
		int n = 100000;
		Arrays.fill(t.a, t.a.length - n, t.a.length, (byte)1);
		Arrays.fill(t.b, t.b.length - n, t.b.length, (byte)1);
		t.a[t.a.length - n/2] = 0;
		long time = System.currentTimeMillis();
		String s = t.solve();
		time = System.currentTimeMillis() - time;
		System.out.println(s + " in " + time + " ms");
		assertEquals("<", s);
	}

	public void testMax3() {
		MSC2_A t = new MSC2_A();
		t.a = new byte[MSC2_A.MAX_LEN + 1];
		t.b = new byte[MSC2_A.MAX_LEN + 1];
		int n = 100000;
		Arrays.fill(t.a, t.a.length - n, t.a.length, (byte)1);
		Arrays.fill(t.b, t.b.length - n, t.b.length, (byte)1);
		t.b[t.b.length - 1] = 0;
		long time = System.currentTimeMillis();
		String s = t.solve();
		time = System.currentTimeMillis() - time;
		System.out.println(s + " in " + time + " ms");
		assertEquals(">", s);
	}

	public void testMax4() {
		MSC2_A t = new MSC2_A();
		t.a = new byte[MSC2_A.MAX_LEN + 1];
		int n = 100000;
		Random r = new Random(20140811);
		for (int i = 0; i < n; i++)
			t.a[t.a.length - i - 1] = (byte)r.nextInt(2);
		t.b = t.a.clone();
		long time = System.currentTimeMillis();
		String s = t.solve();
		time = System.currentTimeMillis() - time;
		System.out.println(s + " in " + time + " ms");
		assertEquals("=", s);
	}
}
