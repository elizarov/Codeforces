package archive.r119;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_119_A {
	public static void main(String[] args) {
		new Round_119_A().go();
	}

	int n;
	int[] a;
	int[] b;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		b = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();
		for (int i = 0; i < n; i++)
			b[i] = in.nextInt();

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int solve() {
		int res = 0;
		int i = 0;
		int j = 0;
		int rem = n;
		boolean[] ok = new boolean[n + 1];
		while (i < rem && j < n) {
			if (ok[b[j]])
				j++;
			else if (a[i] == b[j]) {
				i++;
				j++;
			} else {
				do {
					res++;
					rem--;
					ok[a[rem]] = true;
				} while (a[rem] != b[j]);
			}
		}
		return res;
	}
}
