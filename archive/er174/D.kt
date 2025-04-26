import java.util.TreeSet

fun main() {
    repeat(readln().toInt()) {
        val s = readln()
        val n = s.length
        val ans1 = run {
            val idx = Array(26) { TreeSet<Int>() }
            for (i in 0..<n) idx[s[i] - 'a'].add(i)
            val c = s.toCharArray()
            var lt = n
            var rt = -1
            var i = 0
            var j = n - 1
            while (i < j) {
                if (c[i] != c[j]) {
                    lt = minOf(lt, i)
                    val k = idx[c[j] - 'a'].tailSet(i).first()
                    rt = maxOf(rt, k)
                    check(c[k] == c[j])
                    idx[c[i] - 'a'].remove(i)
                    idx[c[k] - 'a'].remove(k)
                    c[k] = c[i].also { c[i] = c[j] }
                    idx[c[i] - 'a'].add(i)
                    idx[c[k] - 'a'].add(k)
                }
                i++
                j--
            }
            (rt - lt + 1).coerceAtLeast(0)
        }
        val ans2 = run {
            val idx = Array(26) { TreeSet<Int>() }
            for (i in 0..<n) idx[s[i] - 'a'].add(i)
            val c = s.toCharArray()
            var lt = n
            var rt = -1
            var i = 0
            var j = n - 1
            while (i < j) {
                if (c[i] != c[j]) {
                    rt = maxOf(rt, j)
                    val k = idx[c[i] - 'a'].headSet(j).last()
                    lt = minOf(lt, k)
                    check(c[k] == c[i])
                    idx[c[j] - 'a'].remove(j)
                    idx[c[k] - 'a'].remove(k)
                    c[k] = c[j].also { c[j] = c[i] }
                    idx[c[j] - 'a'].add(j)
                    idx[c[k] - 'a'].add(k)
                }
                i++
                j--
            }
            (rt - lt + 1).coerceAtLeast(0)
        }
        println(minOf(ans1, ans2))
    }
}