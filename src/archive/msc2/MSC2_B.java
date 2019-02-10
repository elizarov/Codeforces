package archive.msc2;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class MSC2_B {
	public static void main(String[] args) {
		new MSC2_B().go();
	}

	int m;
	int n;
	int[] a;
	int[] b;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		m = in.nextInt();
		n = in.nextInt();
		a = ra(in, m);
		b = ra(in, n);

		// solve
		long result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	private int[] ra(Scanner in, int m) {
		int[] a = new int[m];
		for (int i = 0; i < m; i++)
			a[i] = in.nextInt();
		return a;
	}

	long sa;
	long sb;

	long solve() {
		sa = sum(a);
		sb = sum(b);
		long ga = gather(a, sb);
		long gb = gather(b, sa);
		return Math.min(ga, gb);
	}

	private long gather(int[] a, long sb) {
		int m = a.length;
		boolean[] g = new boolean[m];
		int cnt = 0;
		int max = 0;
		int maxi = -1;
		for (int i = 0; i < m; i++) {
			g[i] = a[i] >= sb;
			if (g[i])
				cnt++;
			if (a[i] > max) {
				max = a[i];
				maxi = i;
			}
		}
		if (cnt == 0) {
			g[maxi] = true;
		}
		long ga = 0;
		for (int i = 0; i < m; i++)
			if (g[i])
				ga += sb;
			else
				ga += a[i];
		return ga;
	}

	private long sum(int[] a) {
		long sum = 0;
		for (int i : a) {
			sum += i;
		}
		return sum;
	}
}
