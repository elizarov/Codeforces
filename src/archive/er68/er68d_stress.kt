package archive.er68

fun main() {
    for (k in 3..10) {
        val m = 100
        val w = BooleanArray(m)
        w[1] = true
        w[2] = true
        for (i in 3 until m) {
            w[i] = !w[i - 1] || !w[i - 2] || (i >= k && !w[i - k])
        }
        for (i in 1 until m) {
            val ans = solveAliceBob(i, k)
            if (ans != w[i]) {
                println("n = $i, k = $k, ans = $ans is wrong")
                return
            }
        }
    }
}