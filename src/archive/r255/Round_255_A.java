package archive.r255;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_255_A {
	public static void main(String[] args) {
		new Round_255_A().go();
	}

	int n;
	int[] a;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int solve() {
		if (n <= 2)
			return n;
		int[] up = new int[n];
		up[0] = 1;
		for (int i = 1; i < n; i++)
			up[i] = a[i] > a[i - 1] ? up[i - 1] + 1 : 1;
		int[] dn = new int[n];
		dn[n - 1] = 1;
		int ans = 2;
		for (int i = n - 1; --i >= 0;) {
			dn[i] = a[i] < a[i + 1] ? dn[i + 1] + 1 : 1;
			ans = Math.max(ans, Math.min(n, dn[i] + 1));
		}
		for (int i = 1; i < n - 1; i++)
			if (a[i - 1] + 1 < a[i + 1])
				ans = Math.max(ans, up[i - 1] + dn[i + 1] + 1);
		return ans;
	}
}
