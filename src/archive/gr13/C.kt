package archive.gr13

fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val s = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        var ans = 0L
        var all = 0L
        var next = 0L
        val allDec = LongArray(n)
        for (i in 0 until n) {
            all -= allDec[i]
            val cs = all + next
            val c0 = minOf(s[i] - 1L, cs)
            next = cs - c0
            ans += s[i] - 1 - c0
            if (s[i] > 1) {
                val j = i + s[i] + 1
                if (j < n) allDec[j]++
                all++
                next--
            }
        }
        println(ans)
    }
}

