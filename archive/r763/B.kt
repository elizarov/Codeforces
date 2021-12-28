fun main() {
    data class LR(val l: Int, val r: Int)
    repeat(readLine()!!.toInt()) {
        val n = readLine()!!.toInt()
        val a = List(n) {
            val (l, r) = readLine()!!.split(" ").map { it.toInt() }
            LR(l, r)
        }.sortedByDescending { (l, r) -> r - l }
        val m = a[0].r
        val d = HashMap<LR, Int>()
        val dr = HashMap<Int, Int>()
        val dl = HashMap<Int, Int>()
        dr[0] = m + 1
        dl[m + 1] = 0
        for ((l, r) in a.drop(1)) {
            val rr = dr[l - 1]
            if (rr != null && rr > r + 1) {
                d[LR(l, rr - 1)] = r + 1
                dr[l - 1] = r + 1
                dr[r + 1] = rr // new
                dl[rr] = r + 1
                dl[r + 1] = l - 1 // new
            } else {
                val ll = dl[r + 1]
                if (ll != null && ll < l - 1) {
                    d[LR(ll + 1, r)] = l - 1
                    dl[r + 1] = l - 1
                    dl[l - 1] = ll // new
                    dr[ll] = l - 1
                    dr[l - 1] = r + 1 // new
                }
            }
        }
        println(a.joinToString("\n") { (l, r) -> "$l $r ${d[LR(l, r)] ?: l}" })
    }
}