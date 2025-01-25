package algo

// Extended Euclidean algorithm
// a * r + b * s = g

data class EgcdResult(val g: Long, val r: Long, val s: Long)

fun egcd(a: Long, b: Long): EgcdResult {
    var oldR = a
    var r = b
    var oldS = 1L
    var s = 0L
    var oldT = 0L
    var t = 1L
    while (r != 0L) {
        val q = oldR / r
        oldR = r.also { r = oldR - q * r }
        oldS = s.also { s = oldS - q * s }
        oldT = t.also { t = oldT - q * t }
    }
    return EgcdResult(oldR, oldS, oldT)
}