package archive.r131;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Round_131_A {
	public static void main(String[] args) {
		new Round_131_A().go();
	}

	int n;
	int[] c;
	int[][] a;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		c = new int[n];
		for (int i = 0; i < n; i++)
			c[i] = in.nextInt();
		a = new int[n][];
		for (int i = 0; i < n; i++) {
			int k = in.nextInt();
			a[i] = new int[k];
			for (int j = 0; j < k; j++)
				a[i][j] = in.nextInt() - 1;
		}

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int[] bc;
	int[][] b;


	int solve() {
		bc = new int[n];
		b = new int[n][n];
		for (int v = 0; v < n; v++) {
			int k = a[v].length;
			for (int j = 0; j < k; j++) {
				int u = a[v][j];
				b[u][bc[u]++] = v;
			}
		}

		int res = Integer.MAX_VALUE;
		for (int s = 1; s <= 3; s++)
			res = Math.min(res, solveS(s));
		return res;

	}

	private int solveS(int s) {
		List<List<Integer>> q = new ArrayList<List<Integer>>();
		q.add(null);
		for (int t = 1; t <= 3; t++)
			q.add(new LinkedList<Integer>());
		int[] kr = new int[n];
		for (int v = 0; v < n; v++) {
			kr[v] = a[v].length;
			if (kr[v] == 0)
				q.get(c[v]).add(v);
		}
		int cs = 0;
		int res = n;
		while (cs < n) {
			if (q.get(s).isEmpty()) {
				res++;
				s++;
				if (s > 3)
					s = 1;
				continue;
			}
			cs++;
			int u = q.get(s).remove(0);
			for (int j = 0; j < bc[u]; j++) {
				int v = b[u][j];
				if (--kr[v] == 0)
					q.get(c[v]).add(v);
			}
		}
		return res;
	}
}
