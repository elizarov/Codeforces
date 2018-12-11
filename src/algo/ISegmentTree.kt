package algo

import kotlin.math.*

private tailrec fun approxSegmentTreeSize(p: Int, tl: Int, tr: Int): Int {
    if (tl >= tr) return p + 1
    return approxSegmentTreeSize(2 * p + 2, (tl + tr + 1) / 2, tr)
}

interface IFunction {
    operator fun invoke(x: Int, y: Int): Int
}

inline fun ISegmentTree(a: IntArray, f0: Int, crossinline f: (x: Int, y: Int) -> Int) =
    ISegmentTree(a, f0, object : IFunction {
        override fun invoke(x: Int, y: Int): Int = f(x, y)
    })

class ISegmentTree(
    a: IntArray,
    private val f0: Int,
    private val f: IFunction
) {
    private val n = a.size
    private val t = IntArray(approxSegmentTreeSize(0, 0, n - 1))

    init {
        build(a, 0, 0, n - 1)
    }

    private fun build(a: IntArray, p: Int, tl: Int, tr: Int): Int {
        val x = if (tl == tr) {
            a[tl]
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
                               
    operator fun get(l: Int, r: Int): Int = get0(0, 0, n - 1, l, r)

    private fun get0(p: Int, tl: Int, tr: Int, l: Int, r: Int): Int {
        if (l > r) return f0
        if (l == tl && r == tr) return t[p]
        val m = (tl + tr) / 2
        return f(
            get0(2 * p + 1, tl, m, l, min(r, m)),
            get0(2 * p + 2, m + 1, tr, max(l, m + 1), r)
        )
    }

    operator fun set(i: Int, x: Int) = set0(0, 0, n - 1, i, x)

    private fun set0(p: Int, tl: Int, tr: Int, i: Int, x: Int): Int {
        val y = if (tl == tr) {
            x
        } else {
            val m = (tl + tr) / 2
            if (i <= m) {
                set0(2 * p + 1, tl, m, i, x)
            } else {
                set0(2 * p + 2, m + 1, tr, i, x)
            }
            f(t[2 * p + 1], t[2 * p + 2])
        }
        t[p] = y
        return y
    }
}