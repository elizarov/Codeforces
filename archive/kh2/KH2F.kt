package archive.kh2

fun main() {
    val n = readLine()!!.toInt()
    val w = List(n) { readLine()!! }
    val k = "kotlin"
    val g = k.withIndex().associateBy({ it.value }) { it.index }
    val gf = IntArray(k.length) { -1 }
    val gn = IntArray(n)
    val gv = IntArray(n)
    for ((i, s) in w.withIndex()) {
        val p = g.getValue(s.first())
        val q = (g.getValue(s.last()) + 1) % k.length
        gv[i] = q
        gn[i] = gf[p]
        gf[p] = i
    }
    val ans = IntArray(n)
    var cnt = 0
    val vStack = IntArray(n + 1)
    val eStack = IntArray(n + 1)
    var sp = 0
    while (true) {
        val p = vStack[sp]
        if (gf[p] < 0) {
            if (sp <= 0) break
            ans[cnt++] = eStack[sp--]
            continue
        }
        val j = gf[p]
        val q = gv[j]
        gf[p] = gn[j]
        sp++
        vStack[sp] = q
        eStack[sp] = j
    }
    check(cnt == n)
    ans.reverse()
    println(ans.joinToString(" ") { (it + 1).toString() })
}