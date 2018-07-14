package algo

import kotlin.math.*

val REV = arrayOfNulls<IntArray?>(32)

fun rev(n: Int): IntArray {
    require(n and (n - 1) == 0) { "Must be power of two" }
    val k = Integer.numberOfLeadingZeros(n)
    return REV[k] ?: computeRev(n).also { REV[k] = it }
}

private fun computeRev(n: Int): IntArray {
    val rev = IntArray(n)
    var j = 0
    for (i in 1 until n) {
        var bit = n shr 1
        while (j >= bit) {
            j -= bit
            bit = bit shr 1
        }
        j += bit
        rev[i] = j
    }
    return rev
}

// Representing numbers as complex polys
private const val BASE = 10
private const val GROUP = 2
private const val MODULO = 100 // BASE ** GROUP

// rounds it up to power of two
fun fftSize(n: Int) =
    Integer.highestOneBit(n.coerceAtLeast(2) - 1) * 2

// Complex polynomial for FFT
class CPoly private constructor(val a: DoubleArray) {
    val maxN = a.size / 2 // allocated
    var n: Int = maxN // current no. of complex numbers, power of two (for fft)
        set(n) {
            require(n in 1..maxN) { "$n is out of range 1..$maxN"}
            require(n and (n - 1) == 0) { "$n must be power of two"}
            field = n
        }

    var nz: Int = 0 // number of non-zero components

    constructor(n: Int) :
        this(DoubleArray(2 * fftSize(n)))

    fun copy() = CPoly(a.copyOf())
    
    // bit flip
    private fun flip() {
        val rev = rev(n)
        for (i in 1 until n) {
            val j = rev[i]
            if (i < j) swap(i, j)
        }
    }

    fun fft(inverse: Boolean = false) {
        flip()
        var len = 2
        while (len <= n) {
            val ang = (if (inverse) -2 * PI else 2 * PI) / len
            // w0 := e^(i * ang)
            val reW0 = cos(ang)
            val imW0 = sin(ang)
            for (i in 0 until n step len) {
                // w := 1 
                var reW = 1.0
                var imW = 0.0
                val l2 = len / 2
                for (j in 0 until l2) {
                    // u := c[i + j]
                    val reU = a[2 * (i + j)]
                    val imU = a[2 * (i + j) + 1]
                    // t := c[i + j + l2]
                    val reT = a[2 * (i + j + l2)]
                    val imT = a[2 * (i + j + l2) + 1]
                    // v := t * w
                    val reV = reT * reW - imT * imW
                    val imV = reT * imW + imT * reW
                    // c[i + j] := u + v
                    a[2 * (i + j)] = reU + reV
                    a[2 * (i + j) + 1] = imU + imV
                    // c[i + j + l2] := u - v
                    a[2 * (i + j + l2)] = reU - reV
                    a[2 * (i + j + l2) + 1] = imU - imV
                    // wn := w * w0
                    val reWN = reW * reW0 - imW * imW0
                    val imWN = reW * imW0 + imW * reW0
                    // w := wn
                    reW = reWN
                    imW = imWN
                }
            }
            len *= 2
        }
        if (inverse) scaleInv()
    }

    private fun scaleInv() {
        for (i in 0 until 2 * n) a[i] = a[i] / n
    }

    fun ifft() = fft(inverse = true)

    private fun swap(i: Int, j: Int) {
        val re = a[2 * i]
        val im = a[2 * i + 1]
        a[2 * i] = a[2 * j]
        a[2 * i + 1] = a[2 * j + 1]
        a[2 * j] = re
        a[2 * j + 1] = im
    }

    fun mulEach(q: CPoly) {
        for (i in 0 until 2 * n step 2) {
            val re0 = a[i]
            val im0 = a[i + 1]
            val re1 = q.a[i]
            val im1 = q.a[i + 1]
            a[i] = re0 * re1 - im0 * im1
            a[i + 1] = re0 * im1 + im0 * re1
        }
    }

    fun sqrEach() {
        for (i in 0 until 2 * n step 2) {
            val re = a[i]
            val im = a[i + 1]
            a[i] = re * re - im * im
            a[i + 1] = 2 * re * im
        }
    }

    fun fastSqr() {
        n = fftSize(2 * nz)
        fft()
        sqrEach()
        ifft()
        normalize()
    }

    fun fastMul(q: CPoly) {
        val n = fftSize(nz + q.nz)
        this.n = n
        q.n = n
        fft()
        q.fft()
        mulEach(q)
        ifft()
        normalize()
    }

    fun normalize() {
        var cur = 0
        var maxi = 0
        for (i in 0 until 2 * n step 2) {
            cur += a[i].roundToInt()
            val d = cur % MODULO
            a[i] = d.toDouble()
            a[i + 1] = 0.0
            cur /= MODULO
            if (d != 0) maxi = i + 2
        }
        nz = maxi / 2
    }

    fun shortMul(b: Int) {
        var cur = 0
        var i = 0
        val limit = 2 * nz
        while (i < limit || cur != 0) {
            cur += a[i].toInt() * b
            a[i] = (cur % MODULO).toDouble()
            cur /= MODULO
            i += 2
        }
        nz = i / 2
    }

    fun assign(q: CPoly) {
        require(maxN == q.maxN)
        System.arraycopy(q.a, 0, a, 0, 2 * maxN)
        n = q.n
        nz = q.nz
    }

    operator fun compareTo(q: CPoly) : Int {
        nz.compareTo(q.nz).let { if (it != 0) return it }
        for (i in 2 * (nz - 1) downTo 0 step 2) {
            val d = a[i].compareTo(q.a[i])
            if (d != 0) return d
        }
        return 0
    }

    // Convert complex poly back to string
    override fun toString(): String = buildString {
        var m = n - 1
        while (m >= 0 && a[2 * m].absoluteValue < 0.5) m--
        if (m < 0) {
            append('0')
            return@buildString
        }
        val neg = a[2 * m] < 0
        var cur = 0
        var i = 0
        loop@ while (true) {
            if (i <= m) cur += a[2 * i].roundToInt().absoluteValue
            for (k in 0 until GROUP) {
                val d = cur % BASE
                append('0' + d)
                cur /= BASE
                if (cur == 0 && i >= m) break@loop
            }
            i++
        }
        if (neg) append('-')
        reverse()
    }
}

fun fastMul(a: String, b: String): String {
    val n = sizeOfCPoly(a.numberOfDigits() + b.numberOfDigits())
    val p = a.toCPoly(n)
    val q = b.toCPoly(n)
    p.fft()
    q.fft()
    p.mulEach(q)
    p.ifft()
    return p.toString()
}

// a ** b
fun numberOfPowDigits(a: Int, b: Int): Int =
    (log10(a.toDouble()) * b + 1).toInt()

// a ** b
fun fastPow(a: Int, b: Int, n: Int = sizeOfCPoly(numberOfPowDigits(a, b))): CPoly {
    val p = a.toCPoly(n)
    val r = 1.toCPoly(n)
    var pow = b
    while (pow != 0) {
        val last = pow == 1
        p.n = fftSize(if (last) p.maxN else 2 * p.nz)
        p.fft()
        if ((pow and 1) != 0) {
            r.n = p.n
            r.fft()
            r.mulEach(p)
            r.ifft()
            r.normalize()
        }
        if (last) break
        p.sqrEach()
        p.ifft()
        p.normalize()
        pow = pow shr 1
    }
    return r
}

fun String.numberOfDigits(): Int = if (get(0) == '-') length - 1 else length

fun sizeOfCPoly(numberOfDigits: Int): Int = (numberOfDigits + GROUP - 1) / GROUP

// Converts decimal integer to complex polynomial for fft-math
fun String.toCPoly(n: Int = sizeOfCPoly(numberOfDigits())): CPoly {
    val neg = get(0) == '-'
    val j0 = if (neg) 1 else 0
    val p = CPoly(n)
    var j = length
    var i = 0
    while (j > j0) {
        var cur = 0
        for (k in (j - GROUP).coerceAtLeast(j0) until j) {
            val d = get(k) - '0'
            require(d in 0..(BASE - 1))
            cur = cur * BASE + d
        }
        if (neg) cur = -cur
        p.a[i] = cur.toDouble()
        j -= GROUP
        i += 2
    }
    p.nz = i / 2
    return p
}

fun Int.toCPoly(n: Int = sizeOfCPoly(10)): CPoly {
    var cur = this
    val p = CPoly(n)
    var i = 0
    while (cur != 0) {
        p.a[i] = (cur % MODULO).toDouble()
        cur /= MODULO
        i += 2
    }
    p.nz = i / 2
    return p
}