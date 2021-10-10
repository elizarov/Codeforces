package archive.msc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * @author Roman Elizarov
 */
public class MSC2_A {

	public static final int MAX_LEN = 100000;

	public static void main(String[] args) throws IOException {
		new MSC2_A().go();
	}

	byte[] a;
	byte[] b;

	void go() throws IOException {
		// read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		a = parse(in.readLine());
		b = parse(in.readLine());

		// solve
		String result = solve();

		// write result
		PrintStream out = System.out;
		out.println(result);
	}

	byte[] parse(String s) throws IOException {
		if (s.isEmpty() || s.length() > MAX_LEN)
			throw new IllegalArgumentException();
		byte[] b = new byte[MAX_LEN + 1];
		for (int i = s.length() - 1, j = b.length - 1; i >= 0; i--, j--) {
			b[j] = (byte) (s.charAt(i) - '0');
			if (b[j] != 0 && b[j] != 1)
				throw new IllegalArgumentException();
		}
		return b;
	}

	private void norm(byte[] b) {
		assert b[0] == 0;
		for (int j = 1; j < b.length - 1; j++) {
			int i = j;
			while (i >= 0 && b[i] == 1 && b[i+1] == 1) {
				b[i-1] = 1;
				b[i] = 0;
				b[i+1] = 0;
				i -= 2;
			}
		}
	}

	String solve() {
		norm(a);
		norm(b);
		for (int i = 0; i < a.length; i++) {
			if (a[i] < b[i])
				return "<";
			if (a[i] > b[i])
				return ">";
		}
		return "=";
	}
}
