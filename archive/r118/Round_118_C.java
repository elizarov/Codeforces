package archive.r118;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Round_118_C {
	public static void main(String[] args) {
		new Round_118_C().go();
	}

	int n;
	int[] a;
	int[][] w;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		alloc();
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n - i; j++)
				w[i][j] = in.nextInt();

		// solve
		boolean result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result ? "Cerealguy" : "Fat Rat");
	}

	void alloc() {
		a = new int[n];
		w = new int[n][];
		for (int i = 0; i < n; i++)
			w[i] = new int[n - i];
	}

	int[] dp;
	int n2;
	int n3;

	boolean solve() {
		dp = new int[n * n * n * n];
		n2 = n * n;
		n3 = n2 * n;
		Arrays.fill(dp, -1);
		return calc(0, n, n - 1, 0, index(0, n, n - 1, 0)) > 0;
	}

	// [p, q)
	int calc(int p, int q, int r, int c, int index) {
		// assert 0 <= p < q <= n and 0 <= r < n and 0 <= c < n
		int res = dp[index];
		if (res >= 0)
			return res;
		if (p > c + r || q <= c - r) {
			res = 0;
		} else if (r == 0) {
//			res = c >= p && c < q ? a[c] : 0;
			res = a[c];
		} else {
			res = Math.max(calc(p, q, r - 1, c + 1, index - n3 + n2), calc(p, q, r - 1, c, index - n3));
			for (int i = p + 1; i < q; i++)
				res = Math.max(res, calc(p, i, r - 1, c, index - n3 + i - q) + calc(i, q, r - 1, c + 1, index - n3 + n2 + (i - p) * n));
		}
		if (res < w[r][c])
			res = 0;
		return dp[index] = res;
	}

	private int index(int p, int q, int r, int c) {
		return ((r * n + c) * n + p) * n + q - 1;
	}
}
