package archive.y2021.deltixSummer

fun main() {
    val n = readLine()!!.toInt()
    val c = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    println(solveC(n, c))
}

fun solveC(n: Int, c: IntArray): Long {
    var ans = 0L
    for (i in 0 until n step 2) {
        var tb = 0L
        var mb = 0L
        for (j in i + 1 until n step 2) {
            val w = minOf(0, mb + 1)
            var ri = c[i] + w
            if (ri > 0) {
                var bb = -w + tb
                if (bb < 0) {
                    ri += bb
                    bb = 0
                }
                var rj = c[j].toLong()
                if (bb > 0) {
                    rj -= bb
                }
                if (ri > 0 && rj > 0) {
                    ans += minOf(ri, rj)
                }
            }
            tb -= c[j]
            mb = minOf(mb, tb)
            if (j + 1 < n) tb += c[j + 1]
        }
    }
    return ans
}