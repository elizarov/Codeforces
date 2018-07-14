package algo

import java.util.*
import kotlin.test.*

fun main(args: Array<String>) {
    val a = 3
    val b = 2_000_000
    println("====== Stress test with $a ** $b")

    val t = Timer()
    val time1a = t.println("Start with BigInteger")
    val c = a.toBigInteger().pow(b)
    t.println("Pow in BigInteger")
    val ec = c.toString()
    val time1b = t.println("Converted to string, got ${ec.length} digits")

    println("====== Total time via BigInteger ====== ${time1b - time1a} ms")
    
    val time3a = t.println("Start with CPoly")
    val p = fastPow(a, b)
    t.println("FastPow in CPoly")
    val sc = p.toString()
    val time3b = t.println("Converted to string, got ${sc.length} digits")
    
    println("====== Total time via CPoly ====== ${time3b - time3a} ms")
    assertEquals(ec, sc)
}
