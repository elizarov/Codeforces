package archive.r707

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val a = List(n) { readLine()!!.split(" ").map { it.toInt() } }
    val b = List(n) { readLine()!!.split(" ").map { it.toInt() } }
    val result = solveC(n, m, a, b)
    if (result == null) {
        println(-1)
    } else {
        println(result.size)
        println(result.joinToString(" ") { (it + 1).toString() })
    }
}

fun solveC(n: Int, m: Int, a: List<List<Int>>, b: List<List<Int>>): List<Int>? {
    val u = BooleanArray(m)
    val gs = BooleanArray(n)
    gs[0] = true
    var uc = 0
    val result = ArrayList<Int>()
    val bc = List(m) { IntArray(n) }
    val fc = IntArray(m)
    for (j in 1 until n) {
        for (i in 0 until m) {
            bc[i][j] = b[j][i] - b[j - 1][i]
            if (bc[i][j] < 0) fc[i]++
        }
    }
    while (uc < m) {
        val sl = ArrayList<Int>()
        for (i in 0 until m) if (!u[i]) {
            if (fc[i] == 0) {
                u[i] = true
                uc++
                result.add(i)
                sl.add(i)
            }
        }
        if (sl.isEmpty()) break
        for (j in 1 until n) {
            if (gs[j]) continue
            var same = true
            for (i in sl) {
                if (bc[i][j] != 0) {
                    same = false
                    break
                }
            }
            if (!same) {
                gs[j] = true
                for (i in 0 until m) if (!u[i]) {
                    if (bc[i][j] < 0) fc[i]--
                }
            }
        }
    }

    class Cnt(val l: List<Int>, var c: Int = 0)
    val ag = a.withIndex().groupBy({ it.value }, { it.index }).entries
        .associateBy({ it.key }, { Cnt(it.value) })

    var prevK = -1
    for (j in 0 until n) {
        if (gs[j]) prevK = -1
        val br = b[j]
        val ac = ag[br]?.takeIf { it.c < it.l.size } ?: return null
        val k = ac.l[ac.c++]
        if (k < prevK) return null
        prevK = k
    }

    result.reverse()
    return result
}

