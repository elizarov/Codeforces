package archive.krok2012;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_2_B {
	private static final int N = 26;
	private static final int MOD = 1000000007;

	public static void main(String[] args) {
		new Krok2012_2_B().go();
	}

	String a;
	String b;
	int k;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		a = in.next();
		b = in.next();
		k = in.nextInt();
		out.println(solve());
	}

	int solve() {
		int n = a.length();
		long[] cnt0 = new long[n];
		long[] cnt1 = new long[n];
		cnt0[0] = 1;
		for (int i = 0; i < k; i++) {
			long sum = 0;
			for (int j = 0; j < n; j++)
				sum += cnt0[j];
			for (int j = 0; j < n; j++)
				cnt1[j] = (sum - cnt0[j]) % MOD;
			long[] t = cnt0;
			cnt0 = cnt1;
			cnt1 = t;
		}
		long res = 0;
		for (int i = 0; i < n; i++) {
			String p = a.substring(0, i);
			String q = a.substring(i, n);
			if (b.startsWith(q) && b.endsWith(p))
				res += cnt0[i];
		}
		return (int)(res % MOD);
	}
}
