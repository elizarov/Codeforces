fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        if (n % 2 == 0) {
            println(-1)
        } else {
            var x = n / 2
            val ans = ArrayList<Int>()
            while (x != 0) {
                if (x % 2 == 1) ans.add(2) else ans.add(1)
                x /= 2
            }
            ans.reverse()
            println(ans.size)
            println(ans.joinToString(" "))
        }
    }
}