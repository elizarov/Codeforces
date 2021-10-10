package archive.r255;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_255_B {
	public static void main(String[] args) {
		new Round_255_B().go();
	}

	int n;
	int m;
	int k;
	int p;
	int[][] a;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		p = in.nextInt();
		a = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				a[i][j] = in.nextInt();

		// solve
		long result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	long solve() {
		long[] rs = new long[n];
		long[] cs = new long[m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				rs[i] += a[i][j];
				cs[j] += a[i][j];
			}
		heapify(rs);
		heapify(cs);

		long[] ra = new long[k + 1];
		for (int op = 1; op <= k; op++) {
			ra[op] += ra[op - 1] + rs[0];
			rs[0] -= p * m;
			siftUp(rs, 0);
		}

		long[] ca = new long[k + 1];
		for (int op = 1; op <= k; op++) {
			ca[op] += ca[op - 1] + cs[0];
			cs[0] -= p * n;
			siftUp(cs, 0);
		}
		long result = Long.MIN_VALUE;
		for (int op = 0; op <= k; op++)
			result = Math.max(result, ca[op] + ra[k - op] - (long) op * (k - op) * p);
		return result;
	}

	private void heapify(long[] s) {
		for (int i = s.length; --i >= 0;)
			siftUp(s, i);
	}

	private void siftUp(long[] s, int i) {
		while (2 * i + 1 < s.length) {
			int j = 2 * i + 1;
			if (j + 1 < s.length && s[j + 1] > s[j])
				j++;
			if (s[i] >= s[j])
				break;
			long t = s[i];
			s[i] = s[j];
			s[j] = t;
			i = j;
		}
	}
}
