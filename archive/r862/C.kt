fun main() {
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val k = List(n) { readln().toInt() }.sorted().distinct()
        repeat(m) {
            val (a, b, c) = readln().split(" ").map { it.toInt() }
            if (c <= 0) {
                println("NO")
            } else {
                val cc = b.toLong() * b - 4L * a * c
                fun eval(i: Int): Long = k[i].toLong() * k[i] - 2L * b * k[i] + cc
                var i = 0
                var ie = eval(i)
                var j = k.lastIndex
                var je = eval(j)
                while (j - i > 1) {
                    val m1 = i + (j - i + 1) / 3
                    val m2 = j - (j - i + 1) / 3
                    val m1e = eval(m1)
                    val m2e = eval(m2)
                    if (m1e < m2e) {
                        j = m2
                        je = m2e
                    } else {
                        i = m1
                        ie = m1e
                    }
                }
                if (minOf(ie, je) >= 0) {
                    println("NO")
                } else {
                    println("YES")
                    println(k[if (ie < je) i else j])
                }
            }
        }
    }
}