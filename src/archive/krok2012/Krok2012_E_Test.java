package archive.krok2012;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author Roman Elizarov
 */
public class Krok2012_E_Test extends TestCase {
    public void testMax() {
        long time = System.currentTimeMillis();
        String tag = "a";
        String open = "<" + tag + ">";
        String close = "</" + tag + ">";
        int len = open.length() + close.length();
        int n = 1000000 / len;
        StringBuilder sb = new StringBuilder(n * len);
        for (int i = 0; i < n; i++)
            sb.append(open);
        for (int i = 0; i < n; i++)
            sb.append(close);
        Krok2012_E instance = new Krok2012_E();
        instance.s = sb.toString().toCharArray();
        instance.parseRoot();
        String[] qs = new String[200];
        Arrays.fill(qs, tag);
        for (int i = 0; i < 200; i++)
            instance.executeQuery(qs);
        System.out.println(System.currentTimeMillis() - time);
    }
}
