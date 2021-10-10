package archive.gf1

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val b = readLine()!!.split(" ").map { it.toInt() }
        val c = readLine()!!.split(" ").map { it.toInt() }
        val d = IntArray(n)
        d[0] = a[0]
        for (i in 1 until n - 1) {
            d[i] = listOf(a[i], b[i], c[i]).minus(d[i - 1]).first()
        }
        d[n - 1] = listOf(a[n - 1], b[n - 1], c[n - 1]).minus(d[n - 2]).minus(d[0]).first()
        println(d.joinToString(" "))
    }
}