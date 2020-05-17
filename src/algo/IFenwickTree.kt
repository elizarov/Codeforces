package algo

class IFenwickTree(n: Int) {
    val a = IntArray(n)

    fun fill(v: Int) {
        for (i in a.indices) {
            val f = i and (i + 1)
            a[i] = (i - f + 1) * v
        }
    }

    fun sum(toIndex: Int): Int { // inclusive
        var i = toIndex
        var sum = 0
        while (i >= 0) {
            sum += a[i]
            i = (i and (i + 1)) - 1
        }
        return sum
    }

    fun sum(fromIndex: Int, toIndex: Int): Int = // inclusive
        if (toIndex < fromIndex) 0 else
            sum(toIndex) - sum(fromIndex - 1)

    fun update(index: Int, delta: Int) {
        var i = index
        while (i < a.size) {
            a[i] += delta
            i = i or (i + 1)
        }
    }
}