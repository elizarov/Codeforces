fun main() {
    val dp = HashMap<String, Int>()
    fun eval0(p: String): Int {
        dp[p]?.let { return it }
        val q = p.map {
            when(it) {
                'R' -> 'B'
                'B' -> 'R'
                else -> it
            }
        }.joinToString("")
        val v = HashSet<Int>()
        for (i in 0..p.length - 2) {
            if (p[i] == 'R' || p[i + 1] == 'R') {
                val l = q.substring(0, i) + "_"
                val r = "_" + q.substring(i + 2)
                v += eval0(l) xor eval0(r)
            }
        }
        var res = 0
        while (res in v) res++
        dp[p] = res
        return res
    }

    fun eval(p: String): Int {
        return eval0(p)
    }

    for (len in 2..7) {
        println("------")
        for (x in 0 until (1 shl len)) {
            val p = (len - 1 downTo 0).map { if (((x shr it) and 1) == 0) 'B' else 'R' }.joinToString("")
            val res = eval(p)
            println("$p -> $res")
        }
    }
    println("=======")
    for (len in 2..20) {
        val p = (0 until len).map { 'R' }.joinToString("")
        val res = eval(p)
        println("$p -> $res")
    }
    println("=======")
    for (len in 2..20) {
        val p = (0 until len).map { 'B' }.joinToString("")
        val res = eval(p)
        println("$p -> $res")
    }
    println("=======")
    for (len in 2..20) {
        val p = (0 until len).map { if (it % 2 == 0) 'R' else 'B' }.joinToString("")
        val res = eval(p)
        println("$p -> $res")
    }
    println("=======")
    for (len in 2..20) {
        val p = "R" + (0 until len).map { if (it % 2 == 0) 'R' else 'B' }.joinToString("")
        val res = eval(p)
        println("$p -> $res")
    }
    println("=======")
    for (len in 2..20) {
        var p = "R" + (0 until len).map { if (it % 2 == 0) 'R' else 'B' }.joinToString("")
        p += p.last()
        val res = eval(p)
        println("$p -> $res")
    }
}