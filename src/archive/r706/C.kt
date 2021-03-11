package archive.r706

fun main() = System.out.bufferedWriter().run {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val a = Array(n) { readLine()!!.toCharArray() }
        for (i in 1 until n step 3) {
            a[i].fill('X')
            if (i + 3 < n) {
                var found = false
                for (k in 0 until m) {
                    if (a[i + 1][k] == 'X') {
                        a[i + 2][k] = 'X'
                        found = true
                        break
                    }
                    if (a[i + 2][k] == 'X') {
                        a[i + 1][k] = 'X'
                        found = true
                        break
                    }
                }
                if (!found) {
                    a[i + 1][0] = 'X'
                    a[i + 2][0] = 'X'
                }
            }
        }
        when {
            n == 1 -> {
                for (k in 0 until m) {
                    a[0][k] = 'X'
                }
            }
            n % 3 == 1 -> {
                for (k in 0 until m) {
                    if (a[n - 1][k] == 'X') {
                        a[n - 2][k] = 'X'
                    }
                    if (a[n - 2][k] == 'X') {
                        a[n - 1][k] = 'X'
                    }
                }
            }
        }
        a.forEach { println(it.concatToString()) }
    }
}