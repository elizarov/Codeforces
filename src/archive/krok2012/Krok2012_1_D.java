package archive.krok2012;

import java.io.PrintStream;
import java.util.*;

/**
 * @author Roman Elizarov
 */
public class Krok2012_1_D {
	public static void main(String[] args) {
		new Krok2012_1_D().go();
	}

	int n;
	List<List<Integer>> e = new ArrayList<List<Integer>>();

	List<List<Integer>> s = new ArrayList<List<Integer>>();
	List<List<Integer>> ss = new ArrayList<List<Integer>>();

	int[] res;
	int rc;

	private void go() {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		n = in.nextInt();
		int m = in.nextInt();
		init();
		for (int i = 0; i < m; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			e.get(a).add(b);
			e.get(b).add(a);
		}

		boolean yes = solve();

		if (yes) {
			System.out.println("YES");
			for (int i = 0; i < n; i++) {
				if (i > 0)
					System.out.print(' ');
				System.out.print(res[i]);
			}
		} else
			System.out.println("NO");
	}

	void init() {
		while (e.size() < n)
			e.add(new ArrayList<Integer>());
	}

	boolean solve() {
		res = new int[n];

		int[] sn = new int[n];
		int sc = 0;
		Arrays.fill(sn, -1);

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < n; i++)
			if (sn[i] == -1) {
				s.add(new ArrayList<Integer>());
				s.add(new ArrayList<Integer>());
				sn[i] = sc;
				s.get(sc).add(i);
				queue.add(i);
				while (!queue.isEmpty()) {
			 	    int a = queue.remove();
					int sa = sn[a];
					int sb = 2*sc + 1 - sa;
					for (Integer b : e.get(a)) {
						assert sn[b] != sa;
						if (sn[b] == -1) {
							sn[b] = sb;
							s.get(sb).add(b);
							queue.add(b);
						}
					}
				}
				sc += 2;
			}

		int[][] vmod = new int[sc / 2 + 1][3];
		Arrays.fill(vmod[0], -1);
		vmod[0][0] = 0;
		for (int i = 0; i < sc / 2; i++) {
			Arrays.fill(vmod[i + 1], -1);
			for (int j = 0; j < 3; j++)
				if (vmod[i][j] >= 0) {
					vmod[i + 1][(j + s.get(2 * i).size()) % 3] = 2 * i;
					vmod[i + 1][(j + s.get(2 * i + 1).size()) % 3] = 2 * i + 1;
				}
		}

		ss.add(new ArrayList<Integer>());
		ss.add(new ArrayList<Integer>());

		if (vmod[sc / 2][0] >= 0) {
			int mod = 0;
			for (int i = sc / 2; i > 0; i--) {
				int k = vmod[i][mod];
				ss.get(0).addAll(s.get(k));
				ss.get(1).addAll(s.get(k ^ 1));
				mod = (mod + n - s.get(k).size()) % 3;
			}
			if (mod != 0)
				throw new IllegalStateException("mod = " + mod);
		} else {
			for (int i = 0; i < sc / 2; i++) {
				ss.get(0).addAll(s.get(2 * i));
				ss.get(1).addAll(s.get(2 * i + 1));
			}
		}

		if (ss.get(0).size() + ss.get(1).size() != n)
			throw new IllegalStateException(ss.get(0).size() + " + " + ss.get(1).size() + " != " + n);

		boolean yes = false;
		int s1 = ss.get(0).size() % 3;
		if (s1 == 0)
			yes = true;
		else {
			s1--;
			int s2 = 1 - s1;
			List<Integer> list1 = ss.get(s1);
			List<Integer> list2 = ss.get(s2);
			for (int a : list1) {
				if (e.get(a).size() <= list2.size() - 2) {
					yes = true;
					res[a] = 1;
					rc = 1;
					Set<Integer> ee = new HashSet<Integer>(e.get(a));
					List<Integer> found = new ArrayList<Integer>();
					for (Integer b : list2) {
						if (!ee.contains(b)) {
							res[b] = 1;
							found.add(b);
							if (found.size() >= 2)
								break;
						}
					}
					if (found.size() != 2)
						throw new IllegalStateException("found: " + found);
					list1.removeAll(Arrays.asList((Integer)a));
					list2.removeAll(found);
					break;
				}
			}
		}

		if (yes) {
			assign(ss.get(0));
			assign(ss.get(1));
		}
		return yes;
	}

	private void assign(List<Integer> list) {
		if (list.size() % 3 != 0)
			throw new IllegalArgumentException("size=" + list.size());
		for (int i = 0; i < list.size(); i += 3) {
			rc++;
			res[list.get(i)] = rc;
			res[list.get(i + 1)] = rc;
			res[list.get(i + 2)] = rc;
		}
	}
}
