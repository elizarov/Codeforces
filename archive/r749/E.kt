fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    data class E(val x: Int, val y: Int)
    val edges = Array(m) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        E(x, y)
    }
    val cnt = IntArray(n)
    data class Q(val a: Int, val b: Int)
    val qs = Array(readLine()!!.toInt()) {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() - 1 }
        cnt[a]++
        cnt[b]++
        Q(a, b)
    }
    val co = cnt.count { it % 2 != 0 }
    if (co == 0) {
        val p = IntArray(n) { it }
        val d = IntArray(n)
        val sg = Array(n) { ArrayList<Int>() }
        for ((x, y) in edges) {
            var px = p[x]; while (p[px] != px) px = p[px]
            var py = p[y]; while (p[py] != py) py = p[py]
            if (px == py) continue
            sg[x].add(y)
            sg[y].add(x)
            if (d[px] < d[py]) {
                p[px] = py
            } else {
                if (d[px] == d[py]) d[px]++
                p[py] = px
            }
        }
        val qu = IntArray(n)
        val output = ArrayList<String>()
        for ((a, b) in qs) {
            d.fill(-1)
            qu[0] = a
            d[a] = 0
            var qh = 0
            var qt = 1
            while (qh < qt && d[b] < 0) {
                val v = qu[qh++]
                for (u in sg[v]) {
                    if (d[u] < 0) {
                        qu[qt++] = u
                        d[u] = d[v] + 1
                        p[u] = v
                    }
                }
            }
            val ans = ArrayList<Int>()
            var v = b
            ans.add(v + 1)
            while (v != a) {
                v = p[v]
                ans.add(v + 1)
            }
            ans.reverse()
            output.add(ans.size.toString())
            output.add(ans.joinToString(" "))
        }
        println("YES")
        println(output.joinToString("\n"))
    } else {
        println("NO")
        println(co / 2)
    }
}