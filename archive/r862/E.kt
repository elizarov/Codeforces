fun main() {
    val n = readln().toInt()
    val g = Graph(n, 2 * n - 2)
    repeat(n - 1) {
        val (u, v) = readln().split(" ").map { it.toInt() - 1 }
        g.add(u, v)
        g.add(v, u)
    }
    val a = readln().split(" ").map { it.toInt() }
    val b = a.withIndex().groupBy { it.value }.toList().sortedByDescending { it.first }
    val j3 = b.indexOfFirst { it.second.size >= 3 }.takeIf { it >= 0 } ?: b.size
    val w3 = b.getOrNull(j3)?.first ?: 0
    val j2 = b.indexOfFirst { it.second.size == 2 }
    val ans = IntArray(n - 1) { w3 }
    if (j2 < 0 || j2 > j3) {
        println(ans.joinToString("\n"))
        return
    }
    val w2  = b[j2].first
    ans.fill(w2)
    val p = IntArray(n) { -1 }
    val pe = IntArray(n) { -1 }
    val dfsParent = DeepRecursiveFunction<Int, Unit> { v ->
        g.from(v) { u, e ->
            if (u != 0 && p[u] < 0) {
                p[u] = v
                pe[u] = e / 2
                callRecursive(u)
            }
        }
    }
    dfsParent(0)
    val (vl, vr) = b[j2].second.map { it.index }
    fun up(v0: Int): List<Int> {
        val res = ArrayList<Int>()
        res += v0
        var v = v0
        while (p[v] >= 0) {
            res += pe[v]
            v = p[v]
            res += v
            if (v == vl || v == vr) break
        }
        return res
    }
    val ul = up(vl)
    val ur = up(vr)
    val path = when {
        ul.last() == vr -> ul
        ur.last() == vl -> ur
        else -> ul + ur.dropLast(1).reversed()
    }
    val vpi = IntArray(n) { -1 }
    for (i in path.indices step 2) {
        vpi[path[i]] = i
    }
    var fillIndex = 0
    val dfsFillVpi = DeepRecursiveFunction<Int, Unit> { v ->
        g.from(v) { u, _ ->
            if (vpi[u] < 0) {
                vpi[u] = fillIndex
                callRecursive(u)
            }
        }
    }
    while (fillIndex < path.size) {
        dfsFillVpi(path[fillIndex])
        fillIndex += 2
    }
    var il = 0
    var ir = path.lastIndex
    for (j in j2 + 1 until j3) if (b[j].second.size == 2) {
        val w = b[j].first
        var (i1, i2) = b[j].second.map { vpi[it.index] }
        if (i1 == i2) continue
        if (i1 > i2) i1 = i2.also { i2 = i1 }
        if (i2 <= il || i1 >= ir) continue
        i1 = maxOf(i1, il)
        i2 = minOf(i2, ir)
        for (i in il + 1 until i1 step 2) {
            ans[path[i]] = w
        }
        for (i in i2 + 1 until ir step 2) {
            ans[path[i]] = w
        }
        il = i1
        ir = i2
    }
    for (i in il + 1 until ir step 2) {
        ans[path[i]] = w3
    }
    println(ans.joinToString("\n"))
}

class Graph(vCap: Int = 16, eCap: Int = vCap * 2) {
    var vCnt = 0
    var eCnt = 0
    var vHead = IntArray(vCap) { -1 }
    var eVert = IntArray(eCap)
    var eNext = IntArray(eCap)

    fun add(v: Int, u: Int, e: Int = eCnt++) {
        eVert[e] = u
        eNext[e] = vHead[v]
        vHead[v] = e
    }

    inline fun from(v: Int, action: (u: Int, e: Int) -> Unit) {
        var e = vHead[v]
        while (e >= 0) {
            action(eVert[e], e)
            e = eNext[e]
        }
    }
}
