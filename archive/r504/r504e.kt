package archive.r504

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val s = StringBuilder()
    findPath(1, 1, n, n, s)
    println("! $s")
}

fun askPath(r1: Int, c1: Int, r2: Int, c2: Int): Boolean {
    println("? $r1 $c1 $r2 $c2")
    return when (readLine()!!) {
        "YES", "1" -> true
        "NO", "0" -> false
        else -> error("wrong ans")
    }
}

fun findPath(r1: Int, c1: Int, r2: Int, c2: Int, s: StringBuilder) {
    val w = c2 - c1 + 1
    val h = r2 - r1 + 1
    if (h == 1) {
        repeat(w - 1) { s.append('R') }
        return
    }
    if (w == 1) {
        repeat(h - 1) { s.append('D') }
        return
    }
    val d: Int
    var r0: Int
    var c0: Int
    if (w > h) {
        d = h
        r0 = r2
        c0 = c1 + (w - h) / 2
    } else {
        d = w
        r0 = r2 - (h - w) / 2
        c0 = c1
    }
    repeat(d) { iter ->
        if (iter == d - 1 || (askPath(r1, c1, r0, c0) && askPath(r0, c0, r2, c2))) {
            findPath(r1, c1, r0, c0, s)
            findPath(r0, c0, r2, c2, s)
            return
        }
        r0--
        c0++
    }
    error("Cannot happen")
}
