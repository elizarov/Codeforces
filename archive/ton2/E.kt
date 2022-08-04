import java.math.BigInteger

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val g = List(n) { ArrayList<Int>() }
        val a = readln().split(" ").map { it.toLong() }
        repeat(m) {
            val (x, y) = readln().split(" ").map { it.toInt() - 1 }
            g[x] += y
        }
        val f = BooleanArray(n)
        val top = ArrayList<Int>()
        val dfs = DeepRecursiveFunction<Int,Unit> { x ->
            f[x] = true
            for (y in g[x]) {
                if (!f[y]) callRecursive(y)
            }
            top += x
        }
        for (x in 0 until n) if (!f[x]) dfs(x)
        top.reverse()
        val lr = a.map { listOf(LR(BigInteger.ZERO, it.toBigInteger())) }.toMutableList()
        for (x in top) {
            for (y in g[x]) {
                lr[y] = mergeLR(lr[y], lr[x].map { LR(it.l + BigInteger.ONE, it.r + BigInteger.ONE) })
            }
        }
        val z = top.last()
        println((lr[z].lastOrNull()?.r ?: BigInteger.ZERO) % 998244353L.toBigInteger())
    }
}

data class LR(val l: BigInteger, val r: BigInteger)

fun mergeLR(a: List<LR>, b: List<LR>): List<LR> {
    val res = ArrayList<LR>()
    var i = 0
    var j = 0
    var lc = BigInteger.ZERO!!
    var rc = BigInteger.ZERO!!
    while (i < a.size || j < b.size) {
        val next = if (i < a.size && j < b.size && a[i].l <= b[j].l || j == b.size)
            a[i].also { i++ } else
            b[j].also { j++ }
        if (rc < next.l) {
            if (lc < rc) res.add(LR(lc, rc))
            lc = next.l
            rc = next.r
        } else {
            val sum = rc - lc + next.r - next.l
            rc = lc + sum
        }
    }
    if (lc < rc) res.add(LR(lc, rc))
    return res
}

