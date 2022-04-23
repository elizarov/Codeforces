fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var p = -1
        var q = 0
        for (i in 0 until a.size - 1) {
            if (a[i] == a[i + 1]) {
                if (p < 0) p = i
                q = i + 1
            }
        }
        if (q == p + 1) {
            println(0)
        } else {
            println((q - p - 2).coerceAtLeast(1))
        }
    }
}