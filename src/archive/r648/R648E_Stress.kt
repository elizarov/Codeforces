package archive.r648

import kotlin.random.*

fun main() {
    val n = 5
    val rnd = Random(1)
    val lim = 64L
    repeat(10000) {
        val a = LongArray(n) { rnd.nextLong(lim) }.sortedDescending().toLongArray()
        val ans = solveE(n, a)
        val (best, ss) = bruteSolveE(n, a)
        if (ans != best) {
            println(a.joinToString(" "))
            for (i in a) println(i.toString(2).padStart(5, '0'))
            println("ans = $ans")
            println("best = $best")
            println("ss = ${ss.toString(2).padStart(n, '0')}")
            return
        }
    }
}

fun bruteSolveE(n: Int, a: LongArray): Pair<Long, Int> {
    var best = 0L
    var bss = 0
    for (ss in 0 until (1 shl n)) {
        val c = IntArray(63)
        var k = 0
        for (i in 0 until n) if ((ss shr i) and 1 != 0) {
            k++
            for (j in 0 until 63) if ((a[i] shr j) and 1 != 0L) {
                c[j]++
            }
        }
        val good = maxOf(1, k - 2)
        val ans = c.withIndex().filter { it.value >= good }.map { 1L shl it.index }.sum()
        if (ans > best) {
            best = ans
            bss = ss
        }
    }
    return best to bss
}

fun greedySolveE(n: Int, a: LongArray): Long {
    val bits = 63
    val bl = Array(bits) { HashSet<Int>() }
    for (i in 0 until n) {
        for (j in 0 until bits) {
            if ((a[i] shr j) and 1 != 0L) {
                bl[j].add(i)
            }
        }
    }
    val u = BooleanArray(bits)
    repeat(3) {
        var cur: Set<Int>? = null
        for (j in bits - 1 downTo 0) if (!u[j]) {
            val next = if (cur == null) bl[j] else (cur intersect bl[j])
            if (next.isEmpty()) continue
            u[j] = true
            cur = next
        }
    }
    val ans = (0 until bits).filter { u[it] }.map { 1L shl it }.sum()
    return ans
}