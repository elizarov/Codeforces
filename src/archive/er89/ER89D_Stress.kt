package archive.er89

fun main() {
    val n = 100000
    val ps = primeSieve(n + 1)
    for (x in 2..n) {
        var rr = 1
        var p = 1
        var pk = 1
        ps.factors(x) { f ->
            if (f == p) {
                pk *= f
            } else {
                rr *= pk
                p = f
                pk = f
            }
        }
        if (rr % 2 == 0) {
            check(x % rr == 0)
            check(x % pk == 0)
            val g = gcd(rr + pk, x)
            if (g != 1) {
                println("x = $x")
                println("rr = $rr")
                println("pk = $pk")
                return
            }
        }
    }
}

tailrec fun gcd(x: Int, y: Int): Int =
    if (y == 0) x else gcd(y, x % y)