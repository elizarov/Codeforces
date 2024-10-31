import java.util.PriorityQueue

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toLong() }
        val head = HashMap<Long, Int>()
        val next = IntArray(n) { -1 }
        for (i in 0..<n) {
            val l = a[i] + i
            val prev = head.put(l, i)
            if (prev != null) next[i] = prev
        }
        var cur = 0L
        val queue = PriorityQueue<Long>()
        val set = HashSet<Long>()
        fun enq(l: Long) {
            if (set.add(l)) queue.add(l)
        }
        enq(n.toLong())
        while (!queue.isEmpty()) {
            cur = queue.remove()
            var i = head[cur] ?: -1
            while (i >= 0) {
                enq(cur + i)
                i = next[i]
            }
        }
        println(cur)
    }
}