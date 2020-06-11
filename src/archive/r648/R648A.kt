package archive.r648

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val r = IntArray(n)
        val c = IntArray(m)
        repeat(n) { i ->
            val l = readLine()!!.split(" ").map { it.toInt() }
            for (j in 0 until m) {
                if (l[j] != 0) {
                    r[i]++
                    c[j]++
                }
            }
        }
        val e = minOf(r.count { it == 0 }, c.count { it == 0 })
        println(if (e % 2 != 0) "Ashish" else "Vivek")
    }
}