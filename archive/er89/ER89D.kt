package archive.er89

fun main() {
    val n = readLine()!!.toInt()
    val a = readIntArray()
    val ps = primeSieve(a.maxOrNull()!! + 1)
    val d1 = IntArray(n) { -1 }
    val d2 = IntArray(n) { -1 }
    for ((i, x) in a.withIndex()) {
        var r0 = 0
        var r1 = 0
        var rr = 1
        var p = 1
        var pk = 1
        ps.factors(x) { f ->
            if (f == p) {
                pk *= f
            } else {
                when {
                    r0 == 0 -> r0 = f
                    r1 == 0 && f > r0 -> r1 = f
                }
                rr *= pk
                p = f
                pk = f
            }
        }
        if (rr % 2 == 0) {
            d1[i] = rr
            d2[i] = pk
        } else if (r1 > 0) {
            d1[i] = r0
            d2[i] = r1
        }
    }
    println(d1.joinToString(" "))
    println(d2.joinToString(" "))
}

class PrimeSieve(
    val lp: IntArray, // min prime divisor
    val mp: IntArray, // the other multiplier
    val pr: IntArray  // all primes
)

// fast factorization without division (archive.er89.factors in non-decreasing order, smallest factor first)
inline fun PrimeSieve.factors(x: Int, f: (Int) -> Unit) {
    var cur = x
    while (cur > 1) {
        f(lp[cur])
        cur = mp[cur]
    }
}

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

fun readIntArray(): IntArray {
    val input = System.`in`
    var m = 0
    val eol = '\n'
    var c = eol
    fun nextChar() {
        var b = input.read()
        if (b == '\r'.toInt()) b = input.read()
        c = if (b < 0) eol else b.toChar()
    }
    nextChar()
    var res = IntArray(4)
    while (c != eol) {
        var cur = 0
        var neg = false
        if (c == '-') {
            neg = true
            nextChar()
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d in 0..9) { "Unexpected character '$c' at $m" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $m" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $m" }
            nextChar()
            if (c == ' ' || c == eol) break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $m" } }
        m++
        while (c == ' ') nextChar()
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}

fun String.splitToIntArray(): IntArray {
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