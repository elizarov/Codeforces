fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = Array(n) { readln().split(" ").map { it.toInt() - 1 }.toIntArray() }
        val res = solveD(n, m, a)
        println(res.joinToString(" "))
    }
}

private const val M = 10
private val f = IntArray(M + 1).also { f ->
    f[0] = 1
    for (i in 1..M) f[i] = i * f[i - 1]
}
private val bc = IntArray(1 shl M) { it.countOneBits() }
private val ex = Array(1 shl M) { i -> BooleanArray(f[bc[i]]) }

fun solveD(n: Int, m: Int, a: Array<IntArray>): IntArray {
    val ans = IntArray(n)
    val b = IntArray(m)
    for (k in 0 until n) {
        val p = a[k]
        for (j in 0 until m) b[p[j]] = j
        var mask = 0
        var num = 0
        for (j in 0 until m) {
            val i = b[j]
            val bit = 1 shl i
            val rtc = bc[mask and (bit - 1)]
            num = num * (j + 1) + rtc
            mask = mask or bit
            ex[mask][num] = true
        }
    }
    // p given, find q
    // r[j] = q[p[j]]
    for (k in 0 until n) {
        val p = a[k]
        var mask = 0
        var num = 0
        for (j in 0 until m) {
            val i = p[j]
            val bit = 1 shl i
            val rtc = bc[mask and (bit - 1)]
            num = num * (j + 1) + rtc
            mask = mask or bit
            if (ex[mask][num]) {
                ans[k] = j + 1
            } else {
                break
            }
        }
    }
    // clear
    for (k in 0 until n) {
        val p = a[k]
        for (j in 0 until m) b[p[j]] = j
        var mask = 0
        var num = 0
        for (j in 0 until m) {
            val i = b[j]
            val bit = 1 shl i
            val rtc = bc[mask and (bit - 1)]
            num = num * (j + 1) + rtc
            mask = mask or bit
            ex[mask][num] = false
        }
    }
    return ans
}
