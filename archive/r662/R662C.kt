package archive.r662

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val c = IntArray(n + 1)
        for (a in readLine()!!.split(" ").map { it.toInt() }) {
            c[a]++
        }
        val mc = c.maxOrNull()!!
        val mcc = c.count { it == mc }
        val d = (n - mcc) / (mc - 1) - 1
        println(d)
    }
}