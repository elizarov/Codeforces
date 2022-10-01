fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val b = readln().split(" ").map { it.toInt() }
        val s = List(n) { i -> S(a[i], b[i]) }
        val (sa, sb) = s.sortedByDescending { it.b }.partition { it.a == 0 }
        println(maxOf(solveA(sa, sb), solveA(sb, sa)))
    }
}

data class S(val a: Int, val b: Int)

fun solveA(a0: List<S>, b: List<S>): Long {
    val a = if (a0.isEmpty()) a0 else buildList{
        add(a0.last())
        addAll(a0.dropLast(1))
    }
    var res = 0L
    var i = 0
    var prev: S? = null
    fun add(next: S?) {
        if (next == null) return
        res += if ((prev?.a ?: next.a) != next.a)
            2 * next.b
        else
            next.b
        prev = next
    }
    while (i < a.size || i < b.size) {
        add(a.getOrNull(i))
        add(b.getOrNull(i))
        i++
    }
    return res
}
