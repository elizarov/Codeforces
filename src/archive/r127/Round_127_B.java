package archive.r127;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Roman Elizarov
 */
public class Round_127_B {
	public static void main(String[] args) throws IOException {
		new Round_127_B().go();
	}

	int n;
	int m;
	int[][] c;

	void go() throws IOException {
		// read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] s = in.readLine().trim().split("\\s+");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		c = new int[n][m];
		for (int i = 0; i < n; i++) {
			int j = 0;
			for (StringTokenizer st = new StringTokenizer(in.readLine()); st.hasMoreTokens();)
				c[i][j++] = Integer.parseInt(st.nextToken());
		}

		// solve
		long result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
		out.println(rs[0] + " " + cs[0]);
	}

	int[] rs = new int[1];
	int[] cs = new int[1];

	long solve() {
		long result = solveRow(rs);
		transpose();
		result += solveRow(cs);
		return result;
	}

	private void transpose() {
		int[][] d = new int[m][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				d[j][i] = c[i][j];
		c = d;
		int t = n;
		n = m;
		m = t;
	}

	private long solveRow(int[] out) {
	 	int[] s = new int[n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				s[i] += c[i][j];
		long result = Long.MAX_VALUE;
		int resPos = -1;
		for (int i = 0; i <= n; i++) {
			long cur = 0;
			for (int j = 0; j < n; j++) {
				long d = 4 * j + 2 - 4 * i;
				cur += d * d * s[j];
			}
			if (cur < result) {
				result = cur;
				resPos = i;
			}
		}
		out[0] = resPos;
		return result;

	}

}
