package archive.er48

fun main(args: Array<String>) {
    val (n, m, q) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!
    val t = readLine()!!
    val f = Fenwick(n)
    var i = 0
    while (true) {
        val j = s.indexOf(t, i)
        if (j < 0) break
        f.update(j, 1)
        i = j + 1
    }
    repeat(q) {
        val (l, r) = readLine()!!.split(" ").map { it.toInt() }
        println(f.sum(l - 1, r - m))
    }
}

class Fenwick(n: Int) {
    val a = IntArray(n)

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
        if (toIndex < fromIndex) 0 else sum(toIndex) - sum(fromIndex - 1)

    fun update(index: Int, delta: Int) {
        var i = index
        while (i < a.size) {
            a[i] += delta
            i = i or (i + 1)
        }
    }
}