package archive.r103;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_103_A {
	public static void main(String[] args) {
		new Round_103_A().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		int a[] = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = in.nextInt();
		int min = a[n - 1];
		int minPos = n - 1;
		for (int i = n; --i >= 0;) {
			if (a[i] < min) {
				min = a[i];
				minPos = i;
			}
		}
		int max = a[0];
		int maxPos = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] > max) {
				max = a[i];
				maxPos = i;
			}
		}
		int result = maxPos;
		if (minPos < maxPos)
			minPos++;
		result += n - 1 - minPos;
		out.println(result);
	}
}
