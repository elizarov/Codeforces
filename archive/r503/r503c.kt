package archive.r503

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array(n) { ArrayList<Int>() }
    val a = Array(n) { ArrayList<Int>() }
    val c = IntArray(n)
    repeat (m) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[u].add(v)
        a[u].add(v)
        a[v].add(u)
        c[v]++
    }
    val d = IntArray(n) { Int.MAX_VALUE }

    val queue = IntArray(n)
    var h = 0
    var t = 0

    fun update(u: Int, dist: Int) {
        d[u] = dist
        if (dist < 2) {
            for (v in g[u]) {
                if (dist + 1 < d[v]) {
                    if (d[v] == Int.MAX_VALUE) {
                        c[v]--
                        if (c[v] == 0 && dist == 1) {
                            queue[t++] = v
                        }
                    }
                    update(v, dist + 1)
                }
            }
        }
    }

    for (i in 0 until n) if (c[i] == 0) {
        queue[t++] = i
    }

    while (h < t) {
        val u = queue[h++]
        update(u, 0)
    }

    for (i in 0 until n) if (d[i] == Int.MAX_VALUE) {
        queue[t++] = i
        while (h < t) {
            val u = queue[h++]
            update(u, 0)
        }
    }

    val ans = ArrayList<Int>()
    for (i in 0 until n) if (d[i] == 0) ans.add(i + 1)

    println(ans.size)
    println(ans.joinToString(" "))
}