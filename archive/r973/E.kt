fun main() {
    fun gcd(x: Int, y: Int): Int =
        if (y == 0) x else gcd(y, x % y)

    val ans = List(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.sorted()
        if (n == 1) {
            return@List a[0]
        }
        var gall = a[0]
        for (i in 1..<n) {
            gall = gcd(gall, a[i])
        }
        var best = Int.MAX_VALUE
        var bi = -1
        var bj = -1
        for (i in 1..<n) {
            val t = a[0] + gcd(a[i], a[0])
            if (t < best) {
                best = t
                bi = i
                bj = 0
            }
        }
//        for (i in 0..<n) {
//            for (j in i + 1..<n) {
//                val t = a[i] + gcd(a[i], a[j])
//                if (t < best) {
//                    best = t
//                    bi = i
//                    bj = j
//                }
//            }
//        }
        var sum = best.toLong()
        var g = gcd(a[bi], a[bj])
        val rest = a.indices.toMutableSet()
        rest -= bi
        rest -= bj
        while (rest.isNotEmpty() && g > gall) {
            best = Int.MAX_VALUE
            for (i in rest) {
                val t = gcd(g, a[i])
                if (t < best) {
                    best = t
                    bi = i
                }
            }
            sum += best
            g = best
            rest -= bi
        }
        sum += rest.size * gall.toLong()
        sum
    }
    println(ans.joinToString("\n"))
}