package archive.r504

fun main(args: Array<String>) {
    val (n, q) = readLine()!!.splitToIntArray()
    val a = readLine()!!.splitToIntArray()
    val found = findArray(n, q, a)
    if (found) {
        println("YES")
        println(a.joinToString(" "))
    } else {
        println("NO")
    }
}

fun findArray(n: Int, q: Int, a: IntArray): Boolean {
    val c = IntArray(q + 1)
    val fst = IntArray(q + 1) { -1 }
    val lst = IntArray(q + 1) { -1 }
    for ((i, x) in a.withIndex()) {
        c[x]++
        if (fst[x] < 0) fst[x] = i
        lst[x] = i
    }
    var last = q
    while (last > 0 && c[last] == 0) last--
    if (last == 0) {
        a.fill(q)
        return true
    }
    val next = IntArray(n) { it + 1 }
    val prev = IntArray(n) { it - 1 }
    for (x in last downTo 1) {
        if (c[x] == 0) continue
        var i = fst[x] - 1
        while (i >= 0) {
           if (a[i] == 0) {
               a[i] = x
               i--
               continue
           }
           if (a[i] < x) break
           i = prev[i]
        }
        var j = fst[x] + 1
        while (j < n) {
            if (a[j] == 0) {
                a[j] = x
                j++
                continue
            }
            if (a[j] < x) break
            if (a[j] == x) {
                j++
                continue
            }
            j = next[j]
        }
        if (j < lst[x]) return false
        next[i + 1] = j
        prev[j - 1] = i
    }
    if (last < q) {
        if (c[0] == 0) return false
        a[fst[0]] = q
    }
    return true
}

private fun String.splitToIntArray(): IntArray {
    val n = length
    if (n == 0) return IntArray(0) // EMPTY
    var res = IntArray(4)
    var m = 0
    var i = 0
    while (true) {
        var cur = 0
        var neg = false
        var c = get(i) // expecting number, IOOB if there is no number
        if (c == '-') {
            neg = true
            i++
            c = get(i) // expecting number, IOOB if there is no number
        }
        while (true) {
            val d = c.toInt() - '0'.toInt()
            require(d >= 0 && d <= 9) { "Unexpected character '$c' at $i" }
            require(cur >= Integer.MIN_VALUE / 10) { "Overflow at $i" }
            cur = cur * 10 - d
            require(cur <= 0) { "Overflow at $i" }
            i++
            if (i >= n) break
            c = get(i)
            if (c == ' ') break
        }
        if (m >= res.size) res = res.copyOf(res.size * 2)
        res[m++] = if (neg) cur else (-cur).also { require(it >= 0) { "Overflow at $i" } }
        if (i >= n) break
        i++
    }
    if (m < res.size) res = res.copyOf(m)
    return res
}