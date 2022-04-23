fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val b = solveF1(n, a)
        println(b.joinToString(" "))
    }
}

fun solveF1(n: Int, a: List<Int>): IntArray {
    data class IVC(val i: Int, val v: Int, val c: Int) : Comparable<IVC> {
        override fun compareTo(other: IVC): Int {
            if (c != other.c) return other.c - c
            return v - other.v
        }
    }
    val m = a.groupingBy { it }.eachCount()
    val p = a.mapIndexed { i, v -> IVC(i, v, m[v]!!) }.sorted()
    var j = 1
    while (j < n && p[j].v == p[0].v) j++
    val b = IntArray(n)
    for (i in 0 until n) {
        b[p[i].i] = p[(i + j) % n].v
    }
    return b
}