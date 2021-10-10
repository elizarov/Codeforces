package archive.r114;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_114_A {
	public static void main(String[] args) {
		new Round_114_A().go();
	}

	double[] t;
	double[] v;
	double[] r;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		double a = in.nextInt();
		double d = in.nextInt();
		t = new double[n];
		v = new double[n];
		for (int i = 0; i < n; i++) {
			t[i] = in.nextInt();
			v[i] = in.nextInt();
		}
		double bumpTime = Math.sqrt(2 * d / a);
		r = new double[n];
		for (int i = 0; i < n; i++) {
			double t0 = v[i] / a;
			double d0 = t0 * v[i] / 2;
			double rr = t[i];
			if (d > d0) {
				rr += t0 + (d - d0) / v[i];
			} else {
				rr += bumpTime;
			}
			if (i == 0)
				r[i] = rr;
			else
				r[i] = Math.max(rr, r[i - 1]);
		}
		for (int i = 0; i < n; i++)
			out.println(r[i]);
	}
}
