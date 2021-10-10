package archive.r103;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_103_C {
	private static final int Q = '?';
	private static final char C0 = 'a';
	private static final int C1 = 'z';
	private static final int NC = C1 - C0 + 1;

	public static void main(String[] args) {
		new Round_103_C().go();
	}

	char[] s;
	char[] p;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		s = in.next().toCharArray();
		p = in.next().toCharArray();
		out.println(solve());
	}

	int[] cp;
	int[] sp;

	int solve() {
		int n = p.length;
		if (s.length < n)
			return 0;
		cp = new int[NC];
		for (int i = 0; i < n; i++) {
			char c = p[i];
			cp[c - C0]++;
		}
		sp = new int[NC];
		for (int i = 0; i < n; i++) {
			char c = s[i];
			if (c != Q)
				sp[c - C0]++;

		}
		int result = 0;
		if (ok())
			result++;
		for (int i = n; i < s.length; i++) {
			char c = s[i];
			if (c != Q)
				sp[c - C0]++;
			c = s[i - n];
			if (c != Q)
				sp[c - C0]--;
			if (ok())
				result++;
		}
		return result;
	}

	private boolean ok() {
		for (int i = 0; i < NC; i++)
			if (sp[i] > cp[i])
				return false;
		return true;
	}
}
