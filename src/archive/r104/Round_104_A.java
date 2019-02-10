package archive.r104;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_104_A {
	public static void main(String[] args) {
		new Round_104_A().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		String s = in.next();
		boolean ok = true;
		int s1 = 0;
		int s2 = 0;
		for (int i = 0; i < n; i++) {
			int d = s.charAt(i) - '0';
			if (d != 4 && d != 7)
				ok = false;
			if (i < n / 2)
				s1 += d;
			else
				s2 += d;
		}
		ok &= s1 == s2;
		out.println(ok ? "YES" : "NO");
	}
}
