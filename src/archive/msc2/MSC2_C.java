package archive.msc2;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class MSC2_C {

	public static final int M = 100000;

	public static void main(String[] args) {
		new MSC2_C().go();
	}

	int n;
	int[] a;
	int[] b;

	void go() {
		// read input
		read(new Scanner(System.in));

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	void read(Scanner in) {
		n = in.nextInt();
		a = new int[n];
		b = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
			b[i] = in.nextInt();
		}
	}

	int[] v = new int[M + 1];
	int[] h = new int[M];
	int[] hb = new int[M + 1];

	int[] mxi = new int[M];
	int mxn;

	int solve() {
		for (int i = 0; i < n; i++)
			v[a[i]]++;
		for (int i = 1; i <= M; i++) {
			h[i - 1] = i;
			hb[i] = i - 1;
		}
		for (int i = M; --i >= 0;)
			siftDown(i);
		if (v[0] > v[h[0]])
			return 0;
		PriorityQueue<P> pq = new PriorityQueue<>((o1, o2) -> o1.b - o2.b);
		for (int i = 0; i < n; i++)
			if (a[i] != 0)
				pq.add(new P(a[i], b[i]));
		PriorityQueue<P> bq = new PriorityQueue<>((o1, o2) -> o2.b - o1.b);
		int sum = 0;
		while (v[0] <= v[h[0]] && !pq.isEmpty()) {
			P p = pq.remove();
			v[p.a]--;
			siftUp(hb[p.a]);
			bq.add(p);
			v[0]++;
			sum += p.b;
		}
		@SuppressWarnings("unchecked") PriorityQueue<P>[] pqs = (PriorityQueue<P>[]) new PriorityQueue[M + 1];
		while (!pq.isEmpty()) {
			P p = pq.remove();
			if (pqs[p.a] == null)
				pqs[p.a] = new PriorityQueue<>((o1, o2) -> o1.b - o2.b);
			pqs[p.a].add(p);
		}
	improve:
		while (v[h[0]] > 0) {
			update();
			int sum0 = 0;
			int vg = v[0] - 2;
			Set<Integer> as = new HashSet<>();
			for (int i = 0; i < mxn; i++) {
				int a = mxi[i];
				as.add(a);
				P p = pqs[a].remove();
				v[p.a]--;
				siftUp(hb[p.a]);
				sum0 += p.b;
			}
			int sum1 = 0;
			int found = 0;
			while (found < mxn + 1) {
				if (bq.isEmpty())
					break improve;
				P p = bq.remove();
				if (as.contains(p.a) || v[p.a] + 1 > vg)
					continue;
				if (pqs[p.a] == null)
					pqs[p.a] = new PriorityQueue<>((o1, o2) -> o1.b - o2.b);
				pqs[p.a].add(p);
				v[p.a]++;
				siftDown(hb[p.a]);
				sum1 += p.b;
				found++;
			}
			if (sum1 <= sum0)
				break;
			// sum1 > sum0 (!!!)
			sum -= sum1 - sum0;
			v[0]--;
		}
		return sum;
	}

	private void update() {
		mxn = 0;
		scanMXI(0);
	}

	private void scanMXI(int i) {
		if (i >= M || v[h[i]] < v[h[0]])
			return;
		mxi[mxn++] = h[i];
		scanMXI(2 * i + 1);
		scanMXI(2 * i + 2);
	}

	private void siftUp(int i) {
		while (2 * i + 1 < M) {
			int j = 2 * i + 1;
			int vj = v[h[j]];
			if (j + 1 < M && v[h[j + 1]] > vj) {
				j++;
				vj = v[h[j]];
			}
			if (v[h[i]] >= vj)
				break;
			int t = h[i];
			h[i] = h[j];
			h[j] = t;
			hb[h[i]] = i;
			hb[h[j]] = j;
			i = j;
		}
	}

	private void siftDown(int i) {
		while (i > 0) {
			int j = (i - 1) / 2;
			if (v[h[j]] >= v[h[i]])
				break;
			int t = h[i];
			h[i] = h[j];
			h[j] = t;
			hb[h[i]] = i;
			hb[h[j]] = j;
			i = j;
		}
	}

	static class P {
		int a;
		int b;

		P(int a, int b) {
			this.a = a;
			this.b = b;
		}

	}
}
