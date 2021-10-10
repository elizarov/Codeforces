package archive.r110;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_110_B {
	private static final String LIE = "Lie";
	private static final String TRUTH = "Truth";
	private static final String ND = "Not defined";

	public static void main(String[] args) {
		new Round_110_B().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		int m = in.nextInt();
		int[] a = new int[n];
		int[] yes = new int[n];
		int[] no = new int[n];
		int sumyes = 0;
		int sumno = 0;
		for (int i = 0; i < n; i++) {
			String s = in.next();
			if (s.startsWith("+")) {
				a[i] = Integer.parseInt(s.substring(1));
				sumyes++;
				yes[a[i] - 1]++;
			} else if (s.startsWith("-")) {
				a[i] = -Integer.parseInt(s.substring(1));
				sumno++;
				no[-a[i] - 1]++;
			}
		}
		boolean[] can = new boolean[n];
		int ncan = 0;
		for (int i = 0; i < n; i++) {
			int t = yes[i] + (sumno - no[i]);
			can[i] = t == m;
			if (can[i])
				ncan++;
		}
		for (int i = 0; i < n; i++) {
			if (a[i] > 0) {
				if (!can[a[i] - 1])
					System.out.println(LIE);
				else if (ncan > 1)
					System.out.println(ND);
				else
					System.out.println(TRUTH);
			} else {
				if (can[-a[i] - 1]) {
					if (ncan > 1)
						System.out.println(ND);
					else
						System.out.println(LIE);
				} else
					System.out.println(TRUTH);
			}
		}
	}
}
