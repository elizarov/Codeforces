package archive.r102;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_102_A {
	public static void main(String[] args) {
		new Round_102_A().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int r1 = in.nextInt();
		int r2 = in.nextInt();
		int c1 = in.nextInt();
		int c2 = in.nextInt();
		int d1 = in.nextInt();
		int d2 = in.nextInt();
		for (int a11 = 1; a11 <= 9; a11++) {
			int a12 = r1 - a11;
			int a21 = c1 - a11;
			int a22 = c2 - a12;
			if (ok(a12) && ok(a21) && ok(a22) && a11 + a22 == d1 && a12 + a21 == d2 && a21 + a22 == r2 &&
					a12 != a11 &&
					a21 != a12 && a21 != a11 &&
					a22 != a21 && a22 != a12 && a22 != a11)
			{
				out.println(a11 + " " + a12);
				out.println(a21 + " " + a22);
				return;
			}
		}
		out.println(-1);
	}

	private boolean ok(int a) {
		return a >= 1 && a <= 9;
	}
}
