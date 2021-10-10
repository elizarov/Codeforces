package archive.kh4

fun main() {
    val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
    val inf = Int.MAX_VALUE - 1
    val s = IntArray(n) { inf }
    s[k - 1] = 0
    repeat(m) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        val sx = s[x]
        val sy = s[y]
        s[x] = minOf(sx + 1, sy)
        s[y] = minOf(sy + 1, sx)
    }
    println(s.joinToString(" ") { (if (it == inf) -1 else it).toString() })
}