package archive.r662

private val MOD = 1_000_000_007

infix fun Int.mp(that: Int): Int = (this + that) % MOD

fun main() {
    val zc = charArrayOf(0.toChar())
    System.`in`.bufferedReader().run {
        val n = readLine()!!.toInt()
        val w = Array(n) { readLine().toCharArray() + zc }
        val c = Array(n) { IntArray(w[it].size) }
        c[n - 1].fill(1)
        for (r in n - 2 downTo 0) {
            val w0 = w[r]
            val w1 = w[r + 1]
            val c0 = c[r]
            val c1 = c[r + 1]
            var k0 = 0
            while (k0 < w0.size && k0 < w1.size && w0[k0] == w1[k0]) k0++
            if (k0 < w0.size && w0[k0] < w1[k0]) {
                var sk0 = 0
                for (j in k0 + 1 until w1.size) sk0 = sk0 mp c1[j]
                for (i in k0 + 1 until w0.size) c0[i] = sk0
            }
            

            var ki = 0
            for (i in w0.indices) {
                while (ki < i && ki + 1 < w1.size && w0[ki] == w1[ki + 1]) ki++
                for (j in w1.indices) {

                }
            }
        }
        println(c[0].fold(0, Int::mp))
    }
}