package archive.krok2012;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_A {
	public static void main(String[] args) {
		new Krok2012_A().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
        int n = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++)
            s[i] = in.next();
		int result = 0;
        String s0 = s[0];
        for (int j = 1; j <= s0.length(); j++) {
            String p = s0.substring(0, j);
            boolean ok = true;
            for (int k = 1; k < n; k++)
                if (!s[k].startsWith(p)) {
                    ok = false;
                    break;
                }
            if (ok)
                result = j;
            else
                break;
        }
		out.println(result);
	}
}
