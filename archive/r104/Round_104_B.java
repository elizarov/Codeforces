package archive.r104;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_104_B {
	public static void main(String[] args) {
		new Round_104_B().go();
	}

	int a;
	int b;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		a = in.nextInt();
		b = in.nextInt();
		out.println(solve(a, b));
	}

	int[] da;
	int[] db;

	private int solve(int a, int b) {
		if (a < b)
			return b;
		assert a >= b;
		da = digits(a);
		db = digits(b);
		da = Arrays.copyOf(da, da.length + 1);
		assert da.length > db.length;
		return find(0, 0, 1, 0);
	}

	private int find(int i, int j, int im, int ca) {
		if (da.length - i < db.length - j)
			return Integer.MAX_VALUE;
		if (i >= da.length && j >= db.length) {
			return ca > a ? ca : Integer.MAX_VALUE;
		}
		int res = Integer.MAX_VALUE;
		int nim = 10 * im;
		if (i < da.length && j < db.length)
			res = Math.min(res, find(i + 1, j + 1, nim, ca + im * db[j]));
		if (i < da.length)
			for (int k = 0; k < 10; k++)
				if (k != 4 && k != 7)
					res = Math.min(res, find(i + 1, j, nim, ca + im * k));
		return res;
	}

	private int[] digits(int a) {
		List<Integer> da = new ArrayList<Integer>();
		while (a != 0) {
			da.add(a % 10);
			a /= 10;
		}
		int n = da.size();
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = da.get(i);
		}
		return res;
	}
}
