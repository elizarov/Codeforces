fun main() {
    for (x in 2..100 step 2) {
        for (y in 2..100 step 2) {
            val n = solveB(x, y)
            if (n % x != y % n) {
                println("$x $y -> $n (${n % x} != ${y % n})")
            }
        }
    }
}