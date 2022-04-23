fun main() {
    repeat(readLine()!!.toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val b = readln().split(" ").map { it.toInt() }
        val f = IntArray(n)
        val c = IntArray(n) { 1 }
        val l = IntArray(n + 1)
        for (i in n - 1 downTo 0) {
            f[i] = l[a[i]]
            l[a[i]] = i
        }
        var i = 0
        var j = 0
        var ok = true
        while (j < n) {
            while (c[i] == 0) i++
            if (a[i] == b[j]) {
                c[i]--
                j++
                continue
            }
            if (f[i] == 0) {
                ok = false
                break
            }
            c[f[i]] += c[i]
            i++
        }
        println(if (ok) "YES" else "NO")
    }
}