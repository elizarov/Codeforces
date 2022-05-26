import kotlin.math.*

private const val inf = Int.MAX_VALUE / 2

fun main() {
    val n = readln().toInt()
    data class Pt(val x: Int, val y: Int)
    fun Pt.up(): Pt = Pt(x, y + 1)
    fun Pt.rt(): Pt = Pt(x + 1, y)
    val pt = ArrayList<Pt>()
    repeat(n - 1) {
        val (y1, x1, y2, x2) = readln().split(" ").map { it.toInt() - 1 }
        pt += Pt(x1, y1)
        pt += Pt(x2, y2)
    }
    pt += pt[2 * n - 4].up()
    pt += pt[2 * n - 3].rt()
    val sq = sqrt(n.coerceAtLeast(4).toDouble())
    data class Part(val l: Int, val r: Int) {
        val ofs = l * 2
        val m = (r - l) * 2
    }
    val ps = ArrayList<Part>()
    var cur = sq
    var last = cur.roundToInt()
    ps += Part(0, last)
    while (last < n) {
        cur += sq
        val next = cur.roundToInt().coerceAtMost(n)
        ps += Part(last, next)
        last = next
    }
    fun layer(p: Pt): Int = maxOf(p.x, p.y)
    fun z(p: Pt): Int = p.x - p.y
    fun ld(p1: Pt, p2: Pt): Int {
        require(layer(p1) == layer(p2))
        return abs(z(p1) - z(p2))
    }
    val id = ps.map { p ->
        val ofs = p.ofs
        val m = p.m
        val d = Array(m) { IntArray(m) { inf } }
        for (i in 0 until m) d[i][i] = 0
        for (i in 0 until m step 2) {
            dset(d, i, i + 1, ld(pt[i + ofs], pt[i + ofs + 1]))
            if (i < m - 2) {
                dset(d, i, i + 2, ld(pt[i + ofs].up(), pt[i + ofs + 2]) + 1)
                dset(d, i + 1, i + 3, ld(pt[i + ofs + 1].rt(), pt[i + ofs + 3]) + 1)
            }
        }
        dist(m, d)
        d
    }
    val em = 4 * ps.size
    val ed = Array(em) { IntArray(em) { inf } }
    for (i in 0 until em) ed[i][i] = 0
    for (pi in 0 until ps.size) {
        val p = ps[pi]
        val ofs = p.ofs
        val m = p.m
        val id0 = id[pi]
        for (j in 0..3) for (k in 0..3) {
            val jp = if (j <= 1) j else j - 4 + m
            val kp = if (k <= 1) k else k - 4 + m
            ed[pi + j][pi + k] = id0[jp][kp]
        }
        if (pi < ps.size - 1) {
            val p1 = ps[pi + 1]
            dset(ed, 4 * pi + 2, 4 * pi + 4, ld(pt[ofs + m - 2].up(), pt[p1.ofs]) + 1)
            dset(ed, 4 * pi + 2, 4 * pi + 5, ld(pt[ofs + m - 2].up(), pt[p1.ofs + 1]) + 1)
            dset(ed, 4 * pi + 3, 4 * pi + 4, ld(pt[ofs + m - 1].rt(), pt[p1.ofs]) + 1)
            dset(ed, 4 * pi + 3, 4 * pi + 5, ld(pt[ofs + m - 1].rt(), pt[p1.ofs + 1]) + 1)
        }
    }
    dist(em, ed)
    val m = readln().toInt()
    fun findPi(x: Int): Int {
        var l = 0
        var r = ps.size
        while (l < r - 1) {
           val m = (l + r) / 2
           if (ps[m].l > x) {
               r = m
           } else {
               l = m
           }
        }
        return l
    }
    val ans = Array(m) {
        val (y1, x1, y2, x2) = readln().split(" ").map { it.toInt() - 1 }
        var p1 = Pt(x1, y1)
        var p2 = Pt(x2, y2)
        var l1 = layer(p1)
        var l2 = layer(p2)
        if (l1 > l2) {
            p1 = p2.also { p2 = p1 }
            l1 = l2.also { l2 = l1 }
        }
        if (l1 == l2) {
            ld(p1, p2)
        } else {
            val pi1 = findPi(l1)
            val pi2 = findPi(l2)
            if (pi1 == pi2) {
                val p = ps[pi1]
                val d = Array(6) { IntArray(6) { inf } }
                for (i in 0..5) d[i][i] = 0
                dset(d, 0, 1, ld(p1, pt[2 * l1]))
                dset(d, 0, 2, ld(p1, pt[2 * l1 + 1]))
                dset(d, 3, 5, ld(p2, pt[2 * l2]))
                dset(d, 4, 5, ld(p2, pt[2 * l2 + 1]))
                val id1 = id[pi1]
                for (j in 0..3) for (k in 0..3) {
                    val jp = if (j <= 1) 2 * (l1 - p.l) + j else 2 * (l2 - p.l) + j - 2
                    val kp = if (k <= 1) 2 * (l1 - p.l) + k else 2 * (l2 - p.l) + k - 2
                    d[1 + j][1 + k] = id1[jp][kp]
                }
                dist(6, d)
                d[0][5]
            } else {
                val pt1 = ps[pi1]
                val pt2 = ps[pi2]
                val d = Array(10) { IntArray(10) { inf } }
                for (i in 0..9) d[i][i] = 0
                dset(d, 0, 1, ld(p1, pt[2 * l1]))
                dset(d, 0, 2, ld(p1, pt[2 * l1 + 1]))
                dset(d, 7, 9, ld(p2, pt[2 * l2]))
                dset(d, 8, 9, ld(p2, pt[2 * l2 + 1]))
                val id1 = id[pi1]
                val id2 = id[pi2]
                for (j in 0..3) for (k in 0..3) {
                    val jp = if (j <= 1) 2 * (l1 - pt1.l) + j else pt1.m - 4 + j
                    val kp = if (k <= 1) 2 * (l1 - pt1.l) + k else pt1.m - 4 + k
                    d[1 + j][1 + k] = id1[jp][kp]
                }
                for (j in 0..3) for (k in 0..3) {
                    val jp = if (j <= 1) j else 2 * (l2 - pt2.l) + j - 2
                    val kp = if (k <= 1) k else 2 * (l2 - pt2.l) + k - 2
                    d[5 + j][5 + k] = id2[jp][kp]
                }
                for (j in 0..3) for (k in 0..3) {
                    val jp = if (j <= 1) 4 * pi1 + j + 2 else 4 * pi2 + j - 2
                    val kp = if (k <= 1) 4 * pi1 + k + 2 else 4 * pi2 + k - 2
                    d[3 + j][3 + k] = ed[jp][kp]
                }
                dist(10, d)
                d[0][9]
            }
        }
    }
    println(ans.joinToString("\n"))
}

fun dset(d: Array<IntArray>, i: Int, j: Int, v: Int) {
    d[i][j] = v
    d[j][i] = v
}

fun dist(m: Int, d: Array<IntArray>) {
    for (k in 0 until m) {
        for (i in 0 until m) {
            for (j in 0 until m) {
                val s = d[i][k] + d[k][j]
                if (s < d[i][j]) d[i][j] = s
            }
        }
    }
}
