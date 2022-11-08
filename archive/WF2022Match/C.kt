fun main() {
    repeat(readln().toInt()) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val x = readln().split(" ").map { it.toInt() }.sortedDescending()
        var l = 0
        var r = k + 1
        while (r - l > 1) {
            val m = (r + l) / 2
            var t = 0
            var ok = true
            for (i in 0 until m) {
                if (t >= x[i]) {
                    ok = false
                    break
                }
                t += n - x[i]
            }
            if (ok) {
                l = m
            } else {
                r = m
            }
        }
        println(l)
    }
}