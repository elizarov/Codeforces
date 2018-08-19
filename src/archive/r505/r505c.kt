package archive.r505

import kotlin.math.*

fun main(args: Array<String>) {
    val s = readLine()!!.toCharArray()
    val n = s.size
    var mz = 1
    var cz = 1
    for (i in 1 until n) {
        if (s[i] != s[i - 1]) {
            cz++
            mz = max(mz, cz)
        } else {
            cz = 1
        }
    }
    if (cz == n) {
        println(n)
        return
    }
    var lz = 1
    while (s[lz] != s[lz - 1]) lz++
    var rz = 1
    while (s[n - rz] != s[n - rz - 1]) rz++
    if (s[0] != s[n - 1]) {
        mz = max(mz, lz + rz)
    }
    println(mz)

}