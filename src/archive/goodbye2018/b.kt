package archive.goodbye2018

fun main() {
    val n = readLine()!!.toInt()
    val o = List(n) { readPt() }
    val c = List(n) { readPt() }
    val u = Array(n) { BooleanArray(n) }
    val t = List(n) { i -> o[0] + c[i] }
    for (i in 0 until n) u[i][0] = true
    val uc = IntArray(n) { 1 }
    val m = t.withIndex().associateBy({ it.value }, { it.index })
    for (j in 1 until n) {
        for (i in 0 until n) {
            val tt = o[j] + c[i]
            val k = m[tt] ?: continue
            if (u[k][j]) continue
            u[k][j] = true
            uc[k]++
        }
    }
    for (i in 0 until n) {
        if (uc[i] == n) {
            println(t[i])
            return
        }
    }
}

data class Pt(val x: Int, val y: Int) {
    override fun toString(): String = "$x $y"
    operator fun plus(pt: Pt) = Pt(x + pt.x, y + pt.y)
}

fun readPt(): Pt {
    val (x, y) = readLine()!!.split(" ").map { it.toInt() }
    return Pt(x, y)
}
