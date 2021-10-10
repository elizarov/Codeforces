package archive.er78

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = readLine()!!.split(" ").map { it.toByte() }.toByteArray()
        val d = IntArray(2 * n + 1) { Integer.MAX_VALUE / 2 }
        var dl = 0
        for (i in n downTo 0) {
            if (i < d[dl + n]) d[dl + n] = i
            dl += a[n - i] * 2 - 3
        }
        var dr = 0
        var ans = Integer.MAX_VALUE
        for (i in n downTo 0) {
            val j = d[dr + n]
            if (i + j < ans) ans = i + j
            dr -= a[n + i - 1] * 2 - 3
        }
        println(ans)
    }
}