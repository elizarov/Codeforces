package archive.r275;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Round_275_D {
	static final int MOD = 1000000007;

	public static void main(String[] args) {
		new Round_275_D().go();
	}

	int n;
	int[] p;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		p = new int[n];
		for (int i = 1; i < n; i++)
			p[i] = in.nextInt() - 1;

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	List<Integer>[] children;

	@SuppressWarnings("unchecked")
	int solve() {
		children = (List<Integer>[]) new List[n];
		for (int i = 0; i < n; i++)
			children[i] = new ArrayList<>();
		for (int i = 1; i < n; i++)
			children[p[i]].add(i);
		for (int i = 0; i < n; i++)
			Collections.sort(children[i]);
		return sum(compute(0));
	}

	private int[] compute(int i) {
		if (children[i].isEmpty())
			return new int[] { 0, 1 };
		int[][][] curr = new int[2][2][2]; // even/odd, first black?, last black?
		int[][][] next = new int[2][2][2]; // even/odd, first black?, last black?
		for (int j : children[i]) {
			int[] c = compute(j);
			// red
			for (int eo = 0; eo < 2; eo++)
				for (int fb = 0; fb < 2; fb++)
					for (int lb = 0; lb < 2; lb++)
						next[eo][fb][lb] = curr[eo][fb][lb];
			// black / white
			for (int o = 0; o < 2; o++)
				for (int eo = 0; eo < 2; eo++)
					for (int fb = 0; fb < 2; fb++)
						for (int lb = 0; lb < 2; lb++)
							next[eo][fb][lb] = (next[eo][fb][lb] + mul(sum(curr[eo ^ o][fb ^ o]), c[o])) % MOD;
			// --
			for (int o = 0; o < 2; o++)
				next[o][1][1] = (next[o][1][1] + c[o]) % MOD;
			// swap
			int[][][] t = curr;
			curr = next;
			next = t;
		}
		int[] res = new int[2];

		System.out.println(i + " -> " + Arrays.deepToString(curr));
		for (int eo = 0; eo < 2; eo++)
			for (int fb = 0; fb < 2; fb++)
				for (int lb = 0; lb < 2; lb++)
					if (curr[eo][fb][lb] != 0)
						System.out.printf("[%d][%d][%d] = %d%n", eo, fb, lb, curr[eo][fb][lb]);

		for (int eo = 0; eo < 2; eo++)
			for (int fb = 0; fb < 2; fb++)
				for (int lb = 0; lb < 2; lb++)
					if (fb == 1 || lb == 1)
						res[eo] = (res[eo] + curr[eo ^ 1][fb][lb]) % MOD;
		res[1] = (res[1] + 1) % MOD; // all red
		System.out.println(i + " -> " + Arrays.toString(res));
		return res;
	}

	private int mul(int x, int y) {
		return (int)(((long)x * y) % MOD);
	}

	private int sum(int[] x) {
		return (x[0] + x[1]) % MOD;
	}
}
