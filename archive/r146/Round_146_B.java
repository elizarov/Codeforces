package archive.r146;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_146_B {
	public static void main(String[] args) {
		new Round_146_B().go();
	}

	int n;
    double[] p;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
        p = new double[n];
        for (int i = 0; i < n; i++)
            p[i] = in.nextDouble();

		// solve
		double result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	double solve() {
        double[] s = new double[n + 1];
        double[] e = new double[n + 1];
        double[] z = new double[n + 1];
        double[] x = new double[n + 1];
        for (int k = 1; k <= n; k++) {
            s[k] = p[k - 1] * (1 + s[k - 1]);
            e[k] = p[k - 1] * (1 + e[k - 1] + s[k - 1]);
            z[k] = p[k - 1] * (1 + z[k - 1] + 2 * e[k - 1] + s[k - 1]);
            x[k] = x[k - 1] + (1 - p[k - 1]) * z[k - 1];
            System.out.printf("%.4f %.4f %.4f %.4f%n", s[k], e[k], z[k], x[k]);
        }
        return x[n] + z[n];
	}
}
