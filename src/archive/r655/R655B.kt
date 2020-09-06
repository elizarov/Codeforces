package archive.r655

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        var d = 2
        while (d * d <= n) {
            if (n % d == 0) break
            d++
        }
        if (n % d == 0) {
            val a = n / d
            println("$a ${n - a}")
        } else {
            println("1 ${n - 1}")
        }
    }
}