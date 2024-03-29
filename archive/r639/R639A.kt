package archive.r639

fun main() {
    repeat (readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        val c = IntArray(n)
        for (i in 0 until n) {
            c[(i + a[i]) mod n]++
        }
        println(if (c.any { it != 1}) "NO" else "YES")
    }
}

infix fun Int.mod(n: Int): Int {
    val t = this % n
    return if (t >= 0) t else n + t
}