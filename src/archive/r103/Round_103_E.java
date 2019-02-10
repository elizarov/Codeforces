package archive.r103;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_103_E {
	public static void main(String[] args) {
		new Round_103_E().go();
	}

	int[] a;
	int[] b;
	int[] u;
	boolean[] v;

	static class Man {
		int r;
		int c;
		int index;
		int d;


		Man(int r, int c, int index, int n) {
			this.r = r;
			this.c = c;
			this.index = index;
			d = r + c - n - 1;
		}
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		int m = in.nextInt();
		Man[] men = new Man[m];
		for (int i = 0; i < m; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			men[i] = new Man(r, c, i + 1, n);
		}

		Arrays.sort(men, new Comparator<Man>() {
			public int compare(Man o1, Man o2) {
				int diff = o1.d - o2.d;
				if (diff != 0)
					return diff;
				return o1.r - o2.r;
			}
		});

		a = new int[m];
		b = new int[m];
		for (int i = 0; i < m; i++) {
			a[i] = men[i].c - men[i].d - 1;
			b[i] = men[i].c - 1;
		}
		u = new int[n];
		Arrays.fill(u, -1);
		v = new boolean[m];
		int result = 0;
		for (int i = 0; i < m; i++) {
			Arrays.fill(v, 0, i + 1, false);
			if (assign(i))
				result++;
		}
		out.println(result);
		for (int i = 0; i < n; i++)
			if (u[i] >= 0)
				out.print(men[u[i]].index + " ");
		out.println();
	}

	private boolean assign(int i) {
		if (v[i])
			return false;
		v[i] = true;
		for (int j = a[i]; j <= b[i]; j++) {
			int k = u[j];
			if (k < 0 || assign(k)) {
				u[j] = i;
				return true;
			}
		}
		return false;
	}
}
