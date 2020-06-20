package archive.r651

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
            .withIndex()
            .partition { it.value % 2 == 0 }
        var i = 0
        var j = 0
        repeat(n - 1) {
            if (i <= a.size - 2) {
                println("${a[i++].index + 1} ${a[i++].index + 1}")
            } else {
                println("${b[j++].index + 1} ${b[j++].index + 1}")
            }
        }
    }
}