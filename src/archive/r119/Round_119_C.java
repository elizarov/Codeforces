package archive.r119;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_119_C {
	public static void main(String[] args) {
		new Round_119_C().go();
	}

	int n;
	int m;
	int k;
	boolean[] good;
	int[][] e;
	int[] ec;

	int s;
	int t;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		allocate();
		for (int i = 0; i < k; i++)
			good[in.nextInt() - 1] = true;
		for (int i = 0; i < m; i++) {
			int u = in.nextInt() - 1;
			int v = in.nextInt() - 1;
			add(u, v);
			add(v, u);
		}
		s = in.nextInt() - 1;
		t = in.nextInt() - 1;

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	void allocate() {
		good = new boolean[n];
		e = new int[n][2];
		ec = new int[n];
	}

	void add(int u, int v) {
		if (ec[u] >= e[u].length)
			e[u] = Arrays.copyOf(e[u], e[u].length * 2);
		e[u][ec[u]++] = v;
	}

	int solve() {
		good[t] = true;
		q0 = new Q(n);
		q1 = new Q(n);
		gf = new boolean[n];
		int lo = 1;
		int hi = n;
		while (lo < hi) {
			int q = (lo + hi) / 2;
			if (can(q))
				hi = q;
			else
				lo = q + 1;
		}
		return lo < n ? lo : -1;
	}

	static class Q {
		int[] queue;
		int[] dist;
		boolean[] f;
		int head;
		int tail;

		public Q(int n) {
			queue = new int[n];
			dist = new int[n];
			f = new boolean[n];
		}

		void clear() {
			for (int i = 0; i < tail; i++)
				f[queue[i]] = false;
			head = tail = 0;
		}

		boolean isEmpty() {
			return head == tail;
		}

		void add(int v, int d) {
			queue[tail++] = v;
			dist[v] = d;
			f[v] = true;
		}

		int remove() {
			return queue[head++];
		}
	}

	Q q0;
	Q q1;
	boolean[] gf;

	private boolean can(int q) {
		Arrays.fill(gf, false);
		q0.clear();
		q0.add(s, 0);
		gf[s] = true;
		while (!q0.isEmpty()) {
			q1.clear();
			while (!q0.isEmpty()) {
				int u = q0.remove();
				int d = q0.dist[u] + 1;
				for (int i = 0; i < ec[u]; i++) {
					int v = e[u][i];
					if (!q0.f[v]) {
						if (good[v] && !gf[v]) {
							gf[v] = true;
							q1.add(v, 0);
						}
						if (d < q)
							q0.add(v, d);
					}
				}
			}
			if (gf[t])
				return true;
			Q tmp = q0;
			q0 = q1;
			q1 = tmp;
		}
		return false;
	}
}
