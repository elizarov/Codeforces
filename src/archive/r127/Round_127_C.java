package archive.r127;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_127_C {
	public static void main(String[] args) {
		new Round_127_C().go();
	}

	int n;
	int[] a;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		allocate();
		for (int i = 1; i < n; i++)
			a[i] = in.nextInt();

		// solve
		long result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	void allocate() {
		a = new int[n + 1];
	}

	long solve() {
		long result = 0;
		long[] ls = new long[n + 2];
		for (int i = 1; i <= n; i++) {
			if (a[i - 1] > 1)
				ls[i] = ls[i - 1] + (a[i - 1] & ~1);
		}
		long[] rs = new long[n + 2];
		for (int i = n; i >= 1; i--) {
			if (a[i] > 1)
				rs[i] = rs[i + 1] + (a[i] & ~1);
		}
		long[] rm = new long[n + 2];
		for (int i = n; i >= 1; i--) {
			rm[i] = Math.max(((a[i] - 1) & ~1) + 1 + rm[i + 1], rs[i]);
		}
		for (int i = 1; i <= n; i++) {
			result = Math.max(result, ls[i] + rm[i]);
		}
		return result;
	}
}
