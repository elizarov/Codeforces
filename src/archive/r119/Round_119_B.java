package archive.r119;

import java.io.*;

/**
 * @author Roman Elizarov
 */
public class Round_119_B {
	public static void main(String[] args) throws IOException {
		new Round_119_B().go();
	}

	int n;
	int m;
	int r;

	int n2;
	int[][] t;

	void go() throws IOException {
		// read input
		Input in = new Input(System.in);
		n = in.nextInt();
		m = in.nextInt();
		r = in.nextInt();
		allocate();
		for (int p = 0; p < m; p++)
			for (int ij = 0; ij < n2; ij++)
				t[p][ij] = in.nextInt();

		solve();

		for (int i = 0; i < r; i++) {
			int s = in.nextInt();
			int t = in.nextInt();
			int k = in.nextInt();

			// solve
			int result = d[k][(s - 1) * n + t - 1];

			// write result
			PrintStream out = System.out;
			out.println(result);
		}
	}

	void allocate() {
		n2 = n * n;
		t = new int[m][n2];
	}

	int[][] d;

	void solve() {
		expand();
		d = new int[1001][n2];
		int[] d0 = d[0];
		System.arraycopy(t[0], 0, d0, 0, n2);
		for (int p = 1; p < m; p++) {
			int[] m = t[p];
			for (int ij = 0; ij < n2; ij++)
				d0[ij] = Math.min(d0[ij], m[ij]);
		}
		for (int k = 1; k <= 1000; k++) {
			int[] dp = d[k - 1];
			int[] dk = d[k];
			System.arraycopy(dp, 0, dk, 0, n2);
			for (int z = 0; z < n; z++)
				for (int i = 0; i < n; i++)
					for (int j = 0; j < n; j++) {
						int s = d0[i * n + z] + dp[z * n + j];
						if (s < dk[i * n + j])
							dk[i * n + j] = s;
					}
		}
	}

	private void expand() {
		for (int p = 0; p < m; p++) {
			int[] m = t[p];
			for (int z = 0; z < n; z++)
				for (int i = 0; i < n; i++)
					for (int j = 0; j < n; j++) {
						int s = m[i * n + z] + m[z * n + j];
						if (s < m[i * n + j])
							m[i * n + j] = s;
					}
		}
	}

	static class Input {
		private final InputStream in;
		private final byte[] buf = new byte[32768];
		private int pos;
		private int len;

		public Input(InputStream in) throws IOException {
			this.in = in;
			nextChar();
		}

		public char nextChar() throws IOException {
			char c = (char)buf[pos++];
			if (pos >= len) {
				len = in.read(buf);
				pos = 0;
			}
			return c;
		}

		public boolean isEOF() {
			return len <= 0;
		}

		public boolean isSpace() {
			return len > 0 && buf[pos] <= ' ';
		}

		public boolean isNotSpace() {
			return len > 0 && buf[pos] > ' ';
		}

		public String next() throws IOException {
			while (isSpace())
				nextChar();
			if (isEOF())
				throw new EOFException();
			StringBuilder sb = new StringBuilder();
			while (isNotSpace())
				sb.append(nextChar());
			return sb.toString();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
}
