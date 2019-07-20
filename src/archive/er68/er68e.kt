package archive.er68
private const val OFS = 5000
private const val MAX = OFS * 2 + 1

fun main() {
    val n = readLine()!!.toInt()
    val hs = ArrayList<Seg>()
    val vs = ArrayList<Seg>()
    repeat(n) {
        val c = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        if (c[0] > c[2] || c[1] > c[3]) {
            c[0] = c[2].also { c[2] = c[0] }
            c[1] = c[3].also { c[3] = c[1] }
        }
        val seg = Seg(c[0], c[1], c[2], c[3])
        if (seg.x1 == seg.x2) vs.add(seg) else hs.add(seg)
    }
    val f = FenwickTree2(MAX, MAX)
    for (h in hs) for (v in vs) if (intersects(h, v)) {
        f.update(v.x1 + OFS, h.y1 + OFS, 1)
    }
}

private fun intersects(h: Seg, v: Seg) =
    h.y1 in v.y1..v.y2 && v.x1 in h.x1..h.x2

private class Seg(val x1: Int, val y1: Int, val x2: Int, val y2: Int)

class FenwickTree2(
    val n: Int,
    val m: Int
) {
    val a = Array(n) { ShortArray(m) }

    fun sum(x: Int, y: Int): Int { // inclusive
        var sum = 0
        var i = x
        while (i >= 0) {
            var j = y
            while (j >= 0) {
                sum += a[i][j]
                j = (j and (j + 1)) - 1
            }
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun sum(x1: Int, y1: Int, x2: Int, y2: Int): Int = // inclusive
        if (x2 < x1 || y2 < y1) 0 else
            sum(x2, y2) - sum(x2, y1 - 1) - sum(x1 - 1, y2) + sum(x1 - 1, y1 - 1)

    fun update(x: Int, y: Int, delta: Int) {
        var i = x
        while (i < n) {
            var j = y
            while (j < m) {
                a[i][j] = (a[i][j] + delta).toShort()
                j = j or (j + 1)
            }
            i = i or (i + 1)
        }
    }
}