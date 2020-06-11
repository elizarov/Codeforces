package archive.r648

fun main() {
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() - 1 }
    val b = readLine()!!.split(" ").map { it.toInt() - 1 }
    val p = IntArray(n)
    for (i in 0 until n) p[b[i]] = i
    val d = List(n) { i -> (p[a[i]] - i + n) % n }
    val c = d.groupingBy { it }.eachCount().values.max()!!
    println(c)
}