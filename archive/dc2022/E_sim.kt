fun main() {
    val n = 6
    val rt = BooleanArray(n)
    for (mask in 0 until (1 shl n)) {
        val ans = CharArray(2 * n + 1) { ' ' }
        for (j in 0 until n) {
            rt[j] = (1 shl j) and mask != 0
            ans[2 * j + 1] = if (rt[j]) 'R' else 'L'
        }
        val k = sim(n, rt)
        ans[2 * k] = '['
        ans[2 * k + 2] = ']'
        println(ans.concatToString())
    }

}

data class O(val k: Int, var w: Int, var r: Boolean)

fun sim(n: Int, rt: BooleanArray): Int {
    val a = MutableList(n) { i -> O(i, 1, rt[i]) }
    while (a.size > 1) {
        for (i in 0 until a.size - 1) {
            if (a[i].r && !a[i + 1].r) {
                if (a[i].w > a[i + 1].w) {
                    a[i].w += a[i + 1].w
                    a[i + 1].w = 0
                } else {
                    a[i + 1].w += a[i].w
                    a[i].w = 0
                }
            }
        }
        a.removeIf { it.w == 0 }
        if (!a[0].r) a[0].r = true
        if (a.last().r) a.last().r = false
    }
    return a[0].k
}