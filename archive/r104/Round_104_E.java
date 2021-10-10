package archive.r104;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_104_E {
	private static final int MOD = 1000000007;

	public static void main(String[] args) {
		new Round_104_E().go();
	}

	int n;
	int k;
	int m;
	int t;
	int[] a;
	int[] dup;
	int[][] mem;
	int[] fac;
	int[] c;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		n = in.nextInt();
		k = in.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();
		out.println(solve());
	}

	int solve() {
		Arrays.sort(a);
		dup = new int[n];
		m = 0;
		boolean washappy = false;
		for (int i = 0; i < n; i++) {
			int x = a[i];
			boolean same = i > 0 && x == a[i - 1];
			if (same && washappy) {
				dup[m - 1]++;
			} else {
				dup[m++] = 1;
			}
			washappy = same ? washappy : happy(x);
		}
		Arrays.sort(dup, 0, m);
		t = 0;
		while (t < m && dup[t] == 1)
			t++;
		mem = new int[m - t + 1][m - t + 1];
		for (int[] ints : mem) {
			Arrays.fill(ints, -1);
		}
		// precompute factorial table
		fac = new int[t + 1];
		fac[0] = 1;
		for (int i = 1; i <= t; i++)
			fac[i] = mul(fac[i - 1], i);
		// compute binomials as needed
		c = new int[t + 1];
		Arrays.fill(c, -1);
		return compute(t, 0);
	}

	private int compute(int i, int done) {
		if (mem[i - t][done] >= 0)
			return mem[i - t][done];
		if (done >= k)
			return mem[i - t][done] = 1;
		if (i >= m)
			return mem[i - t][done] = c(k - done);
		return mem[i - t][done] = add(
				compute(i + 1, done),
				mul(compute(i + 1, done + 1), dup[i]));
	}

	private int mul(long a, long b) {
		return (int)((a * b) % MOD);
	}

	private int add(long a, long b) {
		return (int)((a + b) % MOD);
	}

	private int pow(long a, int p) {
		int res = 1;
		// a^p * res == original a^p
		while (p > 0) {
			if ((p & 1) != 0)
				res = mul(res, a);
			a = mul(a, a);
			p >>= 1;
		}
		return res;
	}

	private int inv(long a) {
		return pow(a, MOD - 2);
	}

	private int div(long a, long b) {
		return mul(a, inv(b));
	}


	// choose k from t
	private int c(int k) {
		if (k > t)
			return 0;
		if (c[k] >= 0)
			return c[k];
		return c[k] = div(fac[t], mul(fac[k], fac[t - k]));
	}

	private boolean happy(int x) {
		while (x != 0) {
			int d = x % 10;
			if (d != 4 && d != 7)
				return false;
			x /= 10;
		}
		return true;
	}
}
