package archive.r127;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_127_A {
	public static void main(String[] args) {
		new Round_127_A().go();
	}

	int x;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		x = in.nextInt();

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int solve() {
		int n = Integer.MAX_VALUE;
		if (x % 4 == 0) {
			int r = 1;
			while ((r * r + 1) / 2 * 4 < x)
				r++;
			n = 2 * r + 2;
		}
		if (x % 2 == 0) {
			int r = 0;
			while ((r * r + 1) / 2 * 4 + r / 2 * 4 < x && r * r / 2 * 4 + (r + 1) / 2 * 4 < x)
				r++;
			n = Math.min(n, 2 * r + 1);
		} else {
			int r = 0;
			while ((r * r + 1) / 2 * 4 + r / 2 * 4 + 1 < x || (x - 1) % 4 != 0 && r / 2 == 0)
				r++;
			n = Math.min(n, 2 * r + 1);
		}
		return n;
	}
}
