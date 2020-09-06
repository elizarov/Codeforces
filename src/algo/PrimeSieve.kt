package algo

import kotlin.time.*

class PrimeSieve(
    val lp: IntArray, // min prime divisor
    val mp: IntArray, // the other multiplier
    val pr: IntArray  // all primes
)

// fast factorization without division (factors in non-decreasing order, smallest factor first)
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

//fun main() {
//    val n = 10_000_000
//    val (ps, time) = measureTimedValue { primeSieve(n) }
//    println("Found ${ps.pr.size} primes up to $n in $time")
//    println("Primes: ${ps.pr.take(30)}")
//    val x = 9_999_999
//    println("Factors of $x:")
//    ps.factors(x) {
//        println(it)
//    }
//}