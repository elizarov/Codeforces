package archive.r102;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_102_E {

	private int n;
	private int m;
	private int[] prev;
	private int[] cur;
	private int mm;
	private int m2m;

	private char[][] res;
	private char last;
	private int scanprof;
	private int scandnd;
	private int ii;

	public static void main(String[] args) {
		new Round_102_E().go();
	}


	private void go() {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		mm = (1 << m);
		m2m = (1 << (2 * m));
		int[][] cnt = new int[n + 1][m2m];
		for (int i = 0; i <= n; i++)
			Arrays.fill(cnt[i], Integer.MIN_VALUE);
		cnt[0][0] = 0;
		for (ii = 0; ii < n; ii++) {
			prev = cnt[ii];
			cur = cnt[ii + 1];
			cur[0] = 0;
			for (int row = 0; row < mm; row++)
				scan(0, row, 0, 0);
		}
		res = new char[n][m];
		for (int i = 0; i < n; i++)
			Arrays.fill(res[i], '.');
		last = 'A';
		int max = 0;
		scanprof = -1;
		for (int prof = 0; prof < m2m; prof++) {
			if (cnt[n][prof] > max) {
				max = cnt[n][prof];
				scanprof = prof;
			}
		}
		if (max > 0)
			for (ii = n; --ii >= 0; ) {
				prev = cnt[ii];
				cur = cnt[ii + 1];
				scan(0, scanprof & (mm - 1), 0, 0);
				scandnd = scanprof >> m;
			}
		System.out.println(max);
		printRes();
	}

	private void printRes() {
		PrintStream out = System.out;
		for	(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				out.print(res[i][j]);
			out.println();
		}
	}


	private boolean scan(int prof, int row, int j, int k) {
		while (j < m && (row & (1 << j)) == 0)
			j++;
		if (j >= m) {
			int next = ((prof & (mm - 1)) << m) | row;
			for (int other = (m2m - 1) & ~prof; other >= 0; other = (other - 1) & ~prof) {
				int nn = ((other & (mm - 1)) << m) | next;
				int num = prev[other] + k;
				if (res != null && cur[nn] == num && nn == scanprof) {
					printRes();
					//System.out.println(Integer.toBinaryString(prevp));
					scanprof = other;
					return true;
				}
				if (num > cur[nn] && num >= 0) {
//					System.out.println(ii + " " +
//							bin(prof, 2 * m) + " " +
//							bin(row, m) + " " +
//							bin(other, 2 * m) + " " +
//							bin(nn, 2 * m) + " " +
//							j + " " + k + " " + num);
					cur[nn] = num;
				}
			}
			return false;
		}
		if (j > 1 && ii > 1) {
			int mask = (1 << j) | (1 << (m + j)) | (1 << (j - 1))| (1 << (j - 2));
			if (((prof | scandnd) & mask) == 0) {
				if (res != null)
					set1(j, last++);
				if (scan(prof | mask, row, j + 1, k + 1))
					return true;
				if (res != null) {
					set1(j, '.');
					last--;
				}
			}
		}
		if (j < m - 2 && ii > 1) {
			int mask = (1 << j) | (1 << (m + j)) | (1 << (j + 1))| (1 << (j + 2));
			if (((prof | scandnd) & mask) == 0) {
				if (res != null)
					set2(j, last++);
				if (scan(prof | mask, row, j + 1, k + 1))
					return true;
				if (res != null) {
					set2(j, '.');
					last--;
				}
			}
		}
		if (j > 0 && j < m - 1 && ii > 1) {
			int mask = (1 << j) | (1 << (m + j - 1)) | (1 << (m + j + 1)) | (1 << (m + j));
			if (((prof | scandnd) & mask) == 0) {
				if (res != null)
					set3(j, last++);
				if (scan(prof | mask, row, j + 1, k + 1))
					return true;
				if (res != null) {
					set3(j, '.');
					last--;
				}
			}
		}
		if (j < m - 2 && ii > 1) {
			int m0 = (1 << j) | (1 << (j + 1)) | (1 << (j + 2));
			if ((row & m0) == m0) {
				int mask = (1 << (j + 1)) | (1 << (m + j + 1));
				if (((prof | scandnd) & mask) == 0) {
					if (res != null)
						set4(j, last++);
					if (scan(prof | mask, row, j + 3, k + 1))
						return true;
					if (res != null) {
						set4(j, '.');
						last--;
					}
				}
			}
		}
		return false;
	}

	private String bin(int prof, int bits) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bits; i++)
			sb.append((prof >> i) & 1);
		return sb.toString();
	}

	private void set1(int j, char c) {
		res[ii][j] = c;
		res[ii - 1][j] = c;
		res[ii - 1][j - 1] = c;
		res[ii - 1][j - 2] = c;
		res[ii - 2][j] = c;
	}

	private void set2(int j, char c) {
		res[ii][j] = c;
		res[ii - 1][j] = c;
		res[ii - 1][j + 1] = c;
		res[ii - 1][j + 2] = c;
		res[ii - 2][j] = c;
	}

	private void set3(int j, char c) {
		res[ii][j] = c;
		res[ii - 1][j] = c;
		res[ii - 2][j + 1] = c;
		res[ii - 2][j - 1] = c;
		res[ii - 2][j] = c;
	}

	private void set4(int j, char c) {
		res[ii][j] = c;
		res[ii][j + 1] = c;
		res[ii][j + 2] = c;
		res[ii - 1][j + 1] = c;
		res[ii - 2][j + 1] = c;
	}
}
