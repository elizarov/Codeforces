package archive.r119;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_119_D {
	public static void main(String[] args) {
		new Round_119_D().go();
	}

	int n;
	long g;
	long r;
	long[] len;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		g = in.nextLong();
		r = in.nextLong();
		len = new long[n + 1];
		for (int i = 0; i <= n; i++)
			len[i] = in.nextLong();

		// solve
		solve();

		// write result
		PrintStream out = System.out;
		int q = in.nextInt();
		for (int i = 0; i < q; i++)
			out.println(answer(in.nextLong()));
	}

	long[] rem;
	long[] a;
	long[] b;

	void solve() {
		rem = new long[n + 1];
		rem[n] = len[n];
		for (int i = n; --i >= 0;) {
			long p = len[i] % (g + r);
			rem[i] = len[i] + rem[i + 1];
			if (p >= g)
				rem[i] += (g + r - p);
		}
		a = new long[n + 1];
		b = new long[n + 1];
		a[0] = 0;
		b[1] = g + r;
		for (int i = 1; i <= n; i++) {
			long aa = a[i - 1] + len[i - 1];
			long ap = aa % (g + r);

			long bb = b[i - 1] + len[i - 1];

		}
	}

	long answer(long t) {
		return 0;
	}
}
