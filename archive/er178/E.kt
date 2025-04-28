fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val s = readln().map { it - 'a' }.toIntArray()
    val ptr = Array(n + 1) { IntArray(k) }
    val last = IntArray(k) { n }
    last.copyInto(ptr[n])
    val res = IntArray(n + 1)
    var curRes = 1
    res[n] = curRes
    val used = BooleanArray(k)
    var uc = 0
    for (i in n - 1 downTo 0) {
        last[s[i]] = i
        last.copyInto(ptr[i])
        if (!used[s[i]]) {
            used[s[i]] = true
            uc++
            if (uc == k) {
                curRes++
                uc = 0
                used.fill(false)
            }
        }
        res[i] = curRes
    }
    val ans = Array(readln().toInt()) {
        val t = readln().map { it - 'a' }.toIntArray()
        var cnt = 0
        for (x in t) {
            cnt = ptr[cnt][x] + 1
            if (cnt > n) break
        }
        if (cnt > n) {
            0
        } else {
            res[cnt]
        }
    }
    println(ans.joinToString("\n"))
}