fun main() {
    val MOD = 1000000007L
    repeat(readln().toInt()) {
        val n = readln().toInt()
        var sum = 0L
        for (i in 0 until n) {
            sum = (sum + 2 * i) % MOD
        }
        for (i in 1..n) {
            sum = (sum * i) % MOD
        }
        println(sum)
    }
}