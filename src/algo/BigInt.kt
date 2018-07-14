package algo

import kotlin.math.*

private const val BASE = 10
private const val GROUP = 9
private const val MODULO = 1_000_000_000L

class BigInt(capacity: Int) {
    var n = 0
    val a = IntArray(capacity)

    operator fun plusAssign(b: Int) {
        this += b.toLong()
    }

    operator fun plusAssign(b: Long) {
        var rem = b
        var i = 0
        while (rem != 0L) {
            rem += a[i]
            a[i] = (rem % MODULO).toInt()
            rem /= MODULO
            i++
        }
        n = max(n, i)
    }

    operator fun timesAssign(b: Int) {
        val bl = b.toLong()
        var rem = 0L
        var i = 0
        while (i < n || rem != 0L) {
            rem += a[i] * bl
            a[i] = (rem % MODULO).toInt()
            rem /= MODULO
            i++
        }
        n = max(n, i)
    }

    operator fun plusAssign(b: BigInt) {
        var rem = 0L
        var i = 0
        while (i < n || i < b.n || rem != 0L) {
            rem += a[i] + b.a[i]
            a[i] = (rem % MODULO).toInt()
            rem /= MODULO
            i++

        }
        n = max(n, i)
    }

    // this += b * c
    fun addMul(b: BigInt, c: BigInt) {
        for (j in 0 until c.n) {
            val cjl = c.a[j].toLong()
            var rem = 0L
            var i = 0
            var k = j
            while (i < b.n) {
                rem += a[k] + b.a[i] * cjl
                a[k] = (rem % MODULO).toInt()
                rem /= MODULO
                i++
                k++
            }
            while (rem != 0L) {
                rem += a[k]
                a[k] = (rem % MODULO).toInt()
                rem /= MODULO
                k++
            }
            n = max(n, k)
        }
    }

    override fun toString(): String = buildString {
        var m = n - 1
        while (m >= 0 && a[m] == 0) m--
        if (m < 0) {
            append('0')
            return@buildString
        }
        var cur = 0
        var i = 0
        loop@ while (true) {
            if (i <= m) cur += a[i]
            for (k in 0 until GROUP) {
                val d = cur % BASE
                append('0' + d)
                cur /= BASE
                if (cur == 0 && i >= m) break@loop
            }
            i++
        }
        reverse()
    }
}

fun sizeOfBigInt(numberOfDigits: Int): Int = (numberOfDigits + GROUP - 1) / GROUP

fun Int.toBigInt(n: Int = sizeOfBigInt(10)): BigInt {
    val x = BigInt(n)
    x += this
    return x
}

fun Long.toBigInt(n: Int = sizeOfBigInt(20)): BigInt {
    val x = BigInt(n)
    x += this
    return x
}

fun String.toBigInt(n: Int = sizeOfBigInt(length)): BigInt {
    val x = BigInt(n)
    var j = length
    var i = 0
    while(true) {
        var cur = 0
        for (k in (j - GROUP).coerceAtLeast(0) until j) {
            val d = get(k) - '0'
            require(d in 0..(BASE - 1))
            cur = cur * BASE + d
        }
        x.a[i++] = cur
        j -= GROUP
        if (j <= 0) break
    }
    x.n = i
    return x
}