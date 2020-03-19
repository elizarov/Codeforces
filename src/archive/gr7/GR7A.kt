package archive.gr7

fun main() {
    val ans = Array(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        if (n == 1) {
            "-1"
        } else {
            "4" + "3".repeat(n - 1)
        }
    }
    println(ans.joinToString("\n"))
}