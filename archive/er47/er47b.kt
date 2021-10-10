package archive.er47

fun main(args: Array<String>) {
    val s = readLine()!!.toCharArray()
    val n = s.size
    var k = 0
    var c0 = 0
    var c1 = 0
    while (k < n && s[k] != '2') {
        when (s[k]) {
            '0' -> c0++
            '1' -> c1++
        }
        k++
    }
    k = 0
    repeat(c0) { s[k++] = '0' }
    repeat(c1) { s[k++] = '1' }
    var j = n - 1
    for (i in n - 1  downTo k) {
        if (s[i] != '1') s[j--] = s[i]
    }
    for (i in k..j) s[i] = '1'
    println(String(s))
}