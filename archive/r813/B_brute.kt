fun gcd(x: Int, y: Int): Int =
    if (y == 0) x else gcd(y, x % y)

fun lcm(x: Int, y: Int) = x * y / gcd(x, y)

fun main() {
    for (n in 1..10) {
        val p = IntArray(n)
        val u = BooleanArray(n + 1)
        var a = IntArray(n)
        var best = 0
        fun find(i: Int, sum: Int) {
            if (i >= n) {
                if (sum >= best) {
                    best = sum
                    a = p.copyOf()
                }
                return
            }
            for (j in 1..n) if (!u[j]) {
                p[i] = j
                u[j] = true
                find(i + 1, sum + lcm(i + 1, j))
                u[j] = false
            }
        }
        find(0, 0)
        println(a.joinToString(" "))
    }
}