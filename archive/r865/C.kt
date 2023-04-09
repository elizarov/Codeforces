fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        if (n % 2 == 1) {
            println("YES")
        } else {
            val lim = LongArray(n)
            for (i in 1 until n step 2) {
                lim[i] = a[i - 1] - a[i] + (if (i == 1) 0 else lim[i - 2])
            }
            println(if (lim[n - 1] > 0) "NO" else "YES")
        }
    }
}