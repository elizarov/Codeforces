fun main() {
    val mod = 998244353
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var cnt1 = 0
        var cnt2 = 0
        var cnt3 = 0
        for (i in 0..<n) {
            when (a[i]) {
                1 -> cnt1++
                2 -> cnt2 = (cnt2 * 2 + cnt1) % mod
                3 -> cnt3 = (cnt3 + cnt2) % mod
            }
        }
        println(cnt3)
    }
}