package archive.kh4

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k, x, y) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }.sorted()
        val maxSum = k * n.toLong()
        var sum = 0L
        var ans = 0
        var ok = true
        var i = 0
        while (i < n && sum + a[i] <= maxSum) {
            sum += a[i]
            if (ok && a[i] > k) {
                ok = false
                ans = (n - i) * x // can simply drop the rest 
            }
            i++
        }
        ans = if (ok) (n - i) * x // drop the rest
            else minOf(ans, (n - i) * x + y) // drop the rest & rearrange
        println(ans)
    }
}