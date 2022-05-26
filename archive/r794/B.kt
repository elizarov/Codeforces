import java.util.PriorityQueue

fun main() {
    val s = List(readLine()!!.toInt()) {
        val (a, b, c, d) = readln().split(" ").map { it.toInt() }
        val s = readln()
        if (solveB(s, a, b, c, d)) "YES" else "NO"
    }
    println(s.joinToString("\n"))
}

val Boolean.int: Int get() = if (this) 1 else 0

fun solveB(s: String, a: Int, b: Int, ab: Int, ba: Int): Boolean {
    val n = s.length
    val a0 = s.count { it == 'A' }
    val b0 = s.count { it == 'B' }
    if (a + ab + ba != a0 || b + ab + ba != b0) return false
    data class ABP(val i: Int, val o: Int)
    val f = BooleanArray(n)
    val l = BooleanArray(n)
    val r = BooleanArray(n)
    for (i in 0..n - 2) {
        if (s[i] != 'A' || s[i + 1] != 'B') continue
        f[i] = true
        l[i] = i > 0 && s[i - 1] == 'B'
        r[i] = i < n - 2 && s[i + 2] == 'A'
    }
    val zl = IntArray(n) { Int.MAX_VALUE / 2 }
    val zr = IntArray(n) { Int.MAX_VALUE / 2 }
    for (i in 0..n - 2) if (f[i]) {
        if (!l[i]) zl[i] = 0
        if (l[i] && i > 1 && s[i - 2] == 'A') zl[i] = zl[i - 2] + 1
    }
    for (i in n - 2 downTo 0) if (f[i]) {
        if (!r[i]) zr[i] = 0
        if (r[i] && i < n - 3 && s[i + 3] == 'B') zr[i] = zr[i + 2] + 1
    }
    val pq: PriorityQueue<ABP> = PriorityQueue(compareBy(ABP::o))
    fun add(i: Int) {
        if (!f[i]) return
        val o = when (l[i].int + r[i].int) {
            0 -> 0
            1 -> 1 + (if (l[i]) zl[i] else zr[i])
            else -> Int.MAX_VALUE
        }
        pq += ABP(i, o)
    }
    for (i in 0..n - 2) add(i)
    val bl = BooleanArray(n)
    var abc = 0
    while (abc < ab) {
        val p = pq.poll() ?: return false
        val i = p.i
        if (!f[i]) continue
        f[i] = false
        //println("AB $i")
        if (l[i]) bl[i - 1] = true
        if (r[i]) bl[i + 1] = true
        abc++
        if (l[i] && i > 1 && s[i - 2] == 'A') {
            r[i - 2] = false
            add(i - 2)
        }
        if (r[i] && i < n - 3 && s[i + 3] == 'B') {
            l[i + 2] = false
            add(i + 2)
        }
    }
    if (ba == 0) return true
    var bac = 0
    for (i in 0..n - 2) {
        if (s[i] != 'B' || s[i + 1] != 'A') continue
        if (bl[i]) continue
        bac++
        //println("BA $i")
        if (bac >= ba) return true
    }
    return false
}

/*

1
1 1 2 3
ABABABBAABAB
^^        ^^


1
2 3 5 4
AABAABBABAAABABBABBBABB

2 3 5 4
  vv  vvvv     vv
AABAABBABAAABABBABBBABB
 ^^ ^^              ^^


 */