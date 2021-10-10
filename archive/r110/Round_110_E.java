package archive.r110;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_110_E {
	static final int[] PRIMES = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

	char[][][] ids;
	long[] a;
	int n;
	int m;
	int[] d;
	char[][] s;
	long[] c;
	int[] res;

	public static void main(String[] args) {
		new Round_110_E().go();
	}

	void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		
		n = in.nextInt();
		a = new long[n];
		for (int i = 0; i < n; i++)
			a[i] = in.nextLong();
		m = in.nextInt();
		d = new int[m];
		s = new char[m][];
		c = new long[m];
		for (int i = 0; i < m; i++) {
			d[i] = in.nextInt();
			s[i] = expand(in.next());
			c[i] = in.nextLong();
		}

		calc();
		
		for (int i = 0; i < m; i++) 
			out.println(res[i]);
		
	}

	void calc() {
		res = new int[m];
		Arrays.fill(res, -1);
		for (int d = 2; d <= 16; d++) {
			int tsz = 1;
			int rem = n;
			while (rem > 0) {
				rem /= d;
				tsz *= (d + 1);
			}
			byte[] t = new byte[tsz];
			for (int p : PRIMES) {
				buildNode(t, d, p, 0, 0);
			}
		}
		/*
		calcIds();
		for (int i = 0; i < m; i++) {
			boolean found = false;
			for (int p : PRIMES)
				if (compute(d[i], s[i], c[i], p) == 0) {
					res[i] = p;
					found = true;
					break;
				}
			if (!found)
				res[i] = -1;
		}
		*/
	}

	private int buildNode(byte[] t, int d, int p, int i, int prefix) {
		int j = (d + 1) * i;
		int nextPrefix = prefix * d;
		int res = 1;
		for (int k = 0; k < d; k++) {
			j++;
			if (j >= t.length || nextPrefix >= n)
				break;
			res = (res * buildNode(t, d, p, j, nextPrefix)) % p;
			nextPrefix++;
		}
		t[i] = (byte)res;
		return 0;
	}

	int compute(int d, char[] s, long c, int p) {
		int res = 1;
		for (int i = 0; i < n; i++)
			if (matches(ids[i][d], s))
				res = (res * (int)(a[i] % p)) % p;
		return (int)((res + c) % p);
	}

	boolean matches(char[] t, char[] s) {
		for (int i = 0; i < 30; i++)
			if (t[i] != s[i] && s[i] != '?')
				return false;
		return true;
	}

	char[] expand(String s) {
		char[] c = new char[30];
		int k = 30 - s.length();
		for (int i = 0; i < k; i++)
			c[i] = '0';
		System.arraycopy(s.toCharArray(), 0, c, k, s.length());
		return c;
	}

	void calcIds() {
		char[] digits = "0123456789ABCDEF".toCharArray();
		ids = new char[n][17][30];
		for (int i = 0; i < n; i++)
			for (int d = 2; d <= 16; d++) {
				int rem = i;
				for (int j = 30; --j >= 0;) {
					ids[i][d][j] = digits[rem % d];
					rem /= d;
				}
			}
	}
}
