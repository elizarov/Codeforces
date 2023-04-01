//import java.util.PriorityQueue
//
//fun main() {
//    repeat(readln().toInt()) {
//        val (n, m) = readln().split(" ").map { it.toInt() }
//        val a = readln().split(" ").map { it.toInt() }
//        val g = Graph(n, 2 * m)
//        repeat(m) {
//            val (u, v) = readln().split(" ").map { it.toInt() - 1 }
//            g.add(u, v)
//            g.add(v, u)
//        }
//        val enq = BooleanArray(n)
//        data class V(val v: Int) : Comparable<V> {
//            override fun compareTo(other: V): Int = a[v] - a[other.v]
//        }
//        val q = IntArray(n)
//        var qs = 0
//        val pqs = arrayOfNulls<PriorityQueue<V>>(n)
//        val cp = IntArray(n) { it }
//        val cd = IntArray(n)
//        val cs = IntArray(n) { 1 }
//        fun merge(a: Int, b: Int): Int =
//            when  {
//                cd[a] < cd[b] -> {
//                    cp[a] = b
//                    cs[b] += cs[a]
//                    b
//                }
//                cd[a] > cd[b] -> {
//                    cp[b] = a
//                    cs[a] += cs[b]
//                    a
//                }
//                else -> {
//                    cp[b] = a
//                    cs[a] += cs[b]
//                    cd[a]++
//                    a
//                }
//            }
//        var ok = false
//        for (i in 0 until n) if (cs[i] == 1 && a[i] == 0) {
//            val pq = PriorityQueue<V>()
//            fun enq(v: Int) {
//                enq[v] = true
//                q[qs++] = v
//                pq += V(v)
//            }
//            enq(i)
//            var sz = 0
//            var cur = i
//            while (true) {
//                val v = pq.remove()?.v ?: break
//                if (a[v] > sz) break
//                var c = i
//                while (c != cp[c]) c = cp[c]
//                sz += cs[c]
//                val pqc = pqs[c]
//                if (pqc != null) {
//                    while (true) {
//                        val u = pqc.remove()?.v ?: break
//                        if (!enq[u]) enq(u)
//                    }
//                } else {
//                    g.from(v) { u ->
//                        if (!enq[u]) enq(u)
//                    }
//                }
//            }
//            if (sz == n) {
//                ok = true
//                break
//            }
//            pqs.clear()
//            for (i in 0 until qs) {
//                val v = q[i]
//                enq[v] = false
//                if (vv[v] == cc) cs[cc]++
//            }
//            qs = 0
//            cc++
//        }
//        println(if (ok) "YES" else "NO")
//    }
//}
//
//class Graph(vCap: Int = 16, eCap: Int = vCap * 2) {
//    var vCnt = 0
//    var eCnt = 0
//    var vHead = IntArray(vCap) { -1 }
//    var eVert = IntArray(eCap)
//    var eNext = IntArray(eCap)
//
//    fun add(v: Int, u: Int, e: Int = eCnt++) {
//        eVert[e] = u
//        eNext[e] = vHead[v]
//        vHead[v] = e
//    }
//
//    inline fun from(v: Int, action: (u: Int) -> Unit) {
//        var e = vHead[v]
//        while (e >= 0) {
//            action(eVert[e])
//            e = eNext[e]
//        }
//    }
//}
