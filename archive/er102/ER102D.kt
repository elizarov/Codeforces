fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val p = readLine()!!
        val fsum = IntArray(n + 1)
        val fmax = IntArray(n + 1)
        val fmin = IntArray(n + 1)
        for (i in 0 until n) {
            when (p[i]) {
                '+' -> fsum[i + 1] = fsum[i] + 1
                '-' -> fsum[i + 1] = fsum[i] - 1
                else -> error("!")
            }
            fmax[i + 1] = maxOf(fmax[i], fsum[i + 1])
            fmin[i + 1] = minOf(fmin[i], fsum[i + 1])
        }
        val bsum = IntArray(n + 1)
        val bmax = IntArray(n + 1)
        val bmin = IntArray(n + 1)
        for (i in n - 1 downTo 0) {
            when (p[i]) {
                '+' -> bsum[i] = bsum[i + 1] + 1
                '-' -> bsum[i] = bsum[i + 1] - 1
                else -> error("!")
            }
            bmax[i] = maxOf(bmax[i + 1], bsum[i])
            bmin[i] = minOf(bmin[i + 1], bsum[i])
        }
        repeat(m) {
            val (l, r) = readLine()!!.split(" ").map { it.toInt() - 1 }
            val c = fsum[l]
            val cmax = maxOf(fmax[l], c + bsum[r + 1] - bmin[r + 1])
            val cmin = minOf(fmin[l], c + bsum[r + 1] - bmax[r + 1])
            println(cmax - cmin + 1)
        }
    }
}