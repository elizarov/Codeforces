package archive.r114;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_114_B {
	public static void main(String[] args) {
		new Round_114_B().go();
	}

	static class T implements Comparable<T> {
		double p;
		int a;

		public int compareTo(T o) {
			return a - o.a;
		}
	}

	T[] t;
	double[][][] dp;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		int l = in.nextInt();
		int k = in.nextInt();
		t = new T[n];
		for (int i = 0; i < n; i++)
			t[i] = new T();
		for (int i = 0; i < n; i++)
			t[i].p = in.nextInt() / 100.0;
		for (int i = 0; i < n; i++)
			t[i].a = in.nextInt();
		Arrays.sort(t);
		dp = new double[n + 1][n + 1][n + 1];
		for (double[][] d0 : dp) {
			for (double[] d1 : d0) {
				Arrays.fill(d1, -1);
			}
		}
		out.println(compute(n, l, k));
	}

	private double compute(int n, int l, int k) {
		if (l > n || k < 0)
			return 0;
		if (n == 0)
			return 1;
		l = Math.max(l, 0);
		k = Math.min(k, n);
		double res = dp[n][l][k];
		if (res != -1)
			return res;
		int i = n - 1;
		T tt = t[i];
		res = tt.p * compute(i, l - 1, k + tt.a) + (1 - tt.p) * compute(i, l, k);
		return dp[n][l][k] = res;
	}
}
