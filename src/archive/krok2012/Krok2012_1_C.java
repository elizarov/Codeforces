package archive.krok2012;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Krok2012_1_C {
	public static void main(String[] args) {
		new Krok2012_1_C().go();
	}

	int n;
	int m;
	int[][] a;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;

		n = in.nextInt();
		m = in.nextInt();
		a = alloc();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				a[i][j] = in.nextInt();

		out.println(solve());
	}

	int solve() {
		int[][] sq2 = alloc();
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < m - 1; j++)
				sq2[i][j] = a[i][j] + a[i + 1][j] + a[i][j + 1] + a[i + 1][j + 1];
		int[][] sqkPrev = alloc();
		for (int i = 0; i < n; i++)
			System.arraycopy(a[i], 0, sqkPrev[i], 0, m);
		int[][] corPrev = alloc();
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < m - 1; j++)
				corPrev[i][j] = a[i][j] - a[i + 1][j + 1];
		int[][] spiPrev = alloc();
		for (int i = 0; i < n; i++)
			System.arraycopy(a[i], 0, spiPrev[i], 0, m);

		int[][] sqkCur = alloc();
		int[][] corCur = alloc();
		int[][] spiCur = alloc();
		int result = Integer.MIN_VALUE;
		for (int k = 3; k <= n && k <= m; k += 2) {
			for (int i = 0; i <= n - k; i++)
				for (int j = 0; j <= m - k; j++) {
					int cor = corCur[i][j] = corPrev[i][j] + sq2[i + k - 2][j] + sq2[i][j + k - 2];
					int sqk = sqkCur[i][j] = cor + sqkPrev[i + 2][j + 2];
					int spi = spiCur[i][j] = sqk - a[i + 1][j] - spiPrev[i + 1][j + 1];
					if (spi > result)
						result = spi;
				}
			int[][] t;
			t = sqkCur;
			sqkCur = sqkPrev;
			sqkPrev = t;
			t = corCur;
			corCur = corPrev;
			corPrev = t;
			t = spiCur;
			spiCur = spiPrev;
			spiPrev = t;
		}
		return result;
	}

	private int[][] alloc() {
		return new int[n][m];
	}
}
