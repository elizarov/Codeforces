fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = IntArray(n) { readLine()!!.toInt() }
        val cur = IntArray(n + 1)
        var d = 0
        for (i in 0 until n) {
            if (a[i] == 1) {
                cur[d] = 1
                d++
            } else {
                var found = false
                for (j in d - 1 downTo 0) {
                    if (cur[j] == a[i] - 1) {
                        cur[j]++
                        d = j + 1
                        found = true
                        break
                    }
                }
                check(found)
            }
            println(cur.take(d).joinToString("."))
        }
    }
}