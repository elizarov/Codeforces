package archive.r504

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!
    val t = readLine()!!
    println(if (match(s, t)) "YES" else "NO")
}

fun match(s: String, t: String): Boolean {
    val i = s.indexOf('*')
    if (i < 0) return s == t
    val a = s.substring(0, i)
    val b = s.substring(i + 1)
    return t.startsWith(a) && t.endsWith(b) && t.length >= a.length + b.length
}
