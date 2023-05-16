import java.util.Arrays;
import java.util.Scanner;

public class A_java {
    private static int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCount = in.nextInt();
        for (int test = 1; test <= testCount; test++) {
            int n = in.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            for (int i = 0; i < n; i++) b[i] = in.nextInt();
            Arrays.sort(a);
            Arrays.sort(b);
            int ans = 1;
            int i = n;
            for (int j = n - 1; j >= 0; j--) {
                while (i > 0 && a[i - 1] > b[j]) i--;
                ans = (int)(((long)ans * (n - i - (n - 1 - j))) % MOD);
            }
            System.out.println(ans);
        }
    }
}
