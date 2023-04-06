fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val k = List(n) { readln().toInt() }.distinct().sorted()
        repeat(m) {
            val (a, b, c) = readln().split(" ").map { it.toInt() }
            if (c <= 0) {
                println("NO")
            } else {
                val cc = b.toLong() * b - 4L * a * c
                fun eval(i: Int): Long = k[i].toLong() * k[i] - 2L * b * k[i] + cc
                var l = 0
                var r = k.lastIndex
                while (l < r) {
                    val mid = (l + r) / 2
                    if (eval(mid) < eval(mid + 1)) {
                        r = mid
                    } else {
                        l = mid + 1
                    }
                }
                if (eval(l) >= 0) {
                    println("NO")
                } else {
                    println("YES")
                    println(k[l])
                }
            }
        }
    }
}