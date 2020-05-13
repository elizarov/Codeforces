package archive.r641

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }
    println(solveGcdLcm(n, a))
}

fun solveGcdLcm(n: Int, a: List<Int>): Long {
    val ps = primeSieve(a.max()!! + 1)
    val pc = ps.pr.size
    val min0 = IntArray(pc) { Int.MAX_VALUE }
    val min1 = IntArray(pc) { Int.MAX_VALUE }
    val cc = IntArray(pc)
    fun reg(i: Int, c: Int) {
        if (c <= min0[i]) {
            min1[i] = min0[i]
            min0[i] = c
        } else if (c < min1[i]) {
            min1[i] = c
        }
        cc[i]++
    }
    for (x in a) {
        ps.factors(x) { i, c ->
            reg(i, c)
        }
    }
    var res = 1L
    for ((i, p) in ps.pr.withIndex()) {
        repeat(minOf(2, n - cc[i])) { reg(i, 0) }
        repeat(min1[i]) { res *= p }
    }
    return res
}

class PrimeSieve(
    val lp: IntArray, // min prime divisor
    val mp: IntArray, // the other multiplier
    val pr: IntArray  // all primes
)

// fast factorization without division (factors in non-decreasing order, smallest factor first)
inline fun PrimeSieve.factors(x: Int, f: (Int, Int) -> Unit) {
    var cur = x
    var last = -1
    var cnt = 0
    while (cur > 1) {
        if (lp[cur] == last) {
            cnt++
        } else {
            if (cnt > 0) f(last, cnt)
            last = lp[cur]
            cnt = 1
        }
        cur = mp[cur]
    }
    if (cnt > 0) f(last, cnt)
}

// finds all primes and divisors up to n (exclusive)
fun primeSieve(n: Int): PrimeSieve {
    val lp = IntArray(n) { -1 } // min prime divisor index
    val mp = IntArray(n) // the other multiplier
    val pr = IntArray(n) // all primes
    var pc = 0 // the number of primes
    for (i in 2 until n) {
        if (lp[i] < 0) {
            pr[pc] = i // prime found
            lp[i] = pc
            pc++
        }
        var j = 0
        while (j < pc && j <= lp[i]) {
            val c = pr[j] * i
            if (c >= n) break
            lp[c] = j
            mp[c] = i
            j++
        }
    }
    return PrimeSieve(lp, mp, pr.copyOf(pc))
}
