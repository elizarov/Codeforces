package archive.krok2012;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_2_D {
	public static void main(String[] args) {
		new Krok2012_2_D().go();
	}

	int n;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		n = in.nextInt();
		out.println(solve());
	}

	int solve() {
		return 0;
	}
}
