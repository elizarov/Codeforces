package archive.krok2012;

import java.io.*;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Krok2012_E {
    private static final int MAX_DEPTH = 200000;

	public static void main(String[] args) throws IOException {
		new Krok2012_E().go();
	}

    final List<String> tags = new ArrayList<String>();
    final Map<String, Integer> tagIndex = new HashMap<String, Integer>();

    {
        tags.add(null);
    }

    static class Elem {
        int tag;
        Elem parent;
        Elem[] children = new Elem[4];
        int n;

        int index;
        int prefix;

        Elem(int tag) {
            this.tag = tag;
        }

        void add(Elem elem) {
            if (n == children.length)
                children = Arrays.copyOf(children, n * 2);
            children[n++] = elem;
            elem.parent = this;
        }
    }

    char[] s;
    int pos;

    enum Token { OPEN, CLOSE, SELF_CLOSE, END }
    Token curToken;
    int curTag;

    void nextToken() {
        if (pos >= s.length) {
            curToken = Token.END;
            return;
        }
        curToken = null;
        assert s[pos] == '<';
        pos++;
        if (s[pos] == '/') {
            pos++;
            curToken = Token.CLOSE;
        }
        curTag = parseTag();
        if (curToken == null && s[pos] == '/') {
            pos++;
            curToken = Token.SELF_CLOSE;
        }
        if (curToken == null)
            curToken = Token.OPEN;
        assert s[pos] == '>';
        pos++;
    }

    StringBuilder sb = new StringBuilder(10);

    private int parseTag() {
        sb.setLength(0);
        while (s[pos] >= 'a' && s[pos] <= 'z')
            sb.append(s[pos++]);
        String s = sb.toString();
        Integer index = tagIndex.get(s);
        if (index == null) {
            index = tags.size();
            tags.add(s);
            tagIndex.put(s, index);
        }
        return index;
    }

    Elem root = new Elem(0);
    Elem[] stack = new Elem[MAX_DEPTH];
    int stackSize;

    void parseRoot() {
        nextToken();
        stackSize = 0;
        stack[stackSize++] = root;
        while (curToken != Token.END) {
            Elem top = stack[stackSize - 1];
            Elem cur = new Elem(curTag);
            Token token = curToken;
            nextToken();
            switch (token) {
                case OPEN:
                    stack[stackSize++] = cur;
                    // falls through
                case SELF_CLOSE:
                    top.add(cur);
                    break;
                case CLOSE:
                    assert top.tag == cur.tag;
                    stackSize--;
                    break;
                default:
                    assert false;
            }
        }
        assert stackSize == 1;
    }

    private void go() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = System.out;

        s = in.readLine().toCharArray();
        parseRoot();
        int m = Integer.parseInt(in.readLine().trim());
        for (int i = 0; i < m; i++)
            out.println(executeQuery(in.readLine().split("\\s+")));
	}

    int executeQuery(String[] qs) {
        int n = qs.length;
        int[] qi = new int[n];
        for (int j = 0; j < n; j++) {
            Integer index = tagIndex.get(qs[j]);
            if (index == null)
                return 0;
            qi[j] = index;
        }
        stackSize = 0;
        stack[stackSize++] = root;
        root.index = 0;
        root.prefix = 0;
        int r = 0;
        while (stackSize != 0) {
            Elem top = stack[stackSize - 1];
            int prefix = top.prefix;
            if (top.index >= top.n) {
                stackSize--;
            } else {
                Elem child = top.children[top.index++];
                if (child.tag == qi[prefix])
                    if (prefix == n - 1)
                        r++;
                    else
                        prefix++;
                child.index = 0;
                child.prefix = prefix;
                stack[stackSize++] = child;
            }
        }
        return r;
    }
}
