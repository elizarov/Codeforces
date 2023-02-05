fun main() {
    repeat(readln().toInt()) {
        val m = readln().toInt()
        val a = Array(m) { IntArray(3) }
        val w = "win"
        for (i in 0 until m) {
            val s = readln()
            for (c in s) when(c) {
                'w' -> a[i][0]++
                'i' -> a[i][1]++
                'n' -> a[i][2]++
            }
        }
        val z = Array(3) { Array(3) { ArrayList<Int>() } }
        for (i in 0 until m) for (j in 0..2) {
            while (a[i][j] > 1) {
                a[i][j]--
                val k = a[i].indexOfFirst { it == 0 }
                a[i][k] = 1
                z[j][k] += i
            }
        }
        val ans = ArrayList<String>()
        for (j in 0..2) for (k in 0..2) {
            while (z[j][k].isNotEmpty() && z[k][j].isNotEmpty()) {
                val a1 = z[j][k].removeLast() + 1
                val a2 = z[k][j].removeLast() + 1
                ans += "$a1 ${w[j]} $a2 ${w[k]}"
            }
        }
        for (j in 0..2) for (k in 0..2) for (t in 0..2) {
            while (z[j][k].isNotEmpty() && z[k][t].isNotEmpty() && z[t][j].isNotEmpty()) {
                val a1 = z[j][k].removeLast() + 1
                val a2 = z[k][t].removeLast() + 1
                val a3 = z[t][j].removeLast() + 1
                ans += "$a1 ${w[j]} $a2 ${w[k]}"
                ans += "$a2 ${w[j]} $a3 ${w[t]}"
            }
        }
        println(ans.size)
        ans.forEach { println(it) }
    }
}