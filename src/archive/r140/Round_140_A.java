package archive.r140;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_140_A {
	public static void main(String[] args) {
		new Round_140_A().go();
	}

	int n;
	int m;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		n = in.nextInt();
		m = in.nextInt();
		out.println((pow(n) + m - 1) % m);
	}

	long pow(int n) {
		if (n == 0)
			return 1;
		long res = pow(n / 2);
		res = res * res % m;
		if (n % 2 == 1)
			res = res * 3 % m;
		return res;
	}
}
