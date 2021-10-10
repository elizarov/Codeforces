package archive.r655

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        var best = Int.MAX_VALUE
        var aa = -1
        for (a in 1 until n) {
            val b = n - a
            val k = a * b / gcd(a, b)
            if (k < best) {
                best = k
                aa = a
            }
        }
        println("$aa ${n - aa}")
    }
}

tailrec fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)