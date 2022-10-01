fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val b = readln().split(" ").map { it.toInt() }
        val bi = b.withIndex().map { IndexedValue(it.index + 1, it.value) }
        val k = bi.indexOfLast { (i, v) -> v > i } + 1
        val b0 = bi.filter { it.value == 0 }
        val bz = bi.filter { it.value == n + 1 }
        check(!(b0.isNotEmpty() && bz.isNotEmpty()))
        val a = IntArray(n)
        var cur = (b0 + bz).map { it.index }
        val rem = bi.filter { it.value in 1..n }.groupBy { it.value }
        var j = 0
        while (j < n) {
            val next = ArrayList<Int>()
            var last: Int? = null
            for (v in cur) rem[v]?.let { remv ->
                check(last == null)
                last = v
                next.addAll(remv.map { it.index })
            }
            for (v in cur) if (v != last) a[j++] = v
            last?.let { a[j++] = it }
            cur = next
        }
        println(k)
        println(a.joinToString(" "))
    }
}