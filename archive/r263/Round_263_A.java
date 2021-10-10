package archive.r263;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_263_A {
	public static void main(String[] args) {
		new Round_263_A().go();
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
		long result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	long solve() {
		if (n == 1)
			return a[0];
		Arrays.sort(a);
		long result = 0;
		for (int i = 0; i < n; i++)
			result += (long)Math.min(i + 2, n) * a[i];
		return result;
	}
}
