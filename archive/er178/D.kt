fun main() {
    val k = 5800080
    val lp = IntArray(k) // min prime divisor
    val pr = IntArray(400000) // all primes
    var pc = 0 // the number of primes
    for (i in 2 until k) {
        if (lp[i] == 0) {
            pr[pc++] = i // prime found
            lp[i] = i
        }
        var j = 0
        while (j < pc && pr[j] <= lp[i]) {
            val c = pr[j] * i
            if (c >= k) break
            lp[c] = pr[j]
            j++
        }
    }
    val prSum = pr.runningFold(0L) { x, y -> x + y }
    val ans = Array(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.sorted()
        var sum = a.sumOf { it.toLong() }
        var rem = 0
        while (sum < prSum[n - rem]) {
            sum -= a[rem]
            rem++
        }
        rem
    }
    println(ans.joinToString("\n"))
}