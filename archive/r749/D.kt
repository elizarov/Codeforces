fun main() {
    val n = readLine()!!.toInt()
    val aq = IntArray(n)
    val prev = IntArray(n + 1)
    for (i in 1..n) {
        aq.fill(1)
        aq[i - 1] = 2
        println("? ${aq.joinToString(" ")}")
        val k = readLine()!!.toInt()
        if (k < i) prev[k] = i
    }
    for (i in 1..n) {
        aq.fill(2)
        aq[i - 1] = 1
        println("? ${aq.joinToString(" ")}")
        val k = readLine()!!.toInt()
        if (k < i) prev[i] = k
    }
    val p = IntArray(n + 1) { -1 }
    p[0] = 0
    var unk = n
    while (unk > 0) {
        for (i in 1..n) {
            if (p[i] < 0 && p[prev[i]] >= 0) {
                p[i] = p[prev[i]] + 1
                unk--
            }
        }
    }
    println("! ${p.drop(1).joinToString(" ")}")
}
