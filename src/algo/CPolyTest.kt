package algo

import org.junit.*
import org.junit.Assert.*
import java.util.*

class CPolyTest {
    @Test
    fun testString() {
        val rnd = Random(1)
        for (n in 0..1000) {
            val s = rnd.nextBigNumber(n)
            val p = s.toCPoly()
            val s2 = p.toString()
            assertEquals(s, s2)
        }
    }

    @Test
    fun testFFTAndBack() {
        val n = 128
        val rnd = Random(1)
        val p = CPoly(n)
        for (i in 0 until 2 * n) {
            p.a[i] = rnd.nextDouble()
        }
        val q = p.copy()
        q.fft()
        q.ifft()
        val eps = 1e-15
        for (i in 0 until 2 * n) {
            assertEquals(p.a[i], q.a[i], eps)
        }
    }

    @Test
    fun testFastMulSameSize() {
        val rnd = Random(1)
        for (n in 1..1000) {
            val a = rnd.nextBigNumber(n)
            val b = rnd.nextBigNumber(n)
            val c = a.toBigInteger().multiply(b.toBigInteger()).toString()
            val d = fastMul(a, b)
            assertEquals("On ${a.length} + ${b.length} digits", c, d)
        }
    }

    @Test
    fun testFastMulRandomSize() {
        val rnd = Random(2)
        for (n in 2..1000) {
            val a = rnd.nextBigNumber(rnd.nextInt(n) + 1)
            val b = rnd.nextBigNumber(rnd.nextInt(n) + 1)
            val c = a.toBigInteger().multiply(b.toBigInteger()).toString()
            val d = fastMul(a, b)
            assertEquals("On ${a.length} + ${b.length} digits", c, d)
        }
    }

    @Test
    fun testSqr() {
        for (a in 2..20) {
            val sqrs = 8
            val pow = 1 shl sqrs
            val n = sizeOfCPoly(numberOfPowDigits(a, pow))
            val p = a.toCPoly(n)
            repeat(sqrs) {
                p.fastSqr()
            }
            val e = a.toBigInteger().pow(pow).toString()
            val s = p.toString()
            assertEquals(e, s)
        }
    }

    @Test
    fun testPow() {
        val rnd = Random(1)
        for (pow in 0..1000) {
            val x = rnd.nextInt(1000) + 2
            val e = x.toBigInteger().pow(pow).toString()
            val s = fastPow(x, pow).toString()
            assertEquals(e, s)
        }
    }

    @Test
    fun testShortMul() {
        val rnd = Random(1)
        for (n in 1..1000) {
            val a = rnd.nextBigNumber(n, neg = false)
            val b = rnd.nextInt(998) + 1
            val e = (a.toBigInteger() * b.toBigInteger()).toString()
            val p = a.toCPoly(sizeOfCPoly(a.numberOfDigits() + 3))
            p.shortMul(b)
            val s = p.toString()
            assertEquals(e, s)
        }
    }

    @Test
    fun testCompare() {
        val rnd = Random(1)
        for (n in 1..1000) {
            val a = rnd.nextBigNumber(n - rnd.nextInt(2), false)
            val b = rnd.nextBigNumber(n - rnd.nextInt(2), false)
            val e = a.toBigInteger().compareTo(b.toBigInteger())
            val c = a.toCPoly().compareTo(b.toCPoly())
            assertEquals(e, c)
        }
    }
}

fun Random.nextBigNumber(n: Int, neg: Boolean = nextBoolean()) = buildString {
    if (n == 0) {
        append('0')
    } else {
        if (neg) append('-')
        append(nextInt(9) + 1)
        repeat(n - 1) { append(nextInt(10)) }
    }
}
