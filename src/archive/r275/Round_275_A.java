package archive.r275;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_275_A {
	public static void main(String[] args) {
		new Round_275_A().go();
	}

	int n;
	int k;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();

		// solve
		int[] result = solve();

		// write result
		PrintStream out = System.out;
		for (int i = 0; i < result.length; i++) {
			if (i > 0)
				out.print(' ');
			out.print(result[i]);
		}
		out.println();
	}

	int[] solve() {
		int[] a = new int[n];
		a[0] = 1;
		int lo = 2;
		int hi = n;
		int last = 1;
		int dir = 1;
		for (int i = 1; i < k; i++) {
			if (i % 2 == 1) {
				last = hi--;
				dir = -1;
			} else {
				last = lo++;
				dir = 1;
			}
			a[i] = last;
		}
		for (int i = k; i < n; i++) {
			last += dir;
			a[i] = last;
		}
		return a;
	}
}
