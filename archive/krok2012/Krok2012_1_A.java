package archive.krok2012;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_1_A {
	public static void main(String[] args) {
		new Krok2012_1_A().go();
	}

	char[] a;
	char[] b;

	int ra;
	int rb;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		a = in.next().toCharArray();
		b = in.next().toCharArray();
		int mk = a.length * b.length;
		imitate(mk, n / mk);
		imitate(n % mk, 1);
		out.println(ra + " " + rb);
	}

	private void imitate(int mk, int rep) {
		int ai = 0;
		int bi = 0;
		for (int i = 0; i < mk; i++) {
			char ac = a[ai++];
			if (ai >= a.length)
				ai = 0;
			char bc = b[bi++];
			if (bi >= b.length)
				bi = 0;
			if (ac != bc) {
				boolean win;
				switch (ac) {
				case 'R':
					win = bc == 'S';
					break;
				case 'P':
					win = bc == 'R';
					break;
				case 'S':
					win = bc == 'P';
					break;
				default:
					throw new IllegalArgumentException();
				}
				if (win)
					rb += rep;
				else
					ra += rep;
			}
		}
	}
}
