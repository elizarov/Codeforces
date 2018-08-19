package archive.er49

import kotlin.math.*

fun main(args: Array<String>) {
    val tests = readLine()!!.toInt()
    repeat(tests) {
        val n = readLine()!!.toInt()
        val s = readLine()!!
        println(if (checkPoly(s)) "YES" else "NO")
    }
}

private fun checkPoly(s: String): Boolean {
    var i = 0
    var j = s.lastIndex
    while (i < j) {
        if (abs(s[i] - s[j]) !in setOf(0, 2)) return false
        i++
        j--
    }
    return true
}