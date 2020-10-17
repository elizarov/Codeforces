package archive.raif1

fun main() {
    data class Pt(val r: Int, val c: Int)
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }
    val f = IntArray(n) { -1 }
    val q1 = ArrayDeque<Int>(n)
    val q23 = ArrayDeque<Int>(n)
    var fail = false
    for (i in n - 1 downTo 0) {
        when (a[i]) {
            1 -> q1.add(i)
            2 -> {
                if (q1.isEmpty()) { fail = true; break }
                f[i] = q1.removeFirst()
                q23.add(i)
            }
            3 -> {
                if (q23.isEmpty() && q1.isEmpty()) { fail = true; break }
                f[i] = q23.removeFirstOrNull() ?: q1.removeFirst()
                q23.add(i)
            }
        }
    }
    if (fail) {
        println(-1)
    } else {
        val p = ArrayList<Pt>()
        val r1 = IntArray(n)
        var r = 1
        for (i in 0 until n) {
            when (a[i]) {
                1 -> {
                    if (r1[i] > 0) {
                        p += Pt(r1[i], i + 1)
                    } else {
                        p += Pt(r++, i + 1)
                    }
                }
                2 -> {
                    val j = f[i]
                    p += Pt(r, i + 1)
                    r1[j] = r++
                }
                3 -> {
                    val j = f[i]
                    p += Pt(r, i + 1)
                    p += Pt(r, j + 1)
                    r++
                }
            }
        }
        println(p.size)
        println(p.joinToString("\n") { "${it.r} ${it.c}" })
    }
}