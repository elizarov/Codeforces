package archive.msc2;

import java.util.Random;

/**
 * @author Roman Elizarov
 */
public class MSC2_B_Stress {
	int m = 3;
	int n = 2;
	int cc = 1 << (n + m);

	public static void main(String[] args) {
		new MSC2_B_Stress().go();
	}

	private void go() {
		MSC2_B t = new MSC2_B();
		t.m = m;
		t.n = n;
		t.a = new int[t.m];
		t.b = new int[t.n];
		ca = new int[m];
		cb = new int[n];
		Random r = new Random(20141008);
		int cnt = 0;
		while (true) {
			for (int i = 0; i < m; i++)
				t.a[i] = r.nextInt(100) + 1;
			for (int i = 0; i < n; i++)
				t.b[i] = r.nextInt(100) + 1;
			long sol = t.solve();
			best = Integer.MAX_VALUE;
			findA(t, 0, 0);
			if (best != sol) {
				System.out.println();
				System.out.println(best + " != " + sol);
				for (int i = 0; i < m; i++) {
					System.out.println(t.a[i] + " " + Integer.toBinaryString(bca[i]));
				}
				for (int i = 0; i < n; i++) {
					System.out.println(t.b[i] + " " + Integer.toBinaryString(bcb[i]));
				}
			} else {
				System.out.print(".");
				if (cnt++ >= 80) {
					System.out.println();
					cnt = 0;
				}
			}
		}
	}

	int[] ca;
	int[] cb;

	int best;
	int[] bca;
	int[] bcb;

	private void findA(MSC2_B t, int k, int sum) {
		if (k == m) {
			findB(t, 0, sum);
			return;
		}
		for (int c = 0; c < cc; c++) {
			if ((c & (1 << k)) != 0)
				continue;
			ca[k] = c;
			findA(t, k+1, sum + t.a[k] * Integer.bitCount(c));
		}
	}

	private void findB(MSC2_B t, int k, int sum) {
		if (k == n) {
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++) {
					if ((ca[i] & (1 << (m + j))) == 0 && (cb[j] & (1 << i)) == 0) {
						boolean good = false;
						for (int z = 0; z < m + n; z++) {
							if ((ca[i] & (1 << z)) != 0 && (cb[j] & (1 << z)) != 0){
								good = true;
								break;
							}
						}
						if (!good)
							return;
					}
				}
		 	if (sum < best) {
				best = sum;
				bca = ca.clone();
				bcb = cb.clone();
			}
			return;
		}
		for (int c = 0; c < cc; c++) {
			if ((c & (1 << (m + k))) != 0)
				continue;
			cb[k] = c;
			findB(t, k + 1, sum + t.b[k] * Integer.bitCount(c));
		}
	}
}
