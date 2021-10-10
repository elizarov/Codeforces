package archive.r649

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val p = readLine()!!.split(" ").map { it.toInt() }
        val f = BooleanArray(n)
        f[0] = true
        f[n - 1] = true
        for (i in 1 until n - 1) {
            val a = p[i - 1]
            val b = p[i]
            val c = p[i + 1]
            f[i] = (b > a && b > c) || (b < a && b < c)
        }
        println(f.count { it })
        println(p.withIndex().filter { f[it.index] }.joinToString(" ") { it.value.toString() })
    }
}
