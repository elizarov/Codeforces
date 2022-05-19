fun main() {
    var pc = 1
    val p = IntArray(1000)
    p[0] = 2
    fun isPrime(c: Int): Boolean {
        for (j in 0 until pc) {
            if (p[j] * p[j] > c) return true
            if (c % p[j] == 0) return false
        }
        return true
    }
    for (c in 3 until 1001 step 2) {
        if (isPrime(c)) p[pc++] = c
    }
    repeat(readLine()!!.toInt()) {
        val (n, e) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }
        var ans = 0L
        for (i in 0 until e) {
            var ocl = 0L
            var ocr = 0L
            var ps = false
            fun flush() {
                if (ps) {
                    ans += (ocl + 1) * (ocr + 1) - 1
                    ps = false
                }
            }
            for (j in i until n step e) {
                when {
                    a[j] == 1 -> ocr++
                    isPrime(a[j]) -> {
                        flush()
                        ps = true
                        ocl = ocr
                        ocr = 0
                    }
                    else -> {
                        flush()
                        ocl = 0
                        ocr = 0
                    }
                }
            }
            flush()
        }
        println(ans)
    }
}