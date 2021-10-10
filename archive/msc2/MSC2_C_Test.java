package archive.msc2;

import junit.framework.TestCase;

import java.util.Scanner;

/**
 * @author Roman Elizarov
 */
public class MSC2_C_Test extends TestCase {
	public void test8() {
		MSC2_C t = new MSC2_C();
		t.read(new Scanner("10\n" +
				"3 8664\n" +
				"1 783\n" +
				"1 3007\n" +
				"2 2929\n" +
				"2 4685\n" +
				"3 9787\n" +
				"2 4078\n" +
				"0 0\n" +
				"2 5104\n" +
				"2 6635\n"));
		assertEquals(7790, t.solve());
	}
}
