package archive.r651

import java.lang.StringBuilder
import kotlin.system.*

fun main() {
    val n = 1000_0000
    val s = StringBuilder()
    val t = StringBuilder()
    var k = 1
    while (s.length < n) {
        repeat(k) { s.append('0') }
        repeat(k) { t.append('1') }
        repeat(k) { s.append('1') }
        repeat(k) { t.append('0') }
        k++
    }
    check(s.length == t.length)
    val time = measureTimeMillis {
        val ans = solveE(s.length, s.toString(), t.toString())
        println("ans = $ans")
    }
    println("time = $time ms")
}