package archive.r263;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_263_C {
	public static void main(String[] args) {
		new Round_263_C().go();
	}

	int n;
	int q;

	Node root;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		q = in.nextInt();

		buildRoot();
		int shift = 0;

		// solve
		PrintStream out = System.out;
		for (int i = 0; i < q; i++) {
			int type = in.nextInt();
			switch (type) {
				case 1:
					int p = in.nextInt();
					fold(root, shift, p + shift, p + shift);
					shift += p;
					break;
				case 2:
					int l = in.nextInt();
					int r = in.nextInt();
					System.out.println(query(root, l + shift, r + shift));
					break;
			}
		}

	}

	private void buildRoot() {
		Node[] n0 = new Node[Integer.highestOneBit(n) * 2];
		for (int i = 0; i < n0.length; i++)
			n0[i] = new Node(i, i + 1, i < n ? 1 : 0);
		while (n0.length > 1) {
			int m = n0.length / 2;
			Node[] n1 = new Node[m];
			for (int i = 0; i < m; i++) {
				Node c1 = n0[2 * i];
				Node c2 = n0[2 * i + 1];
				n1[i] = new Node(c1.l, c2.r, c1. sum + c2.sum);
				n1[i].c1 = c1;
				n1[i].c2 = c2;
			}
			n0 = n1;
		}
		root = n0[0];
	}

	private void fold(Node node, int l, int r, int p) {
		assert l >= node.l && r <= node.r && l < r && r <= p;
		if (l == node.l && r == node.r) {
			addFold(root, 2 * p - r, 2 * p - l, node, l, r);
			return;
		}
		if (l < node.c1.r)
			fold(node.c1, l, Math.min(r, node.c1.r), p);
		if (r > node.c2.l)
			fold(node.c2, Math.max(l, node.c2.l), r, p);
	}

	private void addFold(Node node, int l, int r, Node add, int lAdd, int rAdd) {
		if (l == node.l && r == node.r) {
			selectToAppend(node, add, lAdd, rAdd);
			return;
		}
		if (l < node.c1.r) {
			int r1 = Math.min(r, node.c1.r);
			addFold(node.c1, l, r1, add, lAdd + (r - r1), rAdd);
		}
		if (r > node.c2.l) {
			int l1 = Math.max(l, node.c2.l);
			addFold(node.c2, l1, r, add, lAdd, rAdd - (l1 - l));
		}
	}

	private void selectToAppend(Node node, Node add, int lAdd, int rAdd) {
		compress(add);
		if (lAdd == add.l && rAdd == add.r) {
			node.append(add);
			return;
		}
		if (lAdd < add.c1.r) {
			int rAdd1 = Math.min(rAdd, add.c1.r);
			selectToAppend(node, add.c1, lAdd, rAdd1);
		}
		if (rAdd > add.c2.l) {
			int lAdd1 = Math.max(lAdd, add.c2.l);
			selectToAppend(node, add.c2, lAdd1, rAdd);
		}
	}

	private int query(Node node, int l, int r) {
		compress(node);
		if (l == node.l && r == node.r)
			return node.sum;
		int result = 0;
		if (l < node.c1.r)
			result += query(node.c1, l, Math.min(r, node.c1.r));
		if (r > node.c2.l)
			result += query(node.c2, Math.max(l, node.c2.l), r);
		return result;
	}

	private void compress(Node node) {
		if (node.addFold != null) {
			for (Node add : node.addFold) {
				node.sum += add.sum;
				if (!node.leaf()) {
					node.c1.append(add.c2);
					node.c2.append(add.c1);
				}
			}
			node.addFold = null;
		}
	}

	static class Node {
		final int l;
		final int r;
		int sum;
		List<Node> addFold;
		Node c1;
		Node c2;

		Node(int l, int r, int sum) {
			this.l = l;
			this.r = r;
			this.sum = sum;
		}

		boolean leaf() {
			return l == r - 1;
		}

		public void append(Node add) {
			assert (add.r - add.l) == (r - l);
			if (addFold == null)
				addFold = new ArrayList<>(2);
			addFold.add(add);
		}
	}
}
