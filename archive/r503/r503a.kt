package archive.r503

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val p = IntArray(n)
    val c = IntArray(n)
    val v = Array(m) { ArrayList<Int>() }
    repeat(n) { i ->
        val (pi, ci) = readLine()!!.split(" ").map { it.toInt() }
        p[i] = pi
        c[i] = ci
        v[pi - 1].add(ci)
    }
    repeat(m) { j ->
        v[j].sort()
    }
    var bestCost = Long.MAX_VALUE
    val c0 = ArrayList<Int>()
    for (votes in v[0].size..n) {
        c0.clear()
        var curCost = 0L
        var curVotes = v[0].size
        for (j in 1 until m) {
            var u = 0
            while (u < v[j].size && v[j].size - u >= votes) {
                curCost += v[j][u++]
                curVotes++
            }
            for (k in u until v[j].size) {
                c0.add(v[j][k])
            }
        }
        c0.sort()
        var i = 0
        while (curVotes < votes) {
            curCost += c0[i++]
            curVotes++
        }
        if (curCost < bestCost) {
            bestCost = curCost
        }
    }
    println(bestCost)
}