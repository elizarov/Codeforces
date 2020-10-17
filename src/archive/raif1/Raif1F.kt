package archive.raif1

fun main() {
    data class D(val j: Int, var m: Int) {
        fun right() = j + m
    }
    val n = readLine()!!.toInt()
    val s = readLine()!!
    var sum = 0L
    var ss = 0L
    val b = ArrayList<D>()
    for (i in 0 until n) {
        if (s[i] == '1') {
            if (b.isEmpty() || b.last().right() < i) {
                val p = b.lastOrNull()?.right() ?: 0
                ss += i - p + 1
                if (b.lastOrNull()?.m == 1) b.removeLast()
                b += D(i, 1)
            } else {
                val last = b.removeLast()
                val p = b.lastOrNull()?.right()?.minus(last.m) ?: 0
                ss += i - p + 1
                last.m++
                if (b.lastOrNull()?.m == last.m) b.removeLast()
                b += last
            }
        }
        sum += ss
    }
    println(sum)
}