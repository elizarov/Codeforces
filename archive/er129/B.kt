fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val m = readln().toInt()
        val b = readln().split(" ").map { it.toInt() }
        val bs = b.sumOf { it.toLong() }
        println(a[(bs % n).toInt()])
    }
}