package archive.er83

private const val m = 100

fun main() {
    repeat(readLine()!!.toInt()) {
        val l0 = readLine()!!.split(" ").map { it.toInt() }
        val n = l0[0]
        val xyz = l0.subList(1, 4)
        val a = readLine()!!.split(" ").map { it.toLong() }
        val f = Array(m) { IntArray(3) }
        val r = BooleanArray(4)
        val sig0 = IntArray(m)
        val sig5 = IntArray(m)
        val back = HashMap<Int, Int>()
        var r0 = -1
        var rd = -1
        for (k in 1 until m) {
            for (t in 0..2) {
                r.fill(false)
                for (u in 0..2) {
                    if (u == 0 || u != t) r[f[maxOf(0, k - xyz[u])][u]] = true
                }
                var p = 0
                while (r[p]) p++
                f[k][t] = p
            }
            sig0[k] = f[k].fold(0) { acc, x -> (acc shl 2) + x }
            if (k >= 4) {
                val s5 = sig0.slice(k - 4..k).fold(0) { acc, x -> (acc shl 6) + x }
                back[s5]?.let { b ->
                    r0 = b
                    rd = k - b
                }
                sig5[k] = s5
                back[s5] = k
            }
            if (r0 >= 0) break
        }
        fun fs(k: Long, t: Int): Int {
            if (k < r0) return f[k.toInt()][t]
            return f[r0 + ((k - r0) % rd).toInt()][t]
        }
        var sg = 0
        for (i in 0 until n) sg = sg xor fs(a[i], 0)
        if (sg == 0) {
            println(0)
        } else {
            var cnt = 0
            for (i in 0 until n) {
                val sg0 = sg xor fs(a[i], 0)
                for (u in 0..2) {
                    if (sg0 xor fs(maxOf(0, a[i] - xyz[u]), u) == 0) cnt++
                }
            }
            println(cnt)
        }
    }
}