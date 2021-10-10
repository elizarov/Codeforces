package archive.kh1

import java.io.*
import kotlin.system.*

fun main() {
    val time = measureTimeMillis {
        File("KH1G-input-80.txt").bufferedReader().use {
            it.solveEuler()
        }
    }
    println("Done in $time ms")
}

class E(
    val y: Int,
    var c: Int = 0
)

class Edges {
    private val e = HashMap<Int, E>(2)
    private val c = ArrayList<E>(2)
    private var first = 0

    fun degree() = c.sumBy { it.c }

    fun add(y: Int) {
        val cur = e.getOrPut(y) { E(y) }
        if (cur.c == 0) c.add(cur)
        cur.c++
    }

    fun first() = c[first].y

    fun hasNext() = first < c.size

    fun rem(y: Int) {
        val cur = e.getValue(y)
        if (--cur.c == 0) {
            while (first < c.size && c[first].c == 0) first++
        }
    }
}

private fun BufferedReader.solveEuler() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array(n) { Edges() }
    repeat(m) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[x].add(y)
        g[y].add(x)
    }
    if (!g.all { it.degree() % 2 == 0 }) {
        println("NO")
    } else {
        val c = ArrayList<List<Int>>()
        var start = 0
        val v = IntArray(n) { -1 }
        while (true) {
            while (start < n && !g[start].hasNext()) start++
            if (start >= n) break
            val list = ArrayList<Int>()
            var x = start
            while (true) {
                v[x] = list.size
                list.add(x)
                val y = g[x].first()
                g[x].rem(y)
                g[y].rem(x)
                if (y == start) break
                if (v[y] >= 0) {
                    val d = list.subList(v[y], list.size)
                    c.add(d.toList())
                    for (z in d) v[z] = -1
                    d.clear()
                }
                x = y
            }
            c.add(list)
            for (z in list) v[z] = -1
        }
        println("YES")
        println(c.size)
        val ans = c.joinToString("\n") { list ->
            "${list.size + 1} ${list.joinToString(" ") { (it + 1).toString() }} ${list[0] + 1}"
        }
        println(ans)
    }
}

