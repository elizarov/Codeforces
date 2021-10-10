package archive.r104;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_104_D {

	private static final String NA = "-1";

	public static void main(String[] args) {
		new Round_104_D().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int a1 = in.nextInt();
		int a2 = in.nextInt();
		int a3 = in.nextInt();
		int a4 = in.nextInt();
		if (a3 == a4) {
			String s1 = finish(rep("47", a3) + "4", a1, a2);
			String s2 = finish(rep("74", a3) + "7", a1, a2);
			if (s1.equals(NA))
				out.println(s2);
			else if (s2.equals(NA))
				out.println(s1);
			else if (s1.compareTo(s2) < 0)
				out.println(s1);
			else
				out.println(s2);
		} else if (a3 == a4 + 1) {
			out.println(finish(rep("47", a3), a1, a2));
		} else if (a3 == a4 - 1) {
			out.println(finish(rep("74", a4), a1, a2));
		} else {
			out.println(NA);
		}
	}

	private String finish(String s, int a1, int a2) {
		int b1 = 0;
		int b2 = 0;
		int n = s.length();
		for (int i = 0; i < n; i++)
			if (s.charAt(i) == '4')
				b1++;
			else
				b2++;
		if (b1 > a1 || b2 > a2)
			return NA;
		if (a1 > b1) {
			int i = s.indexOf('4');
			s = s.substring(0, i) + rep("4", a1 - b1) + s.substring(i);
		}
		if (a2 > b2) {
			int j = s.lastIndexOf('7');
			s = s.substring(0, j) + rep("7", a2 - b2) + s.substring(j);
		}
		return s;
	}

	private String rep(String s, int n) {
		StringBuilder sb = new StringBuilder(n * s.length());
		for (int i = 0; i < n; i++)
			sb.append(s);
		return sb.toString();
	}
}
