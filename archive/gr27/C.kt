fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val bot = ArrayList<Int>()
        var ans = 0
        if (n % 2 == 0) {

        } else {
            var rem = n
            while (rem != 0) {
                bot.add(n)
                bot.add(n - 1)
                val bits = n or (n - 1)
                rem = rem and bits.inv()
            }
            bot.reverse()
            ans = n
        }
        val a = IntArray(n)
        val botSet = bot.toSet()
        var j = 0
        for (i in 1..n) if (i !in botSet) {
            a[j++] = i
        }
        for (i in bot) {
            a[j++] = i
        }
        println(ans)
        println(a.joinToString(" "))
    }
}