package archive.er67

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val se = ArrayList<Ev>(2 * m)
    val ur = ArrayList<Rg>(m)
    repeat(m) {
        val (t, l, r) = readLine()!!.split(" ").map { it.toInt() }
        when (t) {
            0 -> {
                ur.add(Rg(l - 1, r))
            }
            1 -> {
                se.add(Ev(1, l - 1))
                se.add(Ev(-1, r))
            }
            else -> error("$t")
        }
    }
    se.sort()
    var cnt = 0
    val ans = IntArray(n)
    var cur = 2_000
    var idx = 0
    val sm = ArrayList<Ev>(2 * m)
    for (e in se) {
        while (idx < e.x) {
            if (cnt == 0) {
                cur--
            } else {
                cur++
            }
            ans[idx++] = cur
        }
        if (cnt == 0) sm.add(Ev(1, e.x))
        cnt += e.d
        if (cnt == 0) {
            sm.add(Ev(-1, e.x))
            if (idx < n) ans[idx++] = --cur
        }
    }
    while (idx < n) {
        ans[idx++] = --cur
    }
    for (rg in ur) {
        var ok = false
        for (i in rg.l + 1 until rg.r) {
            if (ans[i] < ans[i - 1]) {
                ok = true
                break
            }
        }
        if (!ok) {
            println("NO")
            return
        }
    }
    println("YES")
    println(ans.joinToString(" "))
}

data class Ev(val d: Int, val x: Int) : Comparable<Ev> {
    override fun compareTo(other: Ev): Int = when {
        x < other.x -> -1
        x > other.x -> 1
        d < other.d -> -1
        d > other.d -> 1
        else -> 0
    }
}

data class Rg(val l: Int, val r: Int)