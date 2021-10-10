package archive.r118;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_118_B {
	public static void main(String[] args) {
		new Round_118_B().go();
	}

	int s;
	int[] p;

	double[] best;
	double[] cur;
	BigDecimal bestVal;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		s = in.nextInt();
		p = new int[3];
		p[0] = in.nextInt();
		p[1] = in.nextInt();
		p[2] = in.nextInt();

		// solve
		solve();

		// write result
		PrintStream out = System.out;
		out.println(best[0] + " " + best[1] + " " + best[2]);
	}

	void solve() {
		best = new double[3];
		bestVal = BigDecimal.ZERO;

		int maxp = -1;
		int maxc = 0;
		int nzc = 3;
		for (int i = 0; i < 3; i++) {
			if (p[i] > maxp) {
				maxp = p[i];
				maxc = 1;
			} else if (p[i] == maxp)
				maxc++;
			if (p[i] == 0)
				nzc--;
		}

		cur = new double[3];
		eval();
		if (maxp > 0 && s > nzc - maxc) {
			fillDef();
			double eq = (double)(s - (nzc - maxc)) / maxc;
			for (int i = 0; i < 3; i++)
				if (p[i] == maxp)
					cur[i] = eq;
			eval();
		}

		if (s >= nzc)
			for (int i = 0; i < 3; i++) {
				fillDef();
				cur[i] = s - nzc + 1;
				eval();
			}

		for (int i = 0; i < 3; i++) {
			int a = p[i];
			int b = p[(i + 1) % 3];
			int c = p[(i + 2) % 3];
			double d = det(c + a, a, b, c + b);
			if (d != 0) {
				double x = det(a * s, a, b * s, c + b) / d;
				double y = det(c + a, a * s, b, b * s) / d;
				if (x >= 0 && y >= 0 && x + y <= s) {
					cur[i] = x;
					cur[(i + 1) % 3] = y;
					cur[(i + 2) % 3] = s - x - y;
					eval();
				}
			}
		}
	}

	private double det(double a00, double a01, double a10, double a11) {
		return a00 * a11 - a01 * a10;
	}

	private void fillDef() {
		for (int i = 0; i < 3; i++)
			cur[i] = p[i] == 0 ? 0 : 1;
	}

	private void eval() {
		BigDecimal curVal = BigDecimal.ONE;
		for (int i = 0; i < 3; i++)
			curVal = curVal.multiply(BigDecimal.valueOf(cur[i]).pow(p[i]));
		if (curVal.compareTo(bestVal) > 0) {
			bestVal = curVal;
			System.arraycopy(cur, 0, best, 0, 3);
		}
	}
}
