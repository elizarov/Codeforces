@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val c = readLine()!!.toCharArray()
    val l = IntArray(n)
    val r = IntArray(n)
    repeat(n) { i ->
        val (li, ri) = readLine()!!.split(" ").map { it.toInt() - 1 }
        l[i] = li
        r[i] = ri
    }
    val nextIdx = IntArray(n)
    data class IK(val i: Int, val k: Int)
    val fillNext = DeepRecursiveFunction<IK, Int> { (i, k) ->
        if (r[i] >= 0) {
            nextIdx[i] = callRecursive(IK(r[i], k))
        } else {
            nextIdx[i] = k
        }
        if (l[i] >= 0) {
            callRecursive(IK(l[i], i))
        } else {
            i
        }
    }
    var p = fillNext(IK(0, n))
    val next = CharArray(n)
    while (p < n) {
        val cur = c[p]
        var q = nextIdx[p]
        while (q < n && c[q] == cur) q = nextIdx[q]
        val nc = if (q < n) c[q] else Char(0)
        while (p < n && c[p] == cur) {
            next[p] = nc
            p = nextIdx[p]
        }
    }
    val dup = BooleanArray(n)
    val solve = DeepRecursiveFunction<IK, Int> { (i, k) ->
        if (k == 0) return@DeepRecursiveFunction 0
        val dl = if (l[i] >= 0) callRecursive(IK(l[i], k - 1)) else 0
        val di = if (dl > 0 || c[i] < next[i]) 1 else 0
        val dr = if (di > 0 && r[i] >= 0) callRecursive(IK(r[i], k - 1 - dl)) else 0
        if (di > 0) dup[i] = true
        dl + di + dr
    }
    solve(IK(0, k))
    val ans = StringBuilder()
    val build = DeepRecursiveFunction<Int, Unit> { i ->
        if (l[i] >= 0) callRecursive(l[i])
        ans.append(c[i])
        if (dup[i]) ans.append(c[i])
        if (r[i] >= 0) callRecursive(r[i])
    }
    build(0)
    println(ans)
}