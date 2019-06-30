package archive.kh1

import java.io.*
import kotlin.system.*

fun main() {
    val time = measureTimeMillis {
        File("KH1G-input-22.txt").bufferedReader().use {
            it.solveEuler()
        }
    }
    println("Done in $time ms")
}


private fun BufferedReader.solveEuler() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array(n) { HashMap<Int, Int>() }
    repeat(m) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[x].add(y)
        g[y].add(x)
    }
    if (!g.all { it.values.sum() % 2 == 0 }) {
        println("NO")
    } else {
        val c = ArrayList<List<Int>>()
        val nz = g.withIndex().filter { it.value.size > 0 }.map { it.index }.toMutableSet()
        val v = IntArray(n) { -1 }
        while (nz.isNotEmpty()) {
            val list = ArrayList<Int>()
            val start = nz.first()
            var x = start
            while (true) {
                v[x] = list.size
                list.add(x)
                val y = g[x].keys.first()
                if (g[x].rem(y)) nz.remove(x)
                if (g[y].rem(x)) nz.remove(y)
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
        println(c.joinToString("\n") { list ->
            "${list.size + 1} ${list.joinToString(" ") { (it + 1).toString() } } ${list[0] + 1}"
        })
    }
}

private fun HashMap<Int, Int>.add(y: Int) {
    val cur = get(y)
    put(y, if (cur == null) 1 else cur + 1)
}

private fun HashMap<Int, Int>.rem(y: Int): Boolean {
    val cur = getValue(y)
    if (cur == 1) {
        remove(y)
        return isEmpty()
    } else {
        put(y, cur - 1)
        return false
    }
}
