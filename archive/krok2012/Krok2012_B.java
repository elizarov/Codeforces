package archive.krok2012;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_B {
	public static void main(String[] args) {
		new Krok2012_B().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
        int a = in.nextInt();
        int b = in.nextInt();
        int m = in.nextInt();
        int r = in.nextInt();
        int[] v = new int[m];
        int k = 1;
        while (v[r] == 0) {
            v[r] = k++;
            r = (a * r + b) % m;
        }
		int result = k - v[r];
		out.println(result);
	}
}
