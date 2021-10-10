package archive.r274;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_274_B {
	public static void main(String[] args) {
		new Round_274_B().go();
	}

	int n;
	int l;
	int x;
	int y;
	int[] a;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		l = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();
		assert a[0] == 0;
		assert a[n - 1] == l;

		// solve
		int[] result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result.length);
		for (int i = 0; i < result.length; i++) {
			if (i > 0)
				out.print(' ');
			out.print(result[i]);
		}
	}

	int[] solve() {
		boolean hasX = findFirst(x) >= 0;
		boolean hasY = findFirst(y) >= 0;
		if (hasX && hasY)
			return new int[] {};
		if (hasX)
			return new int[] { y };
		if (hasY)
			return new int[] { x };
		int i = findFirst(x + y);
		if (i >= 0)
			return new int[] { a[i] + x };
		i = findFirst(y - x);
		if (i >= 0 && a[i] + y <= l)
			return new int[]{a[i] + y};
		i = findLast(y - x);
		if (i >= 0 && a[i] - x >= 0)
			return new int[]{a[i] - x};
		return new int[] { x, y };
	}

	int findFirst(int x) {
		for (int k = 0; k < n; k++) {
			if (findAt(x, k))
				return k;
		}
		return -1;
	}

	int findLast(int x) {
		for (int k = n; --k >= 0;) {
			if (findAt(x, k))
				return k;
		}
		return -1;
	}

	private boolean findAt(int x, int k) {
		int g = a[k] + x;
		int i = k;
		int j = n - 1;
		while (i <= j) {
			int m = (i + j) / 2;
			if (g > a[m])
				i = m + 1;
			else if (g < a[m])
				j = m - 1;
			else
				return true;
		}
		return false;
	}
}
