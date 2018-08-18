package archive.r504

import kotlin.math.*

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!.toCharArray()
    val root = parseSeq(s, 0)
    check(root.end == s.size)
    val sb = StringBuilder()
    findSeq(root, k, sb)
    println(sb)
}

class Node(val size: Int, val end: Int, val c: List<Node>)

fun parseSeq(s: CharArray, start: Int): Node {
    var i = start
    if (s[i] == ')') return Node(0, i, emptyList())
    val c = ArrayList<Node>(1)
    while (i < s.size && s[i] == '(') {
        i++
        val d = parseSeq(s, i)
        i = d.end + 1
        c.add(d)
    }
    return Node(i - start, i, c)
}

fun findSeq(n: Node, k: Int, sb: StringBuilder) {
    if (k == 0) return
    var rem = k
    for (m in n.c) {
        sb.append('(')
        val req = min(rem - 2, m.size)
        findSeq(m, req, sb)
        rem = rem - req - 2
        sb.append(')')
        if (rem == 0) break
    }
}

