package archive.r102;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class Round_102_B {

	private static final BigDecimal POW = BigDecimal.valueOf(100);

	public static void main(String[] args) {
		new Round_102_B().go();
	}

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		BigDecimal d = new BigDecimal(in.next());
		String s = String.format(Locale.US, "$%,.2f", d.abs().setScale(2, RoundingMode.DOWN));
		if (d.signum() < 0)
			s = "(" + s + ")";
		out.println(s);
	}
}
