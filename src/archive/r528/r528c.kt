package archive.r528

import java.io.*

fun main(args: Array<String>) = bufferOut { readSolveWrite() }

const val c0 = 'a'.toInt()
const val NA = 0.toChar()

private fun PrintWriter.readSolveWrite() {
    val t = readLine()!!.toInt()
    val c = CharArray(128)
    val u = IntArray(128)
    repeat(t) {
        val k = readLine()!!.toInt()
        val s = readLine()!!
        val a = readLine()!!
        val b = readLine()!!
        val sp = StringProb(k, s, a, b, c, u)
        if (sp.solve()) {
            println("YES")
            sp.fillRest()
            println(String(c.copyOfRange(c0, c0 + k)))
        } else {
            println("NO")
        }
    }
}


class StringProb(
    val k: Int,
    val s: String,
    val a: String,
    val b: String,
    val c: CharArray,
    val u: IntArray
) {
    val n = s.length
    val c1 = c0 + k

    fun fillRest() {
        var d = 'a'
        for (i in c0 until c1) {
            if (c[i] == NA) {
                while (u[d.toInt()] != 0) d++
                c[i] = d
                u[d.toInt()] = 3
            }
        }
    }

    private fun map1(i: Int, d: Char) {
        c[s[i].toInt()] = d
        u[d.toInt()] = 1
    }

    private fun map2(i: Int, d: Char) {
        c[s[i].toInt()] = d
        u[d.toInt()] = 2
    }

    private fun unmap2() {
        for (i in c0 until c1) {
            val d = c[i]
            if (d != NA) {
                if (u[d.toInt()] == 2) {
                    c[i] = NA
                    u[d.toInt()] = 0
                }
            }
        }
    }

    fun solve(): Boolean {
        for (i in c0 until c1) {
            c[i] = NA
            u[i] = 0
        }
        var i = 0
        while (i < n && a[i] == b[i]) {
            val cur = c[s[i].toInt()]
            val d = a[i]
            if (cur != d) {
                if (cur != NA) return false
                if (u[d.toInt()] != 0) return false
                map1(i, d)
            }
            i++
        }
        if (i >= n) return true
        if (a[i] > b[i]) return false
        val cur = c[s[i].toInt()]
        if (cur == NA) {
            for (d in a[i] + 1 until b[i]) {
                if (u[d.toInt()] == 0) {
                    map1(i, d)
                    return true
                }
            }
            if (u[a[i].toInt()] == 0) {
                map2(i, a[i])
                if (find(i + 1, Side.A)) return true
            }
            unmap2()
            if (u[b[i].toInt()] == 0) {
                map1(i, b[i])
                if (find(i + 1, Side.B)) return true
            }
            return false
        } else {
            if (cur < a[i] || cur > b[i]) return false
            if (cur > a[i] && cur < b[i]) return true
            return find(i + 1, if (cur == a[i]) Side.A else Side.B)
        }
    }

    enum class Side { A, B }

    private fun find(i0: Int, side: Side): Boolean {
        var i = i0
        while (i < n) {
            val lo = if (side == Side.A) a[i] else 'a'
            val hi = if (side == Side.A) 'a' + k - 1 else b[i]
            val cur = c[s[i].toInt()]
            if (cur != NA) {
                if (cur < lo || cur > hi) return false
                when (side) {
                    Side.A -> if (cur > lo) return true
                    Side.B -> if (cur < hi) return true
                }
            } else {
                // cur == archive.r528.NA
                when (side) {
                    Side.A -> for (d in lo + 1..hi) {
                        if (u[d.toInt()] == 0) {
                            map2(i, d)
                            return true
                        }
                    }
                    Side.B -> for (d in lo until hi) {
                        if (u[d.toInt()] == 0) {
                            map2(i, d)
                            return true
                        }
                    }
                }
                val d = if (side == Side.A) lo else hi
                if (u[d.toInt()] != 0) return false
                map2(i, d)
            }
            i++
        }
        return true
    }
}


private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }
