package archive.r131;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_131_B {
	private static final int MOD = 1000000007;

	public static void main(String[] args) {
		new Round_131_B().go();
	}

	int n;
	int[] a = new int[10];

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for (int i = 0; i < 10; i++)
			a[i] = in.nextInt();

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int[][] c = new int[101][101];
	int[][] dp = new int[11][101];

	int solve() {
		c[0][0] = 1;
		for (int i = 1; i <= 100; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= 100; j++)
				c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
		}

		int sum = 0;
		for (int i = 0; i < 10; i++)
			sum += a[i];
		int res = 0;
		for (int len = Math.max(1, sum); len <= n; len++) {
			for (int fst = 1; fst < 10; fst++) {
				int slots = len - 1;
				int minSum = sum;
				if (a[fst] > 0)
					minSum--;
				for (int i = 0; i < 10; i++)
					Arrays.fill(dp[i], -1);
				Arrays.fill(dp[10], 0);
				dp[10][0] = 1;
				int combos = compute(fst, minSum, 0, slots);
				res = (res + combos) % MOD;
			}
		}
		return res;
	}

	private int compute(int fst, int minSum, int i, int slots) {
		if (minSum > slots)
			return 0;
		if (dp[i][slots] >= 0)
			return dp[i][slots];
		int min = a[i];
		if (min > 0 && i == fst)
			min--;
		int nextMinSum = minSum - min;
		int res = 0;
		for (int k = min; k <= slots; k++)
			res = (res + mul(compute(fst, nextMinSum, i + 1, slots - k), c[slots][k])) % MOD;
		return dp[i][slots] = res;
	}

	private int mul(long a, long b) {
		return (int)((a * b) % MOD);
	}
}
