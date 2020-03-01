package archive.kh3

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }
        val s = a.withIndex().sortedByDescending { it.value }
        val d = IntArray(n)
        var rem = k
        for (p in 1 until n) {
            val i = s[p - 1].index
            val j = s[p].index
            d[j] = minOf(a[i] + d[i] - a[j] - 1, rem)
            rem -= d[j]
        }
        val add = rem / n
        val ext = rem % n
        for (p in 0 until n) d[s[p].index] += add + if (p < ext) 1 else 0
        println(d.joinToString(" "))
    }
}