//fun main() {
//    repeat(readLine()!!.toInt()) {
//        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
//        val a = readLine()!!.split(" ").map { it.toInt() }
//        data class VC(val v: Int, val oc: Int)
//        val c = a.groupingBy { it }.eachCount().map { VC(it.key, it.value) }.sortedBy { it.v }
//        val cf = IFenwickTree(n + 1)
//        for ((_, oc) in c) cf.update(oc, 1)
//        var curI = 0
//        var curMex = 0
//        while (curI < c.size && c[curI].v == curMex) {
//            cf.update(c[curI].oc, -1)
//            curI++
//            curMex++
//        }
//        fun vanish(k: Int): Int {
//            var l = 0
//            var r = n + 1
//            while (l + 1 < r) {
//                val m = (l + r) / 2
//                val sum = cf.sum(1, m)
//            }
//            return l
//        }
//        var ans = c.size - vanish(k) - curMex
//        for (op in 1..k) {
//            curMex++
//            while (curI < c.size && c[curI].v == curMex) {
//                cf.update(c[curI].oc, -1)
//                curI++
//                curMex++
//            }
//            ans = minOf(ans, c.size + op - vanish(k - op) - curMex)
//            if (curI >= c.size) break
//        }
//        println(ans)
//    }
//}
//
//class IFenwickTree(n: Int) {
//    val a = IntArray(n)
//
//    fun fill(v: Int) {
//        for (i in a.indices) {
//            val f = i and (i + 1)
//            a[i] = (i - f + 1) * v
//        }
//    }
//
//    fun sum(toIndex: Int): Int { // inclusive
//        var i = toIndex
//        var sum = 0
//        while (i >= 0) {
//            sum += a[i]
//            i = (i and (i + 1)) - 1
//        }
//        return sum
//    }
//
//    fun sum(fromIndex: Int, toIndex: Int): Int = // inclusive
//        if (toIndex < fromIndex) 0 else
//            sum(toIndex) - sum(fromIndex - 1)
//
//    fun update(index: Int, delta: Int) {
//        var i = index
//        while (i < a.size) {
//            a[i] += delta
//            i = i or (i + 1)
//        }
//    }
//}