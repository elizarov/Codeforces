package archive.r146;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_146_T {
	public static void main(String[] args) {
		new Round_146_T().go();
	}

	int n;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int solve() {
		return 0;
	}
}
