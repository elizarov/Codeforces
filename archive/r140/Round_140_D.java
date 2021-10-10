package archive.r140;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_140_D {
	public static void main(String[] args) {
		new Round_140_D().go();
	}

	int n;
	int m;
	int[][] d;
	boolean[] a;
	boolean[] b;
	int[] sa;
	int[] sb;
	int na;
	int nb;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		n = in.nextInt();
		m = in.nextInt();
		alloc();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				d[i][j] = in.nextInt();
		rebuild();
		while (na > 0 || nb > 0)
			flip();
		print(a);
		print(b);
	}

	private void alloc() {
		d = new int[n][m];
		a = new boolean[n];
		b = new boolean[m];
		sa = new int[n];
		sb = new int[m];
	}

	private void rebuild() {
		Arrays.fill(sa, 0);
		Arrays.fill(sb, 0);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				int x = d[i][j];
				if (a[i] ^ b[j])
					x = -x;
				sa[i] += x;
				sb[j] += x;
			}
		na = 0;
		for (int i = 0; i < n; i++)
			if (sa[i] < 0)
				na++;
		nb = 0;
		for (int j = 0; j < m; j++)
			if (sb[j] < 0)
				nb++;
	}

	private void print(boolean[] a) {
		int cnt = 0;
		for (boolean x : a) {
			if (x)
				cnt++;
		}
		System.out.print(cnt);
		for (int i = 0; i < a.length; i++) {
			boolean x = a[i];
			if (x)
				System.out.print(" " + (i + 1));
		}
		System.out.println();
	}

	void flip() {
		if (na > 0) {
			flip(a, sa);
			rebuild();
		}
		if (nb > 0) {
			flip(b, sb);
			rebuild();
		}
	}

	private void flip(boolean[] a, int[] sa) {
		for (int i = 0; i < a.length; i++) {
			if (sa[i] < 0) {
				a[i] = !a[i];
				sa[i] = -sa[i];
			}
		}
	}
}
