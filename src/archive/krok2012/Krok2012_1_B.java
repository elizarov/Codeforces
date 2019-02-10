package archive.krok2012;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_1_B {
	private static final int MAX = 2 << 20;
	private static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) {
		new Krok2012_1_B().go();
	}

	int n;
	int m;
	char[][] a;

	boolean col(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m && a[r][c] == '#';
	}

	int pack(int r, int c, int s) {
		if (r < 0 || c < 0 || r >= n || c > m || (c == m && (r != n - 1 || s != 0)))
			return -1;
		return r | (c << 10) | (s << 20);
	}

	int unpackR(int p) {
		return p & ((1 << 10) - 1);
	}

	int unpackC(int p) {
		return (p >> 10) & ((1 << 10) - 1);
	}

	int unpackS(int p) {
		return p >> 20;
	}

	int[] ind = new int[MAX];
	int[] heap = new int[MAX];
	int[] dist = new int[MAX];
	int size;

	void put(int p, int d) {
		if (p < 0)
			return;
		if (dist[p] <= d)
			return;
		dist[p] = d;
		int i = ind[p];
		if (i < 0) {
			heap[size] = p;
			i = ind[p] = size++;
		}
		int j;
		while (i > 0 && dist[heap[j = (i - 1) >> 1]] > d) {
			swap(i, j);
			i = j;
		}
	}

	void swap(int i, int j) {
		int t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
		ind[heap[i]] = i;
		ind[heap[j]] = j;
	}

	int take() {
		int result = heap[0];
		swap(0, --size);
		int i = 0;
		while (true) {
			int i0 = i;
			int j = (i0 << 1) + 1;
			if (j >= size)
				break;
			if (dist[heap[j]] < dist[heap[i]])
				i = j;
			int k = (i0 << 1) + 2;
			if (k < size && dist[heap[k]] < dist[heap[i]])
				i = k;
			if (i == i0)
				break;
			swap(i, i0);
		}
		return result;
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		n = in.nextInt();
		m = in.nextInt();
		a = new char[n][];
		for (int i = 0; i < n; i++)
			a[i] = in.next().toCharArray();
		int result = solve();
		out.println(result == INF ? -1 : result);
	}

	int solve() {
		Arrays.fill(ind, -1);
		Arrays.fill(dist, INF);
		put(pack(0, 0, 0), 0);
		while (size > 0) {
			int p = take();
			int r = unpackR(p);
			int c = unpackC(p);
			int s = unpackS(p);
			int d = dist[p];
			if (s == 0) {
				put(pack(r, c - 1, 0), d);
				put(pack(r, c + 1, 0), d);
				if (col(r, c - 1)) {
					put(pack(r, c - 1, 1), d + 1);
					put(pack(r + 1, c - 1, 1), d + 1);
				}
				if (col(r, c)) {
					put(pack(r, c, 1), d + 1);
					put(pack(r + 1, c, 1), d + 1);
				}
			} else {
				put(pack(r - 1, c, 1), d);
				put(pack(r + 1, c, 1), d);
				if (col(r - 1, c)) {
					put(pack(r - 1, c, 0), d + 1);
					put(pack(r - 1, c + 1, 0), d + 1);
				}
				if (col(r, c)) {
					put(pack(r, c, 0), d + 1);
					put(pack(r, c + 1, 0), d + 1);
				}
			}
		}
		int stop = pack(n - 1, m, 0);
		return dist[stop];
	}
}
