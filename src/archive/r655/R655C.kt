package archive.r655

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toInt() }
        var c = 0
        var d = false
        for (i in 0 until n) {
            if (a[i] == i + 1) {
                d = false
            } else {
                if (!d) {
                    d = true
                    c++
                }
            }
        }
        val r = c.coerceAtMost(2)
        println(r)
    }
}