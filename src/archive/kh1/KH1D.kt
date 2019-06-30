package archive.kh1

import java.util.*

fun main() {
    val m = readLine()!!.toInt()
    val b = readLine()!!.split(" ").map { it.toInt() }
    val n = b.count { it == -1 }
    val a = Array(n) { ArrayList<Int>() }
    val ix = LinkedList<Int>((0 until n).toList())
    var iter = ix.iterator()
    for (x in b) {
        if (!iter.hasNext()) iter = ix.iterator()
        val i = iter.next()
        if (x == -1) {
            iter.remove()
        } else {
            a[i].add(x)
        }
    }
    println(n)
    println(a.joinToString("\n") {
        "${it.size} ${it.joinToString(" ")}"
    })
}