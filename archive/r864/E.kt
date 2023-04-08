import kotlin.math.max
import kotlin.math.min

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val a = readln().split(" ").map { it.toInt() }.toIntArray()

    val mx = a.max()
    val ps = primeSieve(mx + 1)
    val pws = IntArray(mx + 1)
    val rms = IntArray(mx + 1)
    val phi = IntArray(mx + 1)
    phi[0] = 1
    phi[1] = 1
    rms[0] = 1
    pws[0] = 1
    for (i in 2..mx) {
        val p = ps.lp[i]
        val r = ps.mp[i]
        val prv = if (ps.lp[r] == p) pws[r] else 1
        val rem = if (ps.lp[r] == p) rms[r] else r
        rms[i] = rem
        val pwr = prv * p
        pws[i] = pwr
        phi[i] = (pwr - prv) * phi[rem]
    }

    fun common(x0: Int, y0: Int): Int {
        if (x0 == 0) return y0
        if (y0 == 0) return x0
        var x = x0
        var y = y0
        while (x != y) {
            if (x < y) y = phi[y] else x = phi[x]
        }
        return x
    }

    fun dist(x0: Int, y0: Int): Int {
        require(x0 >= y0)
        var x = x0
        var dist = 0
        while (x > y0) {
            x = phi[x]
            dist++
        }
        check(x == y0)
        return dist
    }

    val st = ISegmentTree(
        a,
        fCommon = { x, y -> common(x, y) },
        fDist = { x, y -> dist(x, y) }
    )

    val ans = ArrayList<Int>()

    repeat(m) {
        val (t, l, r) = readln().split(" ").map { it.toInt() - 1}
        when (t) {
            0 -> {
                st.setPush(l, r, st.getCommon(l, r))
            }
            1 -> {
                ans += st.getCost(l, r)
            }
        }
    }

    println(ans.joinToString("\n"))
}

private tailrec fun approxSegmentTreeSize(p: Int, tl: Int, tr: Int): Int {
    if (tl >= tr) return p + 1
    return approxSegmentTreeSize(2 * p + 2, (tl + tr + 1) / 2, tr)
}

fun interface IFunction {
    operator fun invoke(x: Int, y: Int): Int
}

class ISegmentTree(
    a: IntArray,
    private val fCommon: IFunction,
    private val fDist: IFunction
) {
    private val n = a.size
    private val tSize = approxSegmentTreeSize(0, 0, n - 1)
    private val tCommon = IntArray(tSize)
    private val tCost = IntArray(tSize)
    private val tPush = IntArray(tSize)

    init {
        build(a, 0, 0, n - 1)
    }

    private fun build(a: IntArray, p: Int, tl: Int, tr: Int) {
        if (tl == tr) {
            tCommon[p] = a[tl]
            tCost[p] = 0
        } else {
            val m = (tl + tr) / 2
            val p1 = 2 * p + 1
            val p2 = 2 * p + 2
            build(a, p1, tl, m)
            build(a, p2, m + 1, tr)
            val c1 = tCommon[p1]
            val c2 = tCommon[p2]
            val c = fCommon(c1, c2)
            val d1 = fDist(c1, c)
            val d2 = fDist(c2, c)
            tCommon[p] = c
            tCost[p] = tCost[p1] + tCost[p2] + (m - tl + 1) * d1 + (tr - m) * d2
        }
    }

    fun getCommon(l: Int, r: Int): Int = getCommon0(0, 0, n - 1, l, r)

    private fun getCommon0(p: Int, tl: Int, tr: Int, l: Int, r: Int): Int {
        if (l > r) return 0
        val push = tPush[p]
        if (l == tl && r == tr) {
            return fCommon(tCommon[p], push)
        }
        val m = (tl + tr) / 2
        val p1 = 2 * p + 1
        val p2 = 2 * p + 2
        if (push != 0) {
            tPush[p] = 0
            tCost[p] += (tr - tl + 1) * fDist(tCommon[p], push)
            tPush[p1] = push
            tPush[p2] = push
        }
        return fCommon(
            getCommon0(p1, tl, m, l, min(r, m)),
            getCommon0(p2, m + 1, tr, max(l, m + 1), r)
        )
    }

    fun getCost(l: Int, r: Int, common: Int = getCommon(l, r)): Int = getCost0(0, 0, n - 1, l, r, common)

    private fun getCost0(p: Int, tl: Int, tr: Int, l: Int, r: Int, common: Int): Int {
        if (l > r) return 0
        if (l == tl && r == tr) {
            return tCost[p] + (r - l + 1) * fDist(tCommon[p], common)
        }
        val m = (tl + tr) / 2
        val cost1 = getCost0(2 * p + 1, tl, m, l, min(r, m), common)
        val cost2 = getCost0(2 * p + 2, m + 1, tr, max(l, m + 1), r, common)
        return cost1 + cost2
    }

    fun setPush(l: Int, r: Int, push: Int) = setPush0(0, 0, n - 1, l, r, push)

    private fun setPush0(p: Int, tl: Int, tr: Int, l: Int, r: Int, push: Int) {
        if (l > r) return
        if (l == tl && r == tr) {
            tPush[p] = push
            return
        }
        val m = (tl + tr) / 2
        setPush0(2 * p + 1, tl, m, l, min(r, m), push)
        setPush0(2 * p + 2, m + 1, tr, max(l, m + 1), r, push)
    }
}

class PrimeSieve(
    val lp: IntArray, // min prime divisor
    val mp: IntArray, // the other multiplier
    val pr: IntArray  // all primes
)

// finds all primes and divisors up to n (exclusive)
fun primeSieve(n: Int): PrimeSieve {
    val lp = IntArray(n) // min prime divisor
    val mp = IntArray(n) // the other multiplier
    val pr = IntArray(n) // all primes
    var pc = 0 // the number of primes
    for (i in 2 until n) {
        if (lp[i] == 0) {
            pr[pc++] = i // prime found
            lp[i] = i
        }
        var j = 0
        while (j < pc && pr[j] <= lp[i]) {
            val c = pr[j] * i
            if (c >= n) break
            lp[c] = pr[j]
            mp[c] = i
            j++
        }
    }
    return PrimeSieve(lp, mp, pr.copyOf(pc))
}