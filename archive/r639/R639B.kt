package archive.r639

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(n) { readLine()!!.toCharArray() }
    println(solveMagnet(n, m, a))
}

fun solveMagnet(n: Int, m: Int, a: Array<CharArray>): Int {
    val re = RC()
    val ce = RC()
    if (!good(n, m, a, re)) return -1
    if (!good(m, n, trans(n, m, a), ce)) return -1
    if (re.hasEmpty != ce.hasEmpty) return -1
    var cnt = 0
    val qi = IntArray(n * m)
    val qj = IntArray(n * m)
    var qh = 0
    var qt = 0
    fun visit(i: Int, j: Int) {
        if (i !in 0 until n || j !in 0 until m || a[i][j] != '#') return
        a[i][j] = '*'
        qi[qt] = i
        qj[qt] = j
        qt++
    }
    for (i in 0 until n) for (j in 0 until m) {
        if (a[i][j] == '#') {
            cnt++
            visit(i, j)
            while (qh < qt) {
                val ci = qi[qh]
                val cj = qj[qh]
                qh++
                visit(ci + 1, cj)
                visit(ci - 1, cj)
                visit(ci, cj + 1)
                visit(ci, cj - 1)
            }
        }
    }
    return cnt
}

fun trans(n: Int, m: Int, a: Array<CharArray>): Array<CharArray> =
    Array(m) { j -> CharArray(n) { i -> a[i][j] } }

class RC(var hasEmpty: Boolean = false)

fun good(n: Int, m: Int, a: Array<CharArray>, rc: RC): Boolean {
    for (i in 0 until n) {
        val r = a[i]
        val j1 = r.indexOfFirst { it == '#' }
        if (j1 < 0) {
            rc.hasEmpty = true
        } else {
            val j2 = r.indexOfLast { it == '#' }
            if (!r.slice(j1..j2).all { it == '#' }) return false
        }
    }
    return true
}
