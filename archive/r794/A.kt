fun main() {
    val s = List(readLine()!!.toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.sorted()
        val b = IntArray(n + 2)
        for (i in 0 until n / 2) b[2 * i + 1] = a[i]
        for (i in n / 2 until n) b[2 * (i - n / 2) + 2] = a[i]
        b[0] = b[n]
        b[n + 1] = b[1]
        val ok = (1..n).all { i -> b[i] < b[i - 1] && b[i] < b[i + 1] || b[i] > b[i - 1] && b[i] > b[i + 1]}
        if (ok) "YES\n${b.slice(1..n).joinToString(" ")}" else "NO"
    }
    println(s.joinToString("\n"))
}