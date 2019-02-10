package archive;

import java.io.*;

/**
 * @author Roman Elizarov
 */
public class Round_XXX_T {
	public static void main(String[] args) {
		new Round_XXX_T().go();
	}

	void go() {
		// read input
		Input in = new Input(System.in);
		n = in.nextInt();

		// solve
		int result = solve();

		// write result
		PrintWriter out = new PrintWriter(System.out);
		out.println(result);
		out.flush();
	}

	int n;

	int solve() {
		return 0;
	}

	private static class Input {
		private InputStream in;

		public Input(InputStream in) {
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
					sb.append((char) c);
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
