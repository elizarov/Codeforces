package archive.er67

import kotlin.system.*
import kotlin.test.*

fun main() {
    val n = 200_000
    val s = buildString {
        repeat(n) { append('z') }
    }
    val time = measureTimeMillis {
        val res = solveFriends(n, s, listOf(s))
        assertEquals(n, res[0])
    }
    println("$time ms")
}