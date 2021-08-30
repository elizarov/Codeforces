package archive.y2021.deltixSummer

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        println(solveB(n, a))
    }
}

private fun solveB(n: Int, a: IntArray): Int {
    val e = a.count { it % 2 == 0 }
    val o = n - e
    return when (e) {
        o -> minOf(solveB(n, a, 0), solveB(n, a, 1))
        o + 1 -> solveB(n, a, 0)
        o - 1 -> solveB(n, a, 1)
        else -> -1
    }
}

private fun solveB(n: Int, a: IntArray, p: Int): Int {
    var ie = -1
    var io = -1
    val t = IntArray(n)
    for (i in 0 until n) {
        if ((i + p) % 2 == 0) {
            ie++
            while (a[ie] % 2 != 0) ie++
            t[i] = ie
        } else {
            io++
            while (a[io] % 2 == 0) io++
            t[i] = io
        }
    }
    val res = invGet(t, 0, n)
    return res.sumOf { it.inverses }
}

data class Inv(val item: Int, val inverses: Int)

fun invGet(a: IntArray, l: Int, r: Int): List<Inv> {
    if (l == r - 1) return listOf(Inv(a[l], 0))
    val m = (l + r) / 2
    val ls1 = invGet(a, l, m)
    val ls2 = invGet(a, m, r)
    var it1 = 0
    var it2 = 0
    val res = ArrayList<Inv>()
    while (it1 < ls1.size && it2 < ls2.size) {
        if (ls1[it1].item < ls2[it2].item) {
            res += ls1[it1++]
        } else {
            res += Inv(ls2[it2].item, ls2[it2++].inverses + ls1.size - it1)
        }
    }
    while (it1 < ls1.size) {
        res += ls1[it1++]
    }
    while (it2 < ls2.size) {
        res += ls2[it2++]
    }
    return res
}
