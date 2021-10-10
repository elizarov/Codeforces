package archive.r131;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_131_C {
	private static final long INF = Long.MAX_VALUE / 2;

	public static void main(String[] args) {
		new Round_131_C().go();
	}

	int n;
	long[][] a;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new long[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				a[i][j] = in.nextInt();

		// solve
		long result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	long[][] prv;
	long[][] cur;
	long[][] curJ;

	long solve() {
		prv = new long[n][n];
		cur = new long[n][n];
		curJ = new long[n][n];
		for (int row = 0; row < n; row++) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j <= i; j++) {
					long c;
					if (row > 0)
						c = prv[i][j] + a[row][i] + (i > j ? a[row][j] : 0);
					else
						c = (i == 0 && j == 0) ? a[0][0] : -INF;
					if (j > 0) {
						if (i > j)
							c = Math.max(c, curJ[i][j - 1] + a[row][j]);
						else // i == j
							c = Math.max(c, curJ[i - 1][j - 1] + a[row][j]);
					}
					curJ[i][j] = c;
					if (i > j)
						c = Math.max(c, cur[i - 1][j] + a[row][i]);
					else // i == j
						if (i > 0)
							c = Math.max(c, cur[i - 1][j - 1] + a[row][i]);
					cur[i][j] = c;
				}
			long[][] t = cur;
			cur = prv;
			prv = t;
		}
		return prv[n - 1][n - 1];
	}
}
