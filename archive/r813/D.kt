import kotlin.math.*

val inf = 1_000_000_000

fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        println(solveD(n, k, a))
    }
}

fun solveD(n: Int, k: Int, a: IntArray): Int {
    val r = a
        .withIndex().zipWithNext { x, y -> if (x.value < y.value) x else y }
        .sortedBy { it.value }.map { it.index }.distinct()
        .take(k)
    val a1 = a.copyOf()
    for (i in r) a1[i] = inf
    return computeD(a1)
}

fun computeD(a: IntArray): Int {
    val st = ISegmentTree(a)
    return computeD(a, st, 0, a.size, inf)
}

fun computeD(a: IntArray, st: ISegmentTree, l: Int, r: Int, lim: Int): Int {
    if (r <= l + 1) return 0
    val (x, i) = st[l, r - 1]
    if (x >= lim) return lim
    if (r <= l + 2) return x
    val ans = maxOf(x,
        computeD(a, st, l, i, minOf(lim, 2 * x)),
        computeD(a, st, i + 1, r, minOf(lim, 2 * x)),
    )
    return minOf(ans, lim)
}

private tailrec fun approxSegmentTreeSize(p: Int, tl: Int, tr: Int): Int {
    if (tl >= tr) return p + 1
    return approxSegmentTreeSize(2 * p + 2, (tl + tr + 1) / 2, tr)
}

data class MI(val m: Int, val i: Int)

class ISegmentTree(
    a: IntArray,
) {
    private val n = a.size
    private val t = arrayOfNulls<MI>(approxSegmentTreeSize(0, 0, n - 1))

    init {
        build(a, 0, 0, n - 1)
    }

    private val f0 = MI(inf + 1, -1)

    fun f(x: MI, y: MI): MI =
        if (x.m < y.m) x else y

    private fun build(a: IntArray, p: Int, tl: Int, tr: Int): MI {
        val x = if (tl == tr) {
            MI(a[tl], tl)
        } else {
            val m = (tl + tr) / 2
            f(
                build(a, 2 * p + 1, tl, m),
                build(a, 2 * p + 2, m + 1, tr)
            )
        }
        t[p] = x
        return x
    }

    operator fun get(l: Int, r: Int): MI = get0(0, 0, n - 1, l, r)

    private fun get0(p: Int, tl: Int, tr: Int, l: Int, r: Int): MI {
        if (l > r) return f0
        if (l == tl && r == tr) return t[p]!!
        val m = (tl + tr) / 2
        return f(
            get0(2 * p + 1, tl, m, l, min(r, m)),
            get0(2 * p + 2, m + 1, tr, max(l, m + 1), r)
        )
    }
}