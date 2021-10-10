package archive.r275;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Roman Elizarov
 */
public class Round_275_B {

	public static final int BITS = 30;

	public static void main(String[] args) {
		new Round_275_B().go();
	}

	int n;
	int m;
	Cons[] cs;

	static class Cons {
		int l;
		int r;
		int q;

		Cons(int l, int r, int q) {
			this.l = l;
			this.r = r;
			this.q = q;
		}
	}

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		cs = new Cons[m];
		for (int i = 0; i < m; i++)
			cs[i] = new Cons(
					in.nextInt() - 1,
					in.nextInt() - 1,
					in.nextInt());

		// solve
		int[] result = solve();

		// write result
		PrintWriter out = new PrintWriter(System.out);
		if (result == null)
			out.println("NO");
		else {
			out.println("YES");
			for (int i = 0; i < result.length; i++) {
				if (i > 0)
					out.print(' ');
				out.print(result[i]);
			}
			out.println();
		}
		out.flush();
	}

	List<Cons>[] cl;
	List<Cons>[] cr;
	int[] b = new int[BITS];
	int[] last0 = new int[BITS];

	@SuppressWarnings("unchecked")
	int[] solve() {
		int[] a = new int[n];
		cl = (List<Cons>[]) new List[n];
		cr = (List<Cons>[]) new List[n];
		for (Cons c : cs) {
			put(cl, c, c.l);
			put(cr, c, c.r);
		}
		Arrays.fill(last0, -1);
		for (int x = 0; x < n; x++) {
			if (cl[x] != null)
				for (Cons c : cl[x]) {
					update(c.q, 1);
				}
			for (int i = 0; i < BITS; i++)
				if (b[i] > 0) {
					a[x] |= 1 << i;
				} else
					last0[i] = x;
			if (cr[x] != null)
				for (Cons c : cr[x]) {
					for (int i = 0; i < BITS; i++)
						if ((c.q & (1 << i)) == 0 && last0[i] < c.l)
							return null;
					update(c.q, -1);
				}
		}
		return a;
	}


	private void update(int q, int d) {
		for (int i = 0; i < BITS; i++)
			if ((q & (1 << i)) != 0)
				b[i] += d;
	}

	private void put(List<Cons>[] cs, Cons c, int x) {
		if (cs[x] == null)
			cs[x] = new ArrayList<>();
		cs[x].add(c);
	}

	private static class Scanner {
		private InputStream in;

		public Scanner(InputStream in) {
			this.in = new BufferedInputStream(in, 65536);
		}

		public String next() {
			int c;
			StringBuilder sb = new StringBuilder();
			try {
				do {
					c = in.read();
					if (c < 0)
						throw new RuntimeException("EOF");
				} while (c <= ' ');
				do {
					sb.append((char)c);
					c = in.read();
				} while (c > ' ');
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return sb.toString();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
