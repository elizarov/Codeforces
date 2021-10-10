package archive.r140;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_140_B {
	public static void main(String[] args) {
		new Round_140_B().go();
	}

	int n;
	int[] a;
	long[] sum;
	long k1;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		n = in.nextInt();
		alloc();
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();
		prepare();
		int q = in.nextInt();
		for (int i = 0; i < q; i++) {
			int k = in.nextInt();
			if (i > 0)
				out.print(' ');
			out.print(solve(k));
		}
		out.println();
	}

	void alloc() {
		a = new int[n];
	}

	void prepare() {
		Arrays.sort(a);
		sum = new long[4 * Integer.highestOneBit(n)];
		for (int i = 0; i < n; i++)
			k1 += (n - i - 1) * (long)a[i];
		buildSum(0, 0, n - 1);
	}

	private long buildSum(int i, int left, int right) {
		if (left == right)
			return sum[i] = a[left];
		int mid = (left + right) / 2;
		return sum[i] = buildSum(2 * i + 1, left, mid) + buildSum(2 * i + 2, mid + 1, right);
	}

	private long sum(int i, int left, int right, int from, int to) {
		assert from >= left && to <= right : left + "," + right + " vs " + from + "," + to;
		if (from > to)
			return 0;
		if (left == from && right == to)
			return sum[i];
		int mid = (left + right) / 2;
		return sum(2 * i + 1, left, mid, from, Math.min(to, mid)) +
			sum(2 * i + 2, mid + 1, right, Math.max(from, mid + 1), to);
	}

	long solve(int k) {
		if (k == 1)
			return k1;
		long res = 0;
		int last = n - 2;
		int mul = 1;
		long len = k;
		while (last >= 0) {
			res += mul * sum(0, 0, n - 1, (int)Math.max(0, last - len + 1), last);
			if (len > last)
				break;
			last -= len;
			mul++;
			len *= k;
		}
		return res;
	}
}
