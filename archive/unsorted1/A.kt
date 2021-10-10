package archive.unsorted1

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val s = readLine()!!.toCharArray()
        var i = 0
        while (i < n) {
            while (i < n && s[i] == '1') i++
            if (i >= n) break
            var j = i
            while (j < n && s[j] == '0') j++
            val ia = i > 0
            val ja = j < n
            val k = j - i
            val t = minOf(m, k)
            when {
                ia && !ja -> s.fill('1', i, i + t)
                !ia && ja -> s.fill('1', j - t, j)
                ia && ja -> {
                    val k2 = k / 2
                    val t2 = minOf(m, k2)
                    s.fill('1', i, i + t2)
                    s.fill('1', j - t2, j)
                }
            }
            i = j
        }
        println(s.concatToString())
    }
}