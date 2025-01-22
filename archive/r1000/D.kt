fun main() {
    fun List<Int>.combos(): List<Int> {
        val res = ArrayList<Int>()
        var i = 0
        var j = lastIndex
        while (i < j) {
            res += get(j) - get(i)
            i++
            j--
        }
        return res
    }
    repeat(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val a = readln().split(" ").map { it.toInt() }.sorted().combos().runningFold(0L) { a, b -> a + b }
        val b = readln().split(" ").map { it.toInt() }.sorted().combos().runningFold(0L) { a, b -> a + b }
        val kk = minOf(n, m)
        val f = LongArray(kk + 1)
        var i = 0
        var j = 0
        var kmax = 0
        fun ok(i: Int, j: Int) = i >= 0 && j >= 0 && 2 * i + j <= n && 2 * j + i <= m
        fun space(i: Int, j: Int) = ok(i, j) && (ok(i + 1, j) || ok(i, j + 1))
        while (ok(i, j)) {
            f[i + j] = maxOf(f[i + j], a[i] + b[j])
            kmax = i + j
            val oki = ok(i + 1, j)
            val okj = ok(i, j + 1)
            if (oki && (!okj || a[i + 1] + b[j] > a[i] + b[j + 1])) {
                i++
            } else if (okj) {
                j++
            } else {
                if (space(i - 1, j + 1)) {
                    i--
                    j++
                    continue
                }
                if (space(i + 1, j - 1)) {
                    i++
                    j--
                    continue
                }
                break
            }
            while (ok(i + 1, j - 1) && (2 * j + i > m || a[i + 1] + b[j - 1] > a[i] + b[j])) {
                j--
                i++
            }
            while (ok(i - 1, j + 1) && (2 * i + j > n || a[i - 1] + b[j + 1] > a[i] + b[j])) {
                i--
                j++
            }
        }
        println(kmax)
        println(f.slice(1..kmax).joinToString(" "))
    }
}

/*
 4  0+4
 3  0+3  1+3
 2  0+2  1+2  2+2
 1  0+1  1+1  2+2  3+1
 0  0+0  1+0  2+0  3+0  4+0
     0    1    2    3    4
 */