package algo

import java.util.*
import kotlin.test.*

fun main(args: Array<String>) {
    val n = 500_000
    println("====== Stress test with two $n digits numbers")

    val rnd = Random(1)
    val t = Timer()
    val sa = rnd.nextBigNumber(n, neg = false)
    val sb = rnd.nextBigNumber(n, neg = false)

    val time1a = t.println("Generated two numbers, start with BigInteger")
    val a = sa.toBigInteger()
    val b = sb.toBigInteger()
    t.println("Converted to BigIntegers")
    val c = a.multiply(b)
    t.println("Multiplied two BigIntegers")
    val ec = c.toString()
    val time1b = t.println("Converted to string, got ${ec.length} digits")

    println("====== Total time via BigInteger ====== ${time1b - time1a} ms")

    val time2a = t.println("Start with BigInt")
    val nb = sizeOfBigInt(sa.numberOfDigits() + sb.numberOfDigits())
    val x = sa.toBigInt(nb)
    val y = sb.toBigInt(nb)
    t.println("Converted to BigInts")
    val z = BigInt(nb)
    z.addMul(x, y)
    t.println("Multiplied two BigInts")
    val sz = z.toString()
    val time2b = t.println("Converted to string, got ${sz.length} digits")

    println("====== Total time via BigInt ====== ${time2b - time2a} ms")
    assertEquals(ec, sz)

    val time3a = t.println("Start with CPoly")
    val nc = sizeOfCPoly(sa.numberOfDigits() + sb.numberOfDigits())
    val p = sa.toCPoly(nc)
    val q = sb.toCPoly(nc)
    t.println("Converted to CPolys")
    p.fft()
    q.fft()
    t.println("Performed two FFTs")
    p.mulEach(q)
    t.println("Multiplied two CPolys")
    p.ifft()
    t.println("Performed iFFT")
    val sc = p.toString()
    val time3b = t.println("Converted to string, got ${sc.length} digits")
    
    println("====== Total time via CPoly FFT ====== ${time3b - time3a} ms")
    assertEquals(ec, sc)
}
