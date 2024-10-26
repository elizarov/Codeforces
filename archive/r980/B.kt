import kotlin.math.max
import kotlin.math.min

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        val b = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
        val s = LongArray(n)
        s[0] = a[0].toLong()
        for (i in 1..<n) s[i] = s[i - 1] + a[i]
        val t = ISegmentTree(s)
        for (i in n - 1 downTo 0) {
            val j = b[i]
            if (j <= i) continue
            val k = t[j]
            t.update(i, j - 1, k - a[i])
        }
        println(t[0])
    }
}

private tailrec fun approxSegmentTreeSize(p: Int, tl: Int, tr: Int): Int {
    if (tl >= tr) return p + 1
    return approxSegmentTreeSize(2 * p + 2, (tl + tr + 1) / 2, tr)
}

class ISegmentTree(
    s: LongArray,
) {
    private val n = s.size
    private val ts = approxSegmentTreeSize(0, 0, n - 1)
    private val u = LongArray(ts)

    init {
        build(s, 0, 0, n - 1)
    }

    private fun build(s: LongArray, p: Int, tl: Int, tr: Int) {
        if (tl == tr) {
            u[p] = s[tl].toLong()
        } else {
            val m = (tl + tr) / 2
            build(s, 2 * p + 1, tl, m)
            build(s, 2 * p + 2, m + 1, tr)
        }
    }

    operator fun get(i: Int): Long = get0(0, 0, n - 1, i)

    private fun get0(p: Int, tl: Int, tr: Int, i: Int): Long {
        if (tl == tr) {
            check(tl == i)
            return u[p]
        }
        val m = (tl + tr) / 2
        val y =  if (i <= m) get0(2 * p + 1, tl, m, i) else get0(2 * p + 2, m + 1, tr, i)
        return maxOf(y, u[p])
    }

    fun update(l: Int, r: Int, x: Long) = update0(0, 0, n - 1, l, r, x)

    private fun update0(p: Int, tl: Int, tr: Int, l: Int, r: Int, x: Long) {
        if (l > r) return
        val y = maxOf(u[p], x)
        if (l == tl && r == tr) {
            u[p] = y
            return
        }
        val m = (tl + tr) / 2
        update0(2 * p + 1, tl, m, l, min(r, m), y)
        update0(2 * p + 2, m + 1, tr, max(l, m + 1), r, y)
    }
}