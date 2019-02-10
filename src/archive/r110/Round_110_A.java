package archive.r110;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_110_A {
	public static void main(String[] args) {
		new Round_110_A().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		String s = in.next();
		String u = in.next();
		StringBuilder sb = new StringBuilder();
		int k = u.length();
		for (int i = 0; i < k; i++)
			sb.append(' ');
		sb.append(s);
		for (int i = 0; i < k; i++)
			sb.append(' ');
		char[] sc = sb.toString().toCharArray();
		char[] uc = u.toCharArray();
		int result = Integer.MAX_VALUE;
		int n = sc.length - k;
		for (int i = 0; i <= n; i++) {
			int cnt = 0;
			for (int j = 0; j < k; j++)
				if (sc[i + j] != uc[j])
					cnt++;
			result = Math.min(cnt, result);
		}
		out.println(result);
	}
}
