fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }
        val b = ArrayList<Long>()
        for (k in 1..n) {
            val ak = a.take(k).sorted()
            var ans = ak.sumOf { it.toLong() }
            var target = 0
            print("+ ${a[k - 1]} ")
            for (x in ak) {
                if (x > target) {
                    target++
                    print('-')
                } else {
                    print('x')
                }
                ans -= target
            }
            println()
            b += ans
        }
        println(b.joinToString(" "))
    }
}