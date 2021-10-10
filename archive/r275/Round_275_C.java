package archive.r275;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_275_C {
	public static final int ALPHABET = 26 * 2;

	public static void main(String[] args) {
		new Round_275_C().go();
	}

	int n;
	int m;
	int[][] s;
	Trie root = new Trie(null);
	Trie[] nodes;

	static class Trie {
		Trie parent;
		Trie[] next;
		int cnt;

		Trie(Trie parent) {
			this.parent = parent;
		}

		public Trie follow(int c) {
			if (next == null)
				next = new Trie[ALPHABET];
			if (next[c] == null)
				next[c] = new Trie(this);
			return next[c];
		}
	}

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		s = new int[n][];
		for (int i = 0; i < n; i++)
			s[i] = convert(in.next());
		m = s[0].length;

		// solve
		double result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	private int[] convert(String s) {
		int n = s.length();
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = convertChar(s.charAt(i));
		return a;
	}

	private int convertChar(char c) {
		if (c >= 'a' && c <= 'z')
			return c - 'a';
		else if (c >= 'A' && c <= 'Z')
			return c - 'A' + 26;
		else
			throw new AssertionError();
	}

	double[] dp;

	double solve() {
		dp = new double[1 << m];
		Arrays.fill(dp, Double.NaN);
		nodes = new Trie[n];
		Arrays.fill(nodes, root);
		return compute(0, 0, 0, 0);
	}

	double compute(int askedMask, int asked, long knownMask, int known) {
		if (!Double.isNaN(dp[askedMask]))
			return dp[askedMask];

		double sum = 0;
		for (int i = 0; i < m; i++) {
			if ((askedMask & (1 << i)) != 0)
				continue;
			for (int j = 0; j < n; j++) {
				if ((knownMask & (1L << j)) != 0)
					continue;
				nodes[j] = nodes[j].follow(s[j][i]);
				nodes[j].cnt++;
			}
			long nextKnownMask = knownMask;
			int nextKnown = known;
			for (int j = 0; j < n; j++) {
				if ((knownMask & (1L << j)) != 0)
					continue;
				if (nodes[j].cnt == 1) {
					nextKnownMask |= 1L << j;
					nextKnown++;
				}
			}
			if (nextKnown < n)
				sum += (n - nextKnown) * compute(askedMask | (1 << i), asked + 1, nextKnownMask, nextKnown) / (n - known);
			for (int j = 0; j < n; j++) {
				if ((knownMask & (1L << j)) != 0)
					continue;
				nodes[j].cnt = 0;
				nodes[j] = nodes[j].parent;
			}
		}
		double result = 1 + (sum / (m - asked));
		dp[askedMask] = result;
		return result;
	}
}
