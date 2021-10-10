package archive.gr13

fun main() {
    repeat(readLine()!!.toInt()) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() }
        println(if(solveD(u, v)) "YES" else "NO")
    }
}

fun solveD(u: Int, v: Int): Boolean {
    var ub = 1 shl 30
    var vb = 1 shl 30
    var cc = false
    while (true) {
        while (ub > 0 && (u and ub) == 0) ub = ub shr 1
        while (vb > 0 && (v and vb) == 0) vb = vb shr 1
        if (vb == 0) break
        if (ub == 0) {
            return false
        }
        if (ub > vb) {
            if (!cc) {
                return false
            }
            ub = ub shr 1
            continue
        } else {
            cc = ub < vb || cc
            ub = ub shr 1
            vb = vb shr 1
        }
    }
    if (ub == 0) {
        return true
    }
    return cc
}

