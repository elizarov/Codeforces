fun main() {
    val n = readln().toInt()
    val p = IntArray(n)
    val w = IntArray(n)
    repeat(n) { i ->
        val (pi, wi) = readln().split(" ").map { it.toInt() }
        p[i] = pi
        w[i] = wi
    }
    var r = (0..<n).toMutableSet()
    var e0 = 0.0
    var p0 = 1.0
    while (true) {
        var j = -1
        var ej = e0
        var pj = p0
        for (i in r) {
            val e1 = (e0 + w[i] * p0) * p[i] / 100.0
            if (e1 > ej) {
                j = i
                ej = e1
                pj = p0 * p[i] / 100.0
            }
        }
        if (j < 0) break
        r -= j
        e0 = ej
        p0 = pj
    }
    println(e0)
}