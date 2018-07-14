package algo

import org.junit.*
import java.util.*

class BigIntTest {
    @Test
    fun testString() {
        val rnd = Random(1)
        for (n in 0..1000) {
            val s = rnd.nextBigNumber(n, neg = false)
            val x = s.toBigInt()
            val s2 = x.toString()
            Assert.assertEquals(s, s2)
        }
    }

    @Test
    fun testMulRandomSize() {
        val rnd = Random(2)
        for (n in 2..1000) {
            val a = rnd.nextBigNumber(rnd.nextInt(n) + 1, neg = false)
            val b = rnd.nextBigNumber(rnd.nextInt(n) + 1, neg = false)
            val c = a.toBigInteger().multiply(b.toBigInteger()).toString()

            val n = sizeOfBigInt(a.length + b.length)
            val x = a.toBigInt(n)
            val y = b.toBigInt(n)
            val z = BigInt(n)
            z.addMul(x, y)
            val d = z.toString()
            
            Assert.assertEquals("On ${a.length} + ${b.length} digits", c, d)
        }
    }
}