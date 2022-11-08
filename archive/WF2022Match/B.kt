fun main() {
    repeat(readln().toInt()) {
        val n = readln().toLong()
        val pow = (1..18).runningFold(1L) { a, _ -> a * 10 }
        var best = 100
        loop@for (j in 1..18) for (i in 0 until j)  {
            val d1 = (n / pow[i]) % 10
            val d2 = (n / pow[j]) % 10
            if ((10 * d2 + d1) % 25 != 0L) continue
            best = j - 1
            break@loop
        }
        println(best)
    }
}