import kotlin.math.max
import kotlin.math.min

fun main() {
    val (n, qm) = readln().split(" ").map { it.toInt() }
    val a = readln().split(" ").map { it.toInt() }.toIntArray()
    val st = SegmentTree(a)
    val ans = List(qm) { i ->
        val (l, r) = readln().split(" ").map { it.toInt() - 1 }
        st[l, r].d
    }.toMutableList()
    println(ans.joinToString("\n"))
}

private tailrec fun approxSegmentTreeSize(p: Int, tl: Int, tr: Int): Int {
    if (tl >= tr) return p + 1
    return approxSegmentTreeSize(2 * p + 2, (tl + tr + 1) / 2, tr)
}

class Ans(val d: Int, val t: IntArray?)

val empty = Ans(Int.MAX_VALUE, intArrayOf())
val one = Ans(1, null)

fun init(x: Int): Ans = Ans(Int.MAX_VALUE, intArrayOf(x))

fun merge(a1: Ans, a2: Ans): Ans {
    var d = minOf(a1.d, a2.d)
    if (d == 1) return one
    val t1 = a1.t!!
    val t2 = a2.t!!
    val t = IntArray(t1.size + t2.size)
    var i = 0
    var j = 0
    for (k in t.indices) {
        if (i < t1.size && (j >= t2.size || t1[i] <= t2[j])) {
            t[k] = t1[i++]
        } else {
            t[k] = t2[j++]
        }
    }
    for (k in 1 until t.size) d = minOf(d, t[k] - t[k - 1])
    return if (d == 1) one else Ans(d, t)
}

class SegmentTree(a: IntArray) {
    private val n = a.size
    private val t = arrayOfNulls<Ans>(approxSegmentTreeSize(0, 0, n - 1))
    init {
        build(a, 0, 0, n - 1)
    }

    private fun build(a: IntArray, p: Int, tl: Int, tr: Int): Ans {
        val x = if (tl == tr) {
            init(a[tl])
        } else {
            val m = (tl + tr) / 2
            merge(
                build(a, 2 * p + 1, tl, m),
                build(a, 2 * p + 2, m + 1, tr)
            )
        }
        t[p] = x
        return x
    }

    operator fun get(l: Int, r: Int): Ans = get0(0, 0, n - 1, l, r)

    private fun get0(p: Int, tl: Int, tr: Int, l: Int, r: Int): Ans {
        if (l > r) return empty
        if (l == tl && r == tr) return t[p]!!
        val m = (tl + tr) / 2
        return merge(
            get0(2 * p + 1, tl, m, l, min(r, m)),
            get0(2 * p + 2, m + 1, tr, max(l, m + 1), r)
        )
    }
}
