package archive.gr13

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val n = readLine()!!.toInt()
    val fib = IntArray(31)
    fib[0] = 1
    fib[1] = 2
    var fi = 0
    while (fib[fi] < n) {
        if (fi > 0) fib[fi + 1] = fib[fi] + fib[fi - 1]
        fi++
    }
    if (fib[fi] != n) {
        println("NO")
        return
    }
    val g = Graph(n)
    repeat(n - 1) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g.add(u, v)
        g.add(v, u)
    }
    data class PUK(val p: Int, val u: Int, val k: Int)
    val f = DeepRecursiveFunction<PUK, Int> f@{ (p, u, k)  ->
        var sum = 1
        g.from(u) { v ->
            if (v != p) {
                val r = callRecursive(PUK(u, v, k))
                if (r < 0) return@f r
                when {
                    k >= 2 && r == fib[k - 1] -> {
                        val c1 = callRecursive(PUK(u, v, k - 1))
                        val c2 = callRecursive(PUK(v, u, k - 2))
                        return@f if (c1 == FOUND && c2 == FOUND) FOUND else FAIL
                    }
                    k >= 2 && r == fib[k - 2] -> {
                        val c1 = callRecursive(PUK(u, v, k - 2))
                        val c2 = callRecursive(PUK(v, u, k - 1))
                        return@f if (c1 == FOUND && c2 == FOUND) FOUND else FAIL
                    }
                    else -> {
                        sum += r
                    }
                }
            }
        }
        if (k < 2 && sum == fib[k]) FOUND else sum
    }
    val result = f(PUK(-1, 0, fi))
    println(if (result == FOUND) "YES" else "NO")
}

private const val FOUND = -1
private const val FAIL = -2

class Graph(vCap: Int = 16, eCap: Int = vCap * 2) {
    var vCnt = 0
    var eCnt = 0
    var vHead = IntArray(vCap) { -1 }
    var eVert = IntArray(eCap)
    var eNext = IntArray(eCap)

    fun add(v: Int, u: Int, e: Int = eCnt++) {
        ensureVCap(maxOf(v, u) + 1)
        ensureECap(e + 1)
        eVert[e] = u
        eNext[e] = vHead[v]
        vHead[v] = e
    }

    inline fun from(v: Int, action: (u: Int) -> Unit) {
        var e = vHead[v]
        while (e >= 0) {
            action(eVert[e])
            e = eNext[e]
        }
    }

    private fun ensureVCap(vCap: Int) {
        if (vCap <= vCnt) return
        vCnt = vCap
        if (vCap > vHead.size) {
            val newSize = maxOf(2 * vHead.size, vCap)
            vHead = vHead.copyOf(newSize)
        }
    }

    private fun ensureECap(eCap: Int) {
        if (eCap <= eCnt) return
        eCnt = eCap
        if (eCap > eVert.size) {
            val newSize = maxOf(2 * eVert.size, eCap)
            eVert = eVert.copyOf(newSize)
            eNext = eNext.copyOf(newSize)
        }
    }
}
