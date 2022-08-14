fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val li = HashMap<Int, Int>()
        for ((i, x) in a.withIndex()) li[x] = i
        var r = n - 1
        while (r > 0 && a[r - 1] <= a[r]) r--
        val k = a.subList(0, r).distinct().toMutableSet()
        var kli = k.map { x -> li[x]!! }.maxOrNull() ?: -1
        while (kli >= r) {
            val r1 = kli + 1
            for (i in r until kli) if (a[i] !in k) {
                k += a[i]
                kli = maxOf(kli, li[a[i]]!!)
            }
            r = r1
        }
        println(k.size)
    }
}