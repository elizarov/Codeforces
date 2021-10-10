package archive.r263;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_263_B {
	public static void main(String[] args) {
		new Round_263_B().go();
	}

	static final int MOD = 1000000007;

	int n;
	int[] p;
	int[] c;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		p = new int[n];
		for (int i = 1; i < n; i++)
			p[i] = in.nextInt();
		c = new int[n];
		for (int i = 0; i < n; i++)
			c[i] = in.nextInt();

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int[] nChildren;
	int[][] children;

	int solve() {
		nChildren = new int[n];
		for (int i = 1; i < n; i++)
			nChildren[p[i]]++;
		children = new int[n][];
		for (int i = 0; i < n; i++) {
			children[i] = new int[nChildren[i]];
			nChildren[i] = 0;
		}
		for (int i = 1; i < n; i++)
			children[p[i]][nChildren[p[i]]++] = i;
		return subtree(0)[1];
	}

	private int[] subtree(int i) {
		int[] r;
		if (c[i] == 0) {
			if (nChildren[i] == 0)
				r = new int[] { 1, 0 };
			else {
				r = subtree(children[i][0]);
				r = new int[] {
					add(r[1], r[0]),
					r[1]
				};
				for (int k = 1; k < nChildren[i]; k++) {
					int[] s = subtree(children[i][k]);
					r = new int[] {
						add(mul(r[0], s[1]), mul(r[0], s[0])),
						add(mul(r[1], s[1]), mul(r[0], s[1]), mul(r[1], s[0]))
					};
				}
			}
		} else {
			// c[i] == 1
			if (nChildren[i] == 0)
				r = new int[] { 0, 1 };
			else {
				r = subtree(children[i][0]);
				r = new int[] {
					0,
					add(r[1], r[0]) };
				for (int k = 1; k < nChildren[i]; k++) {
					int[] s = subtree(children[i][k]);
					r = new int[] {
							0,
							add(mul(r[1], s[1]), mul(r[1], s[0]))
					};
				}
			}
		}
		return r;
	}

	int mul(long a, long b) {
		return (int)(a * b % MOD);
	}

	int add(long a, long b) {
		return (int)((a + b) % MOD);
	}

	int add(long a, long b, long c) {
		return (int)((a + b + c) % MOD);
	}
}
