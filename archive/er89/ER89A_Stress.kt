package archive.er89

fun main() {
    for (a in 0..30) {
        for (b in 0..30) {
            val s = solveA(a, b)
            val brute = solveABrute(a, b)
            if (s != brute.s) {
                println("$a $b")
                println("s = $s")
                println("brute = $brute")
                return
            }
        }
    }
}

data class E(val x: Int, val y: Int, val s: Int = x + y)

fun solveABrute(a: Int, b: Int): E {
    var res = E(0, 0, 0)
    val n = minOf(a, b)
    for (x in 0..n) {
        for (y in 0..n) {
            if (2 * x + y <= a && 2 * y + x <= b) {
                if (x + y > res.s) res = E(x, y)
            }
        }
    }
    return res
}
