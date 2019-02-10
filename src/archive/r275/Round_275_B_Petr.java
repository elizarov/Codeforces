package archive.r275;

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.InputStream;

public class Round_275_B_Petr {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskB solver = new TaskB();
		solver.solve(1, in, out);
		out.close();
	}

static class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] l = new int[m];
        int[] r = new int[m];
        int[] q = new int[m];
        int[] firstByL = new int[n];
        int[] firstByR = new int[n];
        Arrays.fill(firstByL, -1);
        Arrays.fill(firstByR, -1);
        int[] nextByL = new int[m];
        int[] nextByR = new int[m];
        for (int i = 0; i < m; ++i) {
            l[i] = in.nextInt() - 1;
            r[i] = in.nextInt() - 1;
            q[i] = in.nextInt();
            nextByL[i] = firstByL[l[i]];
            firstByL[l[i]] = i;
            nextByR[i] = firstByR[r[i]];
            firstByR[r[i]] = i;
        }
        int[] nmust = new int[30];
        int[] last0 = new int[30];
        Arrays.fill(last0, -1);
        int[] res = new int[n];
        for (int pos = 0; pos < n; ++pos) {
            int start = firstByL[pos];
            while (start >= 0) {
                for (int i = 0; i < 30; ++i) {
                    if ((q[start] & (1 << i)) != 0) {
                        ++nmust[i];
                    }
                }
                start = nextByL[start];
            }
            for (int i = 0; i < 30; ++i) if (nmust[i] == 0) last0[i] = pos; else res[pos] |= 1 << i;
            start = firstByR[pos];
            while (start >= 0) {
                for (int i = 0; i < 30; ++i) {
                    if ((q[start] & (1 << i)) != 0) {
                        --nmust[i];
                    } else {
                        if (last0[i] < l[start]) {
                            out.println("NO");
                            return;
                        }
                    }
                }
                start = nextByR[start];
            }
        }
        out.println("YES");
        for (int i = 0; i < n; ++i) {
            if (i > 0) out.print(" ");
            out.print(res[i]);
        }
        out.println();
    }
}

static class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

}

}