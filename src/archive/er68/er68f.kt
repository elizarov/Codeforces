package archive.er68

private const val MOD = 1_000_000_007
private const val INV2 = MOD / 2 + 1

fun main() {
    val s = readLine()!!.split(" ")
    val n = s[0].toInt()
    val tl = s[1].toLong()
    val t = readLine()!!.splitToIntArray()
    val ans = solveCrossWord(n, tl, t)
    println(ans)
}

private infix fun Int.mul(that: Int): Int = ((toLong() * that) % MOD).toInt()

//private val bin = Array()
//
//fun bin(n: Int, k: Int): Int {
//
//}

private fun solveCrossWord(n: Int, tl: Long, t: IntArray): Int {
    var sum = 0L
    var k = 0
    var pow = 1
    var ipow = 1
    while (k < n && sum + k <= tl) {
        sum += t[k]
        k++
        pow = pow mul 2
        ipow = ipow mul INV2
    }
    if (sum > tl) return k - 1
    val ext = tl - sum
    var frac = 0
        

    return 0
}

private fun String.splitToIntArray(): IntArray {
    val n = length
    if (n == 0) return IntArray(0) // EMPTY
    var res = IntArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0
        var neg = false
        var c = get(i) // expecting number, IOOB if there is no number
        if (c == '-') {
            neg = true
            i++
            c = get(i) // expecting number, IOOB if there is no number
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d in 0..9) { "Unexpected character '$c' at $i" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $i" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $i" }
            i++
            if (i >= n) break
            c = get(i)
            if (c == ' ') break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m++] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $i" } }
        if (i >= n) break
        i++
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}