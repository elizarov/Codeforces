fun main() {
    repeat(readln().toInt()) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        if (gcd(a, b) == 1) {
            println(1)
            println("$a $b")
        } else {
            println(2)
            println("${a - 1} 1")
            println("$a $b")
        }
    }
}

tailrec fun gcd(x: Int, y: Int): Int =
    if (y == 0) x else gcd(y, x % y)
