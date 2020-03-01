package archive.r625

fun main() {
    val n = readLine()!!.toInt()
    val b = readLine()!!.split(" ").map { it.toInt() }
    val s = LongArray(1_000_000)
    for (i in 0 until n) {
        val x = b[i] - i + n
        s[x] = s[x] + b[i]
    }
    println(s.max())
}