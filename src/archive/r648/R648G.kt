package archive.r648

fun main() {
    val n = readLine()!!.toInt()
    solveG(n, ::interact)
}

private fun interact(x: List<Int>): Long {
    println("? ${x.size} ${x.joinToString(" ") { (it + 1).toString() }}")
    return readLine()!!.toLong()
}

fun solveG(n: Int, interact: ((List<Int>) -> Long)) {
    val m = (n + 1) / 2
    val l = (0 until m).toList()
    val r = (m until n).toList()
    val q = ArrayList<Pair<Set<Int>, Long>>()
    fun ask(x: List<Int>) {
        val b = interact(x)
        q.add(x.toSet() to b)
    }
    ask(l)
    ask(r)
    var curL = l
    var curR = r
    repeat(11) {
        val mix = List(n) { i ->
            if (i % 2 == 0) curL[i / 2] else curR[i / 2]
        }
        curL = mix.subList(0, m)
        curR = mix.subList(m, n)
        ask(curL)
    }
    val a = LongArray(n)
    for (j in 0 until 63) {
        var s: Set<Int>? = null
        for ((x, b) in q) {
            if ((b shr j) and 1 != 0L) {
                s = if (s == null) x else (s intersect x)
            }
        }
        if (s != null) {
            val z = s.singleOrNull() ?: -1
            for (i in 0 until n) if (i != z) {
                a[i] = a[i] or (1L shl j)
            }
        }
    }
    println("! ${a.joinToString(" ")}")
}

