fun main() {
    repeat(readln().toInt()) {
        val s = readln()
        var (a, b, ab, ba) = readln().split(" ").map { it.toInt() }
        val n = s.length
        var i = 0

        while (i < n) {
            when(s[i]) {
                'A' -> {
                    if (i < n - 1 && s[i + 1] == 'B' && ab > 0) {
                        ab--
                        i += 2
                    } else {
                        a--
                        i++
                    }
                }
                'B' -> {
                    if (i < n - 1 && s[i + 1] == 'A' && ba > 0) {
                        ba--
                        i += 2
                    } else {
                        b--
                        i++
                    }
                }
            }
        }
        println(if (a >= 0 && b >= 0) "YES" else "NO")
    }
}

/*

AB AB BA A B BA AB x
1 1  2  3
a b ab ba

A BA B BA AB BA AB !!

ABAB|BA|AB|BA|AB

 */