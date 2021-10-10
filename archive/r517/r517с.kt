package archive.r517

fun main(args: Array<String>) {
    val n = 12
    val ms = mutableListOf<Int>()
    x@ for (x in 0 until n) {
        for (y in x + 1 until n) {
            val z = 2 * y - x
            if (z >= n) continue@x
            val m = (1 shl x) or (1 shl y) or (1 shl z)
            ms.add(m)
        }
    }
    println("Combos: ${ms.size}")
    val found = IntArray(1 shl n) { Int.MAX_VALUE }
    val q = IntArray(1 shl n)
    var h = 0
    var t = 0
    fun enq(m: Int, d: Int) {
        if (found[m] < Int.MAX_VALUE) return
        q[t++] = m
        found[m] = d
    }
    enq(0, 0)
    while (h != t) {
        val m = q[h++]
        for (k in ms) {
            enq(m xor k, found[m] + 1)
        }
    }
    println("Not found")
    printAt(n, found, Int.MAX_VALUE)
    val dd = found[q[h - 1]]
    println("Hardest @ $dd:")
    printAt(n, found, dd)
}

private fun printAt(n: Int, found: IntArray, dd: Int) {
    var cnt = 0
    for (m in 0 until (1 shl n)) {
        if (found[m] == dd) {
            cnt++
            if (cnt < 10) println(m.toString(2).padStart(n, '0'))
        }
    }
    println("Total: $cnt")
}

