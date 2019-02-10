package archive.krok2012;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_D {
	public static void main(String[] args) {
		new Krok2012_D().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
        int a = in.nextInt();
        int n = in.nextInt();

        int b = a + n - 1;
        int[] m = new int[n];
        Arrays.fill(m, 1);
        for (int x = 2;; x++) {
            int sq = x * x;
            if (sq > b)
                break;
            int i0 = a % sq;
            if (i0 > 0)
                i0 = sq - i0;
            for (int i = i0; i < n; i += sq)
                m[i] = sq;
        }
        long result = 0;
        for (int i = 0; i < n; i++)
            result += (a + i) / m[i];
		out.println(result);
	}
}
