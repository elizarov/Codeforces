package archive.krok2012;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Krok2012_C {
	public static void main(String[] args) {
		new Krok2012_C().go();
	}

    private class Person implements Comparable<Person> {
        int t;
        int x;
        int w;

        public int compareTo(Person o) {
            return x - o.x;
        }
    }

    private class Car {
        final int m;
        final Queue<Person> queue = new LinkedList<Person>();
        final List<Person> sit = new ArrayList<Person>();
        int t0;

        private Car(int m) {
            this.m = m;
        }

        void add(Person p) {
            queue.add(p);
        }

        void go() {
            while (!queue.isEmpty())
                goOnce();
        }

        void goOnce() {
            while (sit.size() < m && !queue.isEmpty()) {
                Person p = queue.remove();
                sit.add(p);
                t0 = Math.max(t0, p.t);
            }
            Collections.sort(sit);
            int x0 = 0;
            for (int i = 0, n = sit.size(); i < n;) {
                Person p = sit.get(i);
                int k = 1;
                while (i + k < n && sit.get(i + k).x == p.x)
                    k++;
                t0 += p.x - x0;
                for (int j = i; j < i + k; j++)
                    sit.get(j).w = t0;
                t0 += 1 + k / 2;
                x0 = p.x;
                i += k;
            }
            t0 += x0;
            sit.clear();
        }
    }

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
        int n = in.nextInt();
        int m = in.nextInt();
        Car car = new Car(m);
        Person[] p = new Person[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Person();
            p[i].t = in.nextInt();
            p[i].x = in.nextInt();
            car.add(p[i]);
        }
        car.go();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                out.print(' ');
            out.print(p[i].w);
        }
	}
}
