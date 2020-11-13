package archive.kh5

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toLong() }
        val answer = a.sortedDescending().withIndex().maxOf { (it.index + 1) * it.value }
        println(answer)
    }
}