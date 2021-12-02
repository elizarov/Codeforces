fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    var ans = 1L
    repeat(n) {
        ans = (ans * n) % m
    }
    println(ans)
}