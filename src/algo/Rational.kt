package algo

import kotlin.math.*

fun gcd(x: Long, y: Long): Long =
    if (y == 0L) x else gcd(y, x % y)

data class Q(val p: Long, val q: Long): Comparable<Q> {
    override fun compareTo(other: Q): Int {
        val g = gcd(q, other.q)
        val x = other.q / g * p
        val y = q / g * other.p
        return x.compareTo(y)
    }

    operator fun plus(other: Q): Q {
        val g = gcd(q, other.q)
        return q(other.q / g * p + q / g * other.p, q / g * other.q)
    }

    operator fun minus(other: Q): Q {
        val g = gcd(q, other.q)
        return q(other.q / g * p - q / g * other.p, q / g * other.q)
    }

    fun abs(): Q = Q(p.absoluteValue, q)

    override fun toString(): String = "$p/$q"
}

fun q(x: Long, y: Long): Q {
    val g = gcd(x.absoluteValue, y)
    return Q(x / g, y / g)
}

fun q(x: Int, y: Int): Q = q(x.toLong(), y.toLong())

// Continuous fraction
fun Q.continuousFraction(): Sequence<Long> = sequence {
    var a = p
    var b = q
    while (b != 0L) {
        val r = a / b
        yield(r)
        a -= r * b
        a = b.also { b = a }
    }
}

fun Q.convergents(): Sequence<Q> = sequence {
    val c = continuousFraction().iterator()
    var ph = 0L
    var pk = 1L
    var h = 1L
    var k = 0L
    while (c.hasNext()) {
        val a = c.next()
        val nh = a * h + ph
        val nk = a * k + pk
        ph = h
        pk = k
        h = nh
        k = nk
        yield(Q(h, k))
    }
}

// continuous to regular
fun List<Long>.toFraction(): Q {
    var ph = 0L
    var pk = 1L
    var h = 1L
    var k = 0L
    for (a in this) {
        val nh = a * h + ph
        val nk = a * k + pk
        ph = h
        pk = k
        h = nh
        k = nk
    }
    return Q(h, k)
}

// finds best fraction z: x < z < y
fun bestInInterval(x: Q, y: Q): Q {
    check(x < y)
    if (x.p >= x.q) {  // x >= 1
        val d = x.p / x.q
        val r = bestInInterval(Q(x.p - d * x.q, x.q), Q(y.p - d * y.q, y.q))
        return Q(r.p + d * r.q, r.q)
    }
    // x < 1
    if (y.p > y.q) { // y > 1
        return Q(1, 1)
    }
    // x < y < 1
    val r = bestInInterval(Q(y.q, y.p), Q(x.q, x.p))
    return Q(r.q, r.p)
}

fun Q.limitDenominator(y: Long): Q {
    val c = continuousFraction().iterator()
    var ph = 0L
    var pk = 1L
    var h = 1L
    var k = 0L
    while (c.hasNext()) {
        val a = c.next()
        val nh = a * h + ph
        val nk = a * k + pk
        // convergent h / k -> nh / nk
        if (nk > y) {
            // next denominator is too big, find the best approx here
            // b * k + pk <= y
            val b = (y - pk) / k
            val c0 = Q(h, k) // previous convergent
            val c1 = Q(b * h + ph, b * k + pk) // candidate convergent
            // may overflow here
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
    return Q(h, k) // the last convergent is Ok
}

fun main() {
    println(bestInInterval(Q(1, 3), Q(1, 2)))
}