package algo

import java.math.*

fun gcd(x: BigInteger, y: BigInteger): BigInteger =
    if (y == BigInteger.ZERO) x else gcd(y, x % y)

data class BigQ(val p: BigInteger, val q: BigInteger): Comparable<BigQ> {
    override fun compareTo(other: BigQ): Int {
        val g = gcd(q, other.q)
        val x = other.q / g * p
        val y = q / g * other.p
        return x.compareTo(y)
    }

    operator fun plus(other: BigQ): BigQ {
        val g = gcd(q, other.q)
        return q(other.q / g * p + q / g * other.p, q / g * other.q)
    }

    operator fun minus(other: BigQ): BigQ {
        val g = gcd(q, other.q)
        return q(other.q / g * p - q / g * other.p, q / g * other.q)
    }

    fun abs(): BigQ = BigQ(p.abs(), q)

    override fun toString(): String = "$p/$q"
}

fun q(x: BigInteger, y: BigInteger): BigQ {
    val g = gcd(x.abs(), y)
    return BigQ(x / g, y / g)
}

// Continuous fraction
fun BigQ.continuousFraction(): Sequence<BigInteger> = sequence {
    var a = p
    var b = q
    while (b != BigInteger.ZERO) {
        val r = a / b
        yield(r)
        a -= r * b
        a = b.also { b = a }
    }
}

fun BigQ.limitDenominator(y: BigInteger): BigQ {
    val c = continuousFraction().iterator()
    var ph = BigInteger.ZERO
    var pk = BigInteger.ONE
    var h = BigInteger.ONE
    var k = BigInteger.ZERO
    while (c.hasNext()) {
        val a = c.next()
        val nh = a * h + ph
        val nk = a * k + pk
        // convergent h / k -> nh / nk
        if (nk > y) {
            // next denominator is too big, find the best approx here
            // b * k + pk <= y
            val b = (y - pk) / k
            val c0 = BigQ(h, k) // previous convergent
            val c1 = BigQ(b * h + ph, b * k + pk) // candidate convergent
            if ((c1 - this).abs() < (c0 - this).abs())
                return c1
            else
                return c0
        }
        ph = h
        pk = k
        h = nh
        k = nk
    }
    return BigQ(h, k) // the last convergent is Ok
}