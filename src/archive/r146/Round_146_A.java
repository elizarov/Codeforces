package archive.r146;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_146_A {
	public static void main(String[] args) {
		new Round_146_A().go();
	}

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		// solve
		long result = solve(n);

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	static long solve(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n % 2 == 0) {
            if (n % 3 == 0)
                return (long)(n - 1) * (n - 2) * (n - 3);
            else
                return (long)n * (n - 1) * (n - 3);
        } else
		    return (long)n * (n - 1) * (n - 2);
	}
}
