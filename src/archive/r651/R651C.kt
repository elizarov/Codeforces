package archive.r651

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        println(if (solveC(n)) "Ashishgup" else "FastestFinger")
    }
}

fun solveC(n: Int): Boolean = 
    when {
        n == 1 -> false
        n == 2 -> true
        n and (n - 1) == 0 -> false
        n % 2 != 0 -> true
        n % 4 == 0 -> true
        else -> {
            val r = n / 2
            var f = 3
            var ok = false
            while (f * f <= r) {
                if (r % f == 0) {
                    ok = true
                    break
                }
                f += 2
            }
            ok
        }
    }


