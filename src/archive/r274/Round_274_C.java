package archive.r274;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_274_C {
	private static final int MOD = 1000000007;

	public static void main(String[] args) {
		new Round_274_C().go();
	}

	int n;
	int a;
	int b;
	int k;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = in.nextInt();
		b = in.nextInt();
		k = in.nextInt();

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int solve() {
		if (a > b) {
			b = n - b + 1;
			a = n - a + 1;
		}
		a--;
		b--;
		int[] cur = new int[b];
		int[] next = new int[b];
		Arrays.fill(cur, 0, b, 1);
		for (int step = 0; step < k; step++) {
			int sum = cur[b - 1];
			next[b - 1] = 0;
			for (int i = 2; i <= b; i++) {
				int j = b - 2 * i + 2;
				if (j >= 0) {
					sum = (sum + cur[j]) % MOD;
					if (j - 1 >= 0)
						sum = (sum + cur[j - 1]) % MOD;
				}
				next[b - i] = (sum + MOD - cur[b - i]) % MOD;
			}
			int[] t = cur;
			cur = next;
			next = t;
		}
		return cur[a];
	}
}
