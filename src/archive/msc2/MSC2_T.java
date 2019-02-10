package archive.msc2;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class MSC2_T {
	public static void main(String[] args) {
		new MSC2_T().go();
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
