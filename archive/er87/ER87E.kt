package archive.er87

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val (n1, n2, n3) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array(n) { ArrayList<Int>() }
    repeat(m) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[u].add(v)
        g[v].add(u)
    }
    val ans = solveE(n, g, n1, n2)
    if (ans == null) {
        println("NO")
    } else {
        println("YES")
        println(ans.joinToString(""))
    }
}

private fun solveE(
    n: Int,
    g: Array<ArrayList<Int>>,
    n1: Int,
    n2: Int
): IntArray? {
    val d = IntArray(n) { -1 }
    val c = Array(n) { ArrayList<Int>() }
    val s = Array(2) { IntArray(n) }
    val q = IntArray(n)
    var qh = 0
    var qt = 0
    var cc = 0
    for (i in 0 until n) {
        if (d[i] >= 0) continue
        d[i] = 0
        q[qt++] = i
        s[0][cc]++
        c[cc].add(i)
        while (qh < qt) {
            val u = q[qh++]
            for (v in g[u]) {
                if (d[v] >= 0) {
                    if ((d[u] - d[v]) % 2 == 0) {
                        return null
                    }
                    continue
                }
                d[v] = d[u] + 1
                q[qt++] = v
                s[d[v] % 2][cc]++
                c[cc].add(v)
            }
        }
        cc++
    }
    val a = IntArray(cc)
    var min2 = 0
    var max2 = 0
    for (i in 0 until cc) {
        val min = minOf(s[0][i], s[1][i])
        val max = maxOf(s[0][i], s[1][i])
        min2 += min
        max2 += max
        a[i] = max - min
    }
    if (n2 !in min2..max2) return null
    val bs = bagSum(a, n2 - min2) ?: return null
    val ans = IntArray(n)
    var set1 = 0
    for (i in 0 until cc) {
        val maxI = if (s[1][i] > s[0][i]) 1 else 0
        val mod2 = if (bs[i]) maxI else 1 - maxI
        for (u in c[i]) {
            if (d[u] % 2 == mod2) {
                ans[u] = 2
            } else {
                if (set1 < n1) {
                    ans[u] = 1
                    set1++
                } else {
                    ans[u] = 3
                }
            }
        }
    }
    return ans
}

fun bagSum(a: IntArray, x: Int): BitSet? {
    val n = a.size
    if (x == 0) return BitSet(n)
    val sol = arrayOfNulls<BitSet>(x + 1)
    val ord = a.withIndex().filter { it.value > 0 }.sortedBy { it.value }
    sol[0] = BitSet(n)
    for ((i, d) in ord) {
        for ((sum, bs) in sol.withIndex()) {
            if (sum + d > x) break
            if (bs == null) continue
            if (bs[i]) continue
            if (sol[sum + d] == null) {
                val new = bs.clone() as BitSet
                new.set(i)
                if (sum + d == x) return new
                sol[sum + d] = new
            }
        }
    }
    return null
}
