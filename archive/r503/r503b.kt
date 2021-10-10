package archive.r503

data class Res(val i: Int, val r: Int) {
    override fun equals(other: Any?): Boolean = other is Res && other.r == r
}

fun ask(i: Int): Res {
    println("? $i")
    return Res(i, readLine()!!.toInt())
}

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val r = solveInt(n, ::ask)
    println("! $r")
}

fun solveInt(n: Int, ask: (Int) -> Res): Int {
    if (n % 4 != 0) return -1
    var a = ask(1)
    var b = ask(1 + n / 2)
    if (a.r == b.r) return a.i
    var da = a.r - b.r
    var db = -da
    while (true) {
        val u = ask((a.i + b.i) / 2)
        val v = ask(u.i + n / 2)
        if (u.r == v.r) return u.i
        val du = u.r - v.r
        if (du < 0 && da > 0 || du > 0 && da < 0) {
            b = u
            db = du
        } else {
            a = u
            da = du
        }
    }
}