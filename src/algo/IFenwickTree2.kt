package algo

class IFenwickTree2(
    val n: Int,
    val m: Int
) {
    val a = Array(n) { IntArray(m) }

    fun sum(x: Int, y: Int): Int { // inclusive
        var sum = 0
        var i = x
        while (i >= 0) {
            var j = y
            while (j >= 0) {
                sum += a[i][j]
                j = (j and (j + 1)) - 1
            }
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun sum(x1: Int, y1: Int, x2: Int, y2: Int): Int = // inclusive
        if (x2 < x1 || y2 < y1) 0 else
            sum(x2, y2) - sum(x2, y1 - 1) - sum(x1 - 1, y2) + sum(x1 - 1, y1 - 1)

    fun update(x: Int, y: Int, delta: Int) {
        var i = x
        while (i < n) {
            var j = y
            while (j < m) {
                a[i][j] += delta
                j = j or (j + 1)
            }
            i = i or (i + 1)
        }
    }
}