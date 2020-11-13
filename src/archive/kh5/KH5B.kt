package archive.kh5

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val p = readLine()!!.split(" ").map { it.toInt() }
        val answer = (1..n / k).maxOf { d ->
            val m = d * k
            p.subList(n - m, n - m + d).sum()
        }
        println(answer)
    }
}