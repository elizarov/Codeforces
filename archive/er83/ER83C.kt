package archive.er83

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toLong() }
        val p = PriorityQueue<Long>(reverseOrder())
        for (t in a) {
            if (t != 0L) p.add(t)
        }
        val b = ArrayList<Long>()
        var cur = 1L
        val max = p.peek() ?: 0L
        while (cur <= max) {
            b.add(cur)
            if (cur.toDouble() * k > max) break
            cur *= k
        }
        b.reverse()
        var i = 0
        while (!p.isEmpty() && i < b.size) {
            var t = p.remove()
            while (i < b.size && b[i] > t) i++
            t -= b[i]
            i++
            if (t != 0L) p.add(t)
        }
        if (p.isEmpty()) println("YES") else println("NO")
    }
}