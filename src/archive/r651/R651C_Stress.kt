package archive.r651

fun main() {
    val k = 10000
    val s = BooleanArray(k + 1)
    s[1] = false
    for (i in 2..k) {
        val a = solveC(i)
        var b = !s[i - 1]
        if (!b) {
            for (j in 3..i step 2) {
                if (i % j == 0 && !s[i / j]) {
                    b = true
                    break
                }
            }
        }
        s[i] = b
        if (a != b) {
            println("i = $i")
            println("out = $a")
            println("ans = $b")
            break
        }
    }
}