fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }.sorted()
        for (i in 0 until n / 2) {
            println("${a[i + 1]} ${a[0]}")
        }
    }
}