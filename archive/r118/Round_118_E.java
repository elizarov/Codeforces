package archive.r118;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_118_E {
	public static void main(String[] args) {
		new Round_118_E().go();
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
