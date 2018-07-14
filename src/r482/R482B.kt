package r482b

fun solve(r: String, n: Int): Int {
    val c = r.groupingBy { it }.eachCount()
    val dom = c.values.max()!!
    val tot = c.values.sum()
    val upd = tot - dom
    if (n <= tot - dom) return dom + n
    for (k in c.values) {
        val over = n - (tot - k)
        if (over % 2 == 0) return tot
        if (over % tot == 0) return tot
    }
    if (c.size < 2 * 26 && n >= tot) {
        val over = n - tot
        if (over % 2 == 0) return tot
        if (over % tot == 0) return tot
    }
    return tot - 1
}

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val r = List(3) { readLine()!! }
    val s = r.map { solve(it, n) }
    val max = s.max()
    val w = s.filter { it == max }
    if (w.size > 1) {
        println("Draw")
    } else {
        println(listOf("Kuro", "Shiro", "Katie")[s.indexOfFirst { it == max }])
    }
}