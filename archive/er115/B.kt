fun main() {
    fun List<Int>.toMask(): Int {
        var mask = 0
        for (i in 0 until 5) if (get(i) == 1) mask = mask or (1 shl i)
        return mask
    }

    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toMask() }
        var ok = false
        find@ for (i in 0 until 5) next@for(j in i + 1 until 5) {
            val mi = 1 shl i
            val mj = 1 shl j
            var ci = 0
            var cj = 0
            for (k in 0 until n) {
                if (a[k] and mi != 0) ci++
                if (a[k] and mj != 0) cj++
                if (a[k] and (mi or mj) == 0) continue@next
            }
            if (ci >= n / 2 && cj >= n / 2) {
                ok = true
                break@find
            }
        }
        println(if (ok) "YES" else "NO")
    }
}