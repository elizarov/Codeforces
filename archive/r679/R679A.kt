package archive.r679

fun main() {
    val aa = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val n = readLine()!!.toInt()
    val bb = readLine()!!.split(" ").map { it.toInt() }
    var ans = Int.MAX_VALUE
    for (a0 in aa) {
        val x0 = bb[0] - a0
        var u1 = Int.MAX_VALUE
        for (b in bb) {
            for (a in aa) {
                if (b - a >= x0) u1 = minOf(u1, b - a)
            }
        }
//        ans = minOf(ans, d - c)
    }
    println(ans)
}