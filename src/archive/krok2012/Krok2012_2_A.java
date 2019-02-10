package archive.krok2012;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_2_A {
	public static void main(String[] args) {
		new Krok2012_2_A().go();
	}

	int n;
	int m;
	int k;
	int[][] a;
	int[][] b;
	int[][] c;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		a = new int[n][m];
		b = new int[n][m];
		c = new int[n][m];
		for (int i = 0; i < n; i++) {
			in.next();
			for (int j = 0; j < m; j++) {
				a[i][j] = in.nextInt();
				b[i][j] = in.nextInt();
				c[i][j] = in.nextInt();
			}
		}
		out.println(solve());
	}

	Delta[] d;

	int solve() {
		d = new Delta[m];
		for (int t = 0; t < m; t++)
			d[t] = new Delta();
		int max = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i != j)
					max = Math.max(max, solve2(i, j));
		return max;
	}

	private int solve2(int i, int j) {
		for (int t = 0; t < m; t++) {
			d[t].p = b[j][t] - a[i][t];
			d[t].t = t;
		}
		Arrays.sort(d);
		int rem = k;
		int profit = 0;
		for (int u = 0; k > 0 && u < m; u++) {
			int p = d[u].p;
			if (p <= 0)
				break;
			int cnt = Math.min(rem, c[i][d[u].t]);
			profit += p * cnt;
			rem -= cnt;
		}
		return profit;
	}

	static class Delta implements Comparable<Delta> {
		int t;
		int p;

		public int compareTo(Delta o) {
			return o.p - p;
		}
	}
}
