package archive.r564
private const val MOD = 998244352

fun Int.modInv(n: Int): Int {
    var t = 0
    var r = n
    var newT = 1
    var newR = this
    while (newR != 0) {
        val q = r / newR
        t = newT.also { newT = t - q * newT }
        r = newR.also { newR = r - q * newR }
    }
    check(r == 1)
    if (t < 0) t += n
    return t
}

fun main() {
    // todo:
}