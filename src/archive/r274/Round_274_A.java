package archive.r274;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Round_274_A {
	public static void main(String[] args) {
		new Round_274_A().go();
	}

	int n;
	Exam[] es;

	static class Exam implements Comparable<Exam> {
		int a;
		int b;

		Exam(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Exam o) {
			int d = a - o.a;
			return d != 0 ? d : b - o.b;
		}
	}

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		es = new Exam[n];
		for (int i = 0; i < n; i++) {
			es[i] = new Exam(in.nextInt(), in.nextInt());
		}
		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int solve() {
		Arrays.sort(es);
		int d = 0;
		for (Exam e : es) {
			d = e.b >= d ? e.b : e.a;
		}
		return d;
	}
}
