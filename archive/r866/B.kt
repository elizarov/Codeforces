fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        data class D(val i: Int, val x: Int, val y: Int)
        val ds = Array(2) { ArrayList<D>() }
        repeat (n) { i ->
            val (a, b) = readln().split(" ").map { it.toInt() }
            ds[0] += D(i, a, b)
            ds[1] += D(i, b, a)
        }
        for (d in ds) d.sortByDescending { it.x }
        class Ans {
            val a = LongArray(2)
            override fun equals(other: Any?): Boolean = other is Ans && other.a.contentEquals(a)
            override fun hashCode(): Int = a.contentHashCode()
        }
        val res = LinkedHashSet<Ans>()
        val u = BooleanArray(n)
        loop@for (s in 0..1) {
            u.fill(false)
            val ans = Ans()
            val xs = ds[s][0].x
            ans.a[s] = xs.toLong()
            val i = IntArray(2)
            var c = 1 - s
            while (i[s] < n && ds[s][i[s]].x == xs) {
                u[ds[s][i[s]].i] = true
                ans.a[c] += ds[s][i[s]].y.toLong()
                i[s]++
            }
            while (i[c] < n && u[ds[c][i[c]].i]) i[c]++
            if (i[c] >= n) {
                res += ans
                continue
            }
            var ys = xs
            var xc = ds[c][i[c]].x
            ans.a[c] += xc.toLong()
            while (true) {
                var ok = false
                while (i[c] < n && (u[ds[c][i[c]].i] || ds[c][i[c]].x == xc)) {
                    if (!u[ds[c][i[c]].i]) {
                        u[ds[c][i[c]].i] = true
                        ys -= ds[c][i[c]].y
                    }
                    i[c]++
                    ok = true
                }
                if (ys < 0 || !ok) continue@loop
                if (i[c] >= n) {
                    if (ys == 0) res += ans
                    continue@loop
                }
                c = 1 - c
                xc = ys.also { ys = xc }
            }
        }
        println(res.size)
        res.forEach { println("${it.a[0]} ${it.a[1]}") }
    }
}