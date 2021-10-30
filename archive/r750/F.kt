fun main() {
    val BITS = 1024
    val n = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toInt() }
    var cur = IntArray(BITS) { Int.MAX_VALUE }
    var prev = IntArray(BITS) { Int.MAX_VALUE }
    cur[0] = -1
    cur[a[0]] = a[0]
    for (i in 1 until n) {
        cur = prev.also { prev = cur }
        for (b in 0 until BITS) {
            cur[b] = prev[b]
            val p = b xor a[i]
            if (prev[p] < a[i] && a[i] < cur[b]) {
                cur[b] = a[i]
            }
        }
    }
    val ans = cur.withIndex().filter { it.value < Int.MAX_VALUE }.map { it.index }
    println(ans.size)
    println(ans.joinToString(" "))
}