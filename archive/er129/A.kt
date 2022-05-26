fun main() {
    repeat(readLine()!!.toInt()) {
        val n0 = readln().toInt()
        val a = readln().split(" ").map { it.toInt() }.distinct().sorted()
        val m0 = readln().toInt()
        val b = readln().split(" ").map { it.toInt() }.distinct().sorted()
        val k = 50
        val aw = BooleanArray(k + 1)
        val bw = BooleanArray(k + 1)
        var ap = a.size
        var bp = b.size
        for (j in k downTo 0) {
            while(ap > 0 && a[ap - 1] > j) ap--
            aw[j] = false
            for(i in ap until a.size) if (!bw[a[i]]) {
                aw[j] = true
                break
            }
            while(bp > 0 && b[bp - 1] > j) bp--
            bw[j] = false
            for(i in bp until b.size) if (!aw[b[i]]) {
                bw[j] = true
                break
            }
        }
        println(if(aw[0]) "Alice" else "Bob")
        println(if(bw[0]) "Bob" else "Alice")
    }
}