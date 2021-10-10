package archive.r274;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_274_D {
	public static final int CACHE_BITS = 24;
	public static final int CACHE_MASK = (1 << (CACHE_BITS + 1)) - 1;
	public static final int MAGIC = 0x9E779B9;

	public static void main(String[] args) {
		new Round_274_D().go();
	}

	int n;
	int S;

	static class Box implements Comparable<Box> {
		int in;
		int out;
		int w;
		int s;
		int v;

		Box(int in, int out, int w, int s, int v) {
			this.in = in;
			this.out = out;
			this.w = w;
			this.s = s;
			this.v = v;
		}

		@Override
		public int compareTo(Box o) {
			int d = in - o.in;
			return d != 0 ? d : o.out - out;
		}
	}

	Box[] boxes;
	int[] outIdx;

	void go() {
		// read input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		S = in.nextInt();

		boxes = new Box[n];
		for (int i = 0; i < n; i++)
			boxes[i] = new Box(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());

		// solve
		int result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	int solve() {
		Arrays.sort(boxes);
		outIdx = new int[n];
		for (int i = 0; i < n; i++) {
			outIdx[i] = n;
			for (int j = n; --j >= 0;) {
				if (boxes[j].in >= boxes[i].out)
					outIdx[i] = j;
				else
					break;
			}
		}
		return solve(0, S, 2 * n);
	}

	int[] cache = new int[1 << (CACHE_BITS + 1)];

	int solve(int i, int S, int tl) {
		if (i >= n)
			return 0;
		Box box = boxes[i];
		if (box.in >= tl)
			return 0;

		int result;
		if (box.w <= S && box.out <= tl) {

			int code = (i << 20) | (S << 10) | tl;
			int index0 = ((code * MAGIC) >>> (32 - CACHE_BITS)) << 1;
			int index = index0;
			int cv;
			while ((cv = cache[index]) != 0) {
				if (cv == code)
					return cache[index + 1];
				index = (index + 2) & CACHE_MASK;
			}

			result = Math.max(solve(i + 1, S, tl),
					box.v +
						solve(i + 1, Math.min(box.s, S - box.w), box.out) +
						solve(outIdx[i], S, tl));

			index = index0;
			while (cache[index] != 0)
				index = (index + 2) & CACHE_MASK;
			cache[index] = code;
			cache[index + 1] = result;
		} else
			result = solve(i + 1, S, tl);

		return result;
	}
}
