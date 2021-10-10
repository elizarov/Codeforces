package archive.r118;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_118_A {
	private static final long MOD = 1000000007;

	public static void main(String[] args) {
		new Round_118_A().go();
	}

	long n;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextLong();

		// solve
		long result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	long solve() {
		long p = n;
		long ca = 1;
		long cb = 0;
		long ma = 3;
		long mb = 1;
		// inv: C * M^p
		while (p > 0) {
			if (p % 2 != 0) {
				long da = (ca * ma + cb * mb) % MOD;
				long db = (ca * mb + cb * ma) % MOD;
				ca = da;
				cb = db;
			}
			if (p > 1) {
				long da = (ma * ma + mb * mb) % MOD;
				long db = (ma * mb + mb * ma) % MOD;
				ma = da;
				mb = db;
			}
			p /= 2;
		}
		return ca;
	}
}
