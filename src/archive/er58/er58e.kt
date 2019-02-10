package archive.er58

import java.io.*

fun main() = bufferOut { readSolveWrite() }

private fun PrintWriter.readSolveWrite() {
    val n = readLine()!!.toInt()
    var root: Node? = null
    repeat(n) {
        val q = readLine()!!.split(" ")
        val (x, y) = sort(q[1].toInt(), q[2].toInt())
        val px = Pt(x, y, 0)
        val py = Pt(x, y, 1)
        when (q[0]) {
            "+" -> {
                val nl = root.trimLeft(py)
                val nr = root.trimRight(px)
                if (nl.size + nr.size <= root.size) {
                    root = Node(px, nl, nr)
                }
            }
            "?" -> {
                val inside = root.leftSizeInc(px) + root.rightSizeInc(py) < root.size
                println(if (inside) "NO" else "YES")
            }
        }
    }
}

class Node(
    val p: Pt,
    val l: Node?,
    val r: Node?
) {
    val size: Int = l.size + r.size
}

val Node?.size: Int get() = this?.size ?: 0

fun Node?.leftSizeInc(q: Pt): Int = when {
    this == null -> 0
    q < p -> l.leftSizeInc(q)
    else -> l.size + 1 + r.leftSizeInc(q)
}

fun Node?.rightSizeInc(q: Pt): Int = when {
    this == null -> 0
    q > p -> r.rightSizeInc(q)
    else -> r.size + 1 + l.rightSizeInc(q)
}

fun Node?.trimLeft(q: Pt): Node? = when {
    this == null -> null
    q <= p -> l.trimLeft(q)
    else -> Node(p, l, r.trimLeft(q))
}

fun Node?.trimRight(q: Pt): Node? = when {
    this == null -> null
    q >= p -> r.trimRight(q)
    else -> Node(p, l.trimRight(q), r)
}

fun sort(x: Int, y: Int): Pair<Int, Int> =
    if (x > y) x to y else y to x

class Pt(val x: Int, val y: Int, private val mode: Int = 0): Comparable<Pt> {
    override fun compareTo(other: Pt): Int = when (mode) {
        0 -> x - other.x
        1 -> other.y - y
        else -> error("!")
    }
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
