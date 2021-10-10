package archive.r254;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_254_C {
	public static void main(String[] args) {
		new Round_254_C().go();
	}

	int n;
	int m;

	static class Node {
		int l;
		int r;
		boolean fix;
		int x;
		int x0 = -1;
		int x0setLen;
		long sumX0;
		long sumInc;

		Node(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	Node[] nodes;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();

		nodes = new Node[Integer.highestOneBit(n * 2 - 1) * 2];
		build(0, 1, n);

		PrintStream out = System.out;

		// solve
		for (int op = 1; op <= m; op++) {
			int type = in.nextInt();
			if (type == 1) {
				int l = in.nextInt();
				int r = in.nextInt();
				int x = in.nextInt();
				color(0, l, r, x, -1);
			} else {
				int l = in.nextInt();
				int r = in.nextInt();
				out.println(query(0, l, r));
			}
		}

		// write result
	}

	private long query(int i, int l, int r) {
		Node node = nodes[i];
		if (node.x0setLen == 0)
			return 0;
		boolean match = node.l == l && node.r == r;
		if (match)
			return node.sumX0 + node.sumInc;
		long sum = 0;
		if (node.x0 >= 0) {
			if (node.x0 >= l)
				sum += sum2(l, Math.min(node.x0, r), node.x0);
			if (node.x0 <= r)
				sum += sum2(Math.max(node.x0, l), r, node.x0);
			return sum + node.sumInc;
		}
		int m = (node.l + node.r) / 2;
		if (l <= m)
			sum += query(2 * i + 1, l, Math.min(m, r));
		if (r >= m + 1)
			sum += query(2 * i + 2, Math.max(l, m + 1), r);
		return sum;
	}

	private long sum2(int l, int r, int x0) {
		int a = Math.abs(l - x0);
		int b = Math.abs(r - x0);
		if (a > b) {
			int t = a;
			a = b;
			b = t;
		}
		// a <= b
		return (long)b * (b + 1) / 2 - (long)a * (a - 1) / 2;
	}

	private long[] color(int i, int l, int r, int x, int x0) {
		Node node = nodes[i];
		if (node.x0 >= 0)
			x0 = node.x0;
		boolean match = node.l == l && node.r == r;
		int len = r - l + 1;
		if (match && node.x0setLen == 0 && x0 < 0) {
			node.x0 = x;
			node.x = x;
			node.fix = true;
			node.x0setLen = len;
			long inc = 0;
			if (x >= l)
				inc += sum2(l, Math.min(node.x0, r), node.x0);
			if (x <= r)
				inc += sum2(Math.max(node.x0, l), r, node.x0);
			node.sumX0 = inc;
			return new long[] { inc, 0 };
		}
		if (match && node.fix) {
			long inc = (long)Math.abs(node.x - x) * len;
			node.sumInc += inc;
			node.x = x;
			return new long[] { 0, inc };
		}
		int m = (node.l + node.r) / 2;
		long[] inc = new long[2];
		if (l <= m)
			add(inc, color(2 * i + 1, l, Math.min(m, r), x, x0));
		if (r >= m + 1)
			add(inc, color(2 * i + 2, Math.max(l, m + 1), r, x, x0));
		if (node.x0setLen < len) {
			node.x0setLen = nodes[2 * i + 1].x0setLen;
			if (m + 1 <= node.r)
				node.x0setLen += nodes[2 * i + 2].x0setLen;
		}
		if (match && node.x0setLen == len) {
			node.fix = true;
			node.x = x;
		} else
			node.fix = false;
		node.sumX0 += inc[0];
		node.sumInc += inc[1];
		return inc;
	}

	private static void add(long[] inc, long[] a) {
		inc[0] += a[0];
		inc[1] += a[1];
	}

	private void build(int i, int l, int r) {
		Node node = new Node(l, r);
		nodes[i] = node;
		if (l == r)
			return;
		int m = (l + r) / 2;
		build(2 * i + 1, l, m);
		if (m + 1 <= r)
			build(2 * i + 2, m + 1, r);
	}
}
