package archive.r103;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Round_103_D {
	public static void main(String[] args) {
		new Round_103_D().go();
	}

	private final List<List<Road>> adj = new ArrayList<List<Road>>();

	int[] dist;
	int[] q;
	int[] p;
	int qs;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		int m = in.nextInt();
		int s = in.nextInt() - 1;
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Road>());
		for (int i = 0; i < m; i++) {
			int v = in.nextInt() - 1;
			int u = in.nextInt() - 1;
			int w = in.nextInt();
			add(v, u, w);
			add(u, v, w);
		}
		int l = in.nextInt();
		dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s] = 0;
		q = new int[n];
		p = new int[n];
		qs = 0;
		enqueue(s);
		while (qs > 0) {
			int v = remove();
			int dv = dist[v];
			for (Road road : adj.get(v)) {
				int u = road.u;
				int du = dist[u];
				if (dv + road.w < du) {
					if (du == Integer.MAX_VALUE)
						enqueue(u);
					dist[u] = dv + road.w;
					siftDown(p[u]);
				}
			}
		}
		int result1 = 0;
		int result2 = 0;
		for (int v = 0; v < n; v++) {
			int dv = dist[v];
			if (dv == l)
				result1++;
			else if (dv < l) {
				for (Road road : adj.get(v)) {
					int u = road.u;
					int du = dist[u];
					if (dv + road.w > l) {
						int d = l - dv;
						int alt = road.w - d + du;
						if (alt == l)
							result2++;
						else if (alt > l)
							result1++;
					}
				}
			}
		}
		out.println(result1 + result2 / 2);
	}

	private int remove() {
		int v = q[0];
		qs--;
		if (qs > 0) {
			swap(0, qs);
			siftUp(0);
		}
		return v;
	}

	private void swap(int i, int j) {
		int vi = q[i];
		int vj = q[j];
		q[i] = vj;
		p[vj] = i;
		q[j] = vi;
		p[vi] = j;
	}

	private void siftUp(int i) {
		while (true) {
			int j = i * 2 + 1;
			int k = i * 2 + 2;
			if (k < qs && dist[p[k]] < dist[p[j]])
				j = k;
			if (j < qs && dist[p[j]] < dist[p[i]]) {
				swap(i, j);
				i = j;
			} else
				break;
		}
	}

	private void siftDown(int i) {
		while (i > 0) {
			int j = (i - 1) / 2;
			if (dist[p[i]] < dist[p[j]]) {
				swap(i, j);
				i = j;
			} else
				break;
		}
	}

	private void enqueue(int v) {
		q[qs] = v;
		p[v] = qs;
		qs++;
	}

	private void add(int v, int u, int w) {
		adj.get(v).add(new Road(u, w));
	}

	private static class Road {
		final int u;
		final int w;

		private Road(int u, int w) {
			this.u = u;
			this.w = w;
		}
	}
}
