package archive.r146;

import junit.framework.TestCase;

/**
 * @author Roman Elizarov
 */
public class Round_146_A_Test extends TestCase {
    public void test() {
        assertEquals(1, Round_146_A.solve(1));
        assertEquals(2, Round_146_A.solve(2));
        assertEquals(6, Round_146_A.solve(3));
        assertEquals(12, Round_146_A.solve(4));
        assertEquals(60, Round_146_A.solve(5));
        assertEquals(60, Round_146_A.solve(6));
        assertEquals(210, Round_146_A.solve(7));
        assertEquals(280, Round_146_A.solve(8));
    }
}
