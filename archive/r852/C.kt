fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        var l = 0
        var r = n - 1
        var min = 1
        var max = n
        while (l < r && (a[l] == min || a[l] == max || a[r] == min || a[r] == max)) {
            var min1 = min
            var max1 = max
            if (a[l] == min) {
                l++
                min1++
            } else if (a[l] == max) {
                l++
                max1--
            }
            if (a[r] == min) {
                r--
                min1++
            } else if (a[r] == max) {
                r--
                max1--
            }
            min = min1
            max = max1
        }
        if (l < r) {
            println("${l + 1} ${r + 1}")
        } else {
            println(-1)
        }
    }
}