package archive.r103;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_103_B {
	public static void main(String[] args) {
		new Round_103_B().go();
	}

	int xa;
	int ya;
	int xb;
	int yb;

	int n;
	int[] x;
	int[] y;
	int[] r;
	int[] r2;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		xa = in.nextInt();
		ya = in.nextInt();
		xb = in.nextInt();
		yb = in.nextInt();
		n = in.nextInt();
		x = new int[n];
		y = new int[n];
		r = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
			r[i] = in.nextInt();
		}
		out.println(solve());
	}

	int solve() {
		r2 = new int[n];
		for (int i = 0; i < n; i++)
			r2[i] = sqr(r[i]);
		int x1 = Math.min(xa, xb);
		int x2 = Math.max(xa, xb);
		int y1 = Math.min(ya, yb);
		int y2 = Math.max(ya, yb);
		int result = 0;
		for (int x = x1; x <= x2; x++) {
			if (cold(x, y1))
				result++;
			if (cold(x, y2))
				result++;
		}
		for (int y = y1 + 1; y < y2; y++) {
			if (cold(x1, y))
				result++;
			if (cold(x2, y))
				result++;
		}
		return result;
	}

	private boolean cold(int xx, int yy) {
		for (int i = 0; i < n; i++)
			if (sqr(xx - x[i]) + sqr(yy - y[i]) <= r2[i])
				return false;
		return true;
	}

	private int sqr(int x) {
		return x * x;
	}
}
