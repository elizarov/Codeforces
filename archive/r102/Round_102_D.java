package archive.r102;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_102_D {
	public static void main(String[] args) {
		new Round_102_D().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		int m = in.nextInt();
		int res = 0;
		if (n == 1 || m == 1)
			res = n * m;
		else {
			res = update(res, n, m, n / 2, m / 2);
			res = update(res, n, m, 2 * (n / 4), 2 * (m / 4));
		}
		out.println(res);
	}

	private int update(int res, int n, int m, int oddr, int oddc) {
		int b1 = oddr * oddc + (n - oddr) * (m - oddc);
		res = Math.max(res, b1);
		res = Math.max(res, n * m - b1);
		int b2 = oddr * (m - oddc) + (n - oddr) * oddc;
		res = Math.max(res, b2);
		res = Math.max(res, n * m - b2);
		return res;
	}
}
