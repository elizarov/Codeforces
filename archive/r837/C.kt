import kotlin.math.sqrt

fun main() {
    val ps = primeSieve(31628)
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.toIntArray()
        println(if (solveC(ps, n, a)) "YES" else "NO")
    }
}

fun solveC(ps: PrimeSieve, n: Int, a: IntArray): Boolean {
    for (p in ps.pr) {
        var dc = 0
        for (i in 0 until n) {
            var x = a[i]
            if (x % p == 0) {
                if (++dc >= 2) return true
                do {
                    x /= p
                } while (x % p == 0)
                a[i] = x
            }
        }
    }
    a.sort()
    for (i in 1 until n) if (a[i] > 1 && a[i - 1] == a[i]) return true
    return false
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
