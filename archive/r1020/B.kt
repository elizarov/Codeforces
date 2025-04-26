import kotlin.math.abs

fun main() {
    data class P(val x: Int, val y: Int)
    val mod = 1_000_000_007
    repeat(readln().toInt()) {
        val (n, m, k) = readln().split(" ").map { it.toInt() }
        val p = List(k + 1) {
            val (x, y) = readln().split(" ").map { it.toInt() }
            P(x, y)
        }
        var ok = true
        val fix = Array(n + 1) { BooleanArray(m + 1) }
        val link = Array(n + 1) { arrayOfNulls<ArrayList<P>>(m + 1) }
        val list = ArrayList<P>()
        for ((p1, p2) in p.zipWithNext()) {
            if (abs(p1.x - p2.x) + abs(p1.y - p2.y) != 2) {
                ok = false
                break
            }
            if (p1.x == p2.x || p1.y == p2.y) {
                val p0 = P((p1.x + p2.x) / 2, (p1.y + p2.y) / 2)
                if (fix[p0.x][p0.y]) {
                    ok = false
                    break
                }
                fix[p0.x][p0.y] = true
            } else {
                val p3 = P(p1.x, p2.y)
                val p4 = P(p2.x, p1.y)
                val lp3 = link[p3.x][p3.y] ?: ArrayList<P>(3).also {
                    link[p3.x][p3.y] = it
                    list += p3
                }
                val lp4 = link[p4.x][p4.y] ?: ArrayList<P>(3).also {
                    link[p4.x][p4.y] = it
                    list += p4
                }
                lp3 += p4
                lp4 += p3
            }
        }
        if (!ok) {
            println(0)
        } else {
            var ans = 1L
            val vis = Array(n + 1) { BooleanArray(m + 1) }
            val queue = ArrayList<P>()
            for (p0 in list) {
                if (vis[p0.x][p0.y]) continue
                vis[p0.x][p0.y] = true
                var qh = 0
                queue += p0
                var fixc = 0
                var edgec2 = 0
                while (qh < queue.size) {
                    val p = queue[qh++]
                    if (fix[p.x][p.y]) fixc++
                    for (q in link[p.x][p.y]!!) {
                        edgec2++
                        if (!vis[q.x][q.y]) {
                            vis[q.x][q.y] = true
                            queue += q
                        }
                    }
                }
                queue.clear()
                val loopc = edgec2 / 2 - qh + 1
                var res = qh
                if (fixc > 1 || loopc > 1) {
                    res = 0
                } else if (fixc == 1) {
                    res = if (loopc > 0) 0 else 1
                } else if (loopc == 1) {
                    res = 2
                }
                ans = (ans * res) % mod
            }
            println(ans)
        }
    }
}