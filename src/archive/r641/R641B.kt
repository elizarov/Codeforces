package archive.r641

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        println(if (solveMedian(n, k, a)) "yes" else "no")
    }
}

fun solveMedian(n: Int, k: Int, a: IntArray): Boolean {
    if (k !in a) return false
    if (n == 1) return true
    if (balLeft(n, k, a)) return true
    a.reverse()
    if (balLeft(n, k, a)) return true
    return false
}

fun balLeft(n: Int, k: Int, a: IntArray): Boolean {
    var bal = 0
    var minBal = 1
    for (i in 0 until n) {
        if (a[i] >= k) {
            if (bal - minBal >= 0) return true
        }
        if (bal < minBal) minBal = bal
        if (a[i] >= k) bal++ else bal--
    }
    return false
}
