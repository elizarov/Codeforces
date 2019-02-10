package archive.r104;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_104_C {
	public static void main(String[] args) {
		new Round_104_C().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		char[] a = in.next().toCharArray();
		char[] b = in.next().toCharArray();
		int n = a.length;
		assert b.length == n;
		int miss1 = 0;
		int miss2 = 0;
		for (int i = 0; i < n; i++)
			if (a[i] < b[i])
				miss1++;
			else if (a[i] > b[i])
				miss2++;
		out.println(miss1 + miss2 - Math.min(miss1, miss2));
	}
}
