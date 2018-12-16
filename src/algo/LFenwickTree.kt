package algo

class LFenwickTree(n: Int) {
    val a = LongArray(n)

    fun fill(v: Long) {
        for (i in 0 until a.size) {
            val f = i and (i + 1)
            a[i] = (i - f + 1) * v
        }
    }

    fun sum(toIndex: Int): Long { // inclusive
        var i = toIndex
        var sum = 0L
        while (i >= 0) {
            sum += a[i]
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun sum(fromIndex: Int, toIndex: Int): Long = // inclusive
        if (toIndex < fromIndex) 0 else
            sum(toIndex) - sum(fromIndex - 1)

    fun update(index: Int, delta: Long) {
        var i = index
        while (i < a.size) {
            a[i] += delta
            i = i or (i + 1)
        }
    }
}