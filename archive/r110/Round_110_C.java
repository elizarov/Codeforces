package archive.r110;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_110_C {
	public static void main(String[] args) {
		new Round_110_C().go();
	}

	private static final int MOD = 1000000007;

	int[][] dp = new int[101][2501];

	private void go() {
		for (int[] ints : dp) {
			Arrays.fill(ints, -1);
		}
		Arrays.fill(dp[0], 0);
		dp[0][0] = 1;

		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			String w = in.next();
			int sum = 0;
			for (int j = 0; j < w.length(); j++)
				sum += w.charAt(j) - 'a';
			out.println((compute(w.length(), sum) + MOD - 1) % MOD);
		}
	}

	private int compute(int len, int sum) {
		if (dp[len][sum] >= 0)
			return dp[len][sum];
		long cnt = 0;
		int max = Math.min(25, sum);
		for (int i = 0; i <= max; i++)
			cnt += compute(len - 1, sum - i);
		return dp[len][sum] = (int)(cnt % MOD);
	}
}
