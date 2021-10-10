package archive.krok2012;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Krok2012_2_C {
	private static final String FIRST = "First";
	private static final String SECOND = "Second";

	private static final int W = 7;
	private static final long ROW = (1L << W) - 1;
	private static final long COL;

	static {
		long col = 0;
		for (int i = 0; i < W; i++)
			col |= 1L << (i * W);
		COL = col;
	}

	public static void main(String[] args) {
		new Krok2012_2_C().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		int n = in.nextInt();
		int m = in.nextInt();
		int x1 = in.nextInt();
		int y1 = in.nextInt();
		int x2 = in.nextInt();
		int y2 = in.nextInt();
		out.println(solve(n, m, x1, y1, x2, y2));
	}

	String solve(int n, int m, int x1, int y1, int x2, int y2) {
		int dx = Math.abs(x1 - x2);
		int dy = Math.abs(y1 - y2);
		if (dx >= W || dy >= W)
			return SECOND;
		return solved(dx, dy, 0) ? FIRST : SECOND;
	}

	Map<Long, Boolean> dp = new HashMap<Long, Boolean>();

	boolean solved(int dx, int dy, long mask) {
		// 0 <= dx, dy < W
		if (dx + dy <= 2)
			return true;
		Long pos = mask | (dx << (W * W)) | (dy << (W * W + 3));
		Boolean res = dp.get(pos);
		if (res != null)
			return res;
		if (dx > 0) {
			if (get(1, 0, mask) == 0 && solve2(dx - 1, dy, (mask & ~COL) >> 1)) {
				dp.put(pos, true);
				//System.out.println("(" + dx + "," + dy + "," + Long.toHexString(mask) + ") -> 1,0");
				return true;
			}
			if (get(dx - 1, dy, mask) == 0 && solve2(dx - 1, dy, mask & ~(COL << dx))) {
				dp.put(pos, true);
				//System.out.println("(" + dx + "," + dy + "," + Long.toHexString(mask) + ") -> -1,0");
				return true;
			}
		}
		if (dy > 0) {
			if (get(0, 1, mask) == 0 && solve2(dx, dy - 1, mask >> W)) {
				dp.put(pos, true);
				//System.out.println("(" + dx + "," + dy + "," + Long.toHexString(mask) + ") -> 0,1");
				return true;
			}
			if (get(dx, dy - 1, mask) == 0 && solve2(dx, dy - 1, mask & ~(ROW << (dy * W)))) {
				dp.put(pos, true);
				//System.out.println("(" + dx + "," + dy + "," + Long.toHexString(mask) + ") -> 0,-1");
				return true;
			}
		}
		dp.put(pos, false);
		return false;
	}

	private boolean solve2(int dx, int dy, long mask) {
		for (int i = 0; i <= dx; i++)
			for (int j = 0; j <= dy; j++)
				if ((i != 0 || j != 0) && (i != dx || j != dy) && get(i, j, mask) == 0 &&
						!solved(dx, dy, mask | (1L << (i + j * W))))
					return false;
		return solved(dx, dy, mask);
	}

	private int get(int dx, int dy, long mask) {
		return (int)((mask >> (dx + dy * W)) & 1);
	}
}
