fun main() {
    fun query(l: Int, r: Int): Long {
        println("? $l $r")
        return readLine()!!.toLong()
    }
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val t1 = query(1, n)
        var l = 1
        var r = n - 2
        while (l + 1 < r) {
            val m = (l + r) / 2
            val q = query(m, n)
            if (q != t1) {
                r = m
            } else {
                l = m
            }
        }
        val i = l
        val t2 = query(i + 1, n)
        val j = (i + t1 - t2 + 1).toInt()
        val t3 = query(j, n)
        val t4 = query(j + 1, n)
        val k = (j + t3 - t4).toInt()
        println("! $i $j $k")
    }
}