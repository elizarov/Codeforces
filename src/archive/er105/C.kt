package archive.er105

fun main() {
    repeat(readLine()!!.toInt()) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        val b = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        var c0 = 0
        var i = 0
        var j = 0
        while (i < n && j < m) {
            when {
                a[i] == b[j] -> {
                    c0++
                    i++
                    j++
                }
                a[i] < b[j] -> i++
                else -> j++
            }

        }
        var ansL = 0
        var delta = 0
        var ts = 0
        i = -1; while(i < n - 1 && a[i + 1] < 0) i++
        j = -1; while(j < m - 1 && b[j + 1] < 0) j++
        var k = j
        while (i >= 0 || j >= 0) {
            val pPos = if (i >= 0) a[i] else Int.MIN_VALUE
            val pEnter = if (j >= 0) b[j] else Int.MIN_VALUE
            val pLeave = (if (k >= 0) b[k] else (Int.MIN_VALUE + ts)) - ts
            when (maxOf(pPos, pEnter, pLeave)) {
                pPos -> {
                    ts++
                    if (j >= 0 && a[i] == b[j]) delta--
                    i--
                }
                pLeave -> {
                    k--
                    if (ts > 0) delta--
                }
                pEnter -> {
                    j--
                    if (ts > 0) {
                        delta++
                        ansL = maxOf(ansL, delta)
                    }
                }
            }
        }

        var ansR = 0
        delta = 0
        ts = 0
        i = 0; while(i < n && a[i] < 0) i++
        j = 0; while(j < m && b[j] < 0) j++
        k = j
        while (i < n || j < m) {
            val pPos = if (i < n) a[i] else Int.MAX_VALUE
            val pEnter = if (j < m) b[j] else Int.MAX_VALUE
            val pLeave = (if (k < m) b[k] else (Int.MAX_VALUE - ts)) + ts
            when (minOf(pPos, pEnter, pLeave)) {
                pPos -> {
                    ts++
                    if (j < m && a[i] == b[j]) delta--
                    i++
                }
                pLeave -> {
                    k++
                    if (ts > 0) delta--
                }
                pEnter -> {
                    j++
                    if (ts > 0) {
                        delta++
                        ansR = maxOf(ansR, delta)
                    }
                }
            }
        }

        println(c0 + ansL + ansR)
    }
}
