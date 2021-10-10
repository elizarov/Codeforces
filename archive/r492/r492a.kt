package archive.r492

fun main(args: Array<String>) {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val a = Array(4) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
    val ms = ArrayList<Mv>()
    while (true) {
        var e: Pt? = null
        var rem = false
        for (i in 1..2) for(j in 0 until n) {
            val car = a[i][j]
            if (car != 0) {
                val i2 = if(i == 1) 0 else 3
                if (a[i2][j] == car) {
                    ms += Mv(car, i2, j)
                    a[i][j] = 0
                    if (e == null) e = Pt(i, j)
                } else {
                    rem = true
                }
            } else {
                if (e == null) e = Pt(i, j)
            }
        }
        if (!rem) break
        if (e == null) {
            println(-1)
            return
        }
        var ci = e.r
        var cj = e.c
        while (true) {
            var ni = ci
            var nj = cj
            when (ci) {
                1 -> if (cj == 0) {
                    ni = 2
                } else {
                    nj--
                }
                2 -> {
                    if (cj == n - 1) {
                        ni = 1
                    } else {
                        nj++
                    }
                }
                else -> error("!!!")
            }
            if (ni == e.r && nj == e.c) break
            val car = a[ni][nj]
            if (car != 0) {
                ms += Mv(car, ci, cj)
                a[ni][nj] = 0
                a[ci][cj] = car
            }
            ci = ni
            cj = nj
        }
    }
    println(ms.size)
    ms.forEach { m ->
        println("${m.car} ${m.r + 1} ${m.c + 1}")
    }

}

class Mv(val car: Int, val r: Int, val c: Int)
class Pt(val r: Int, val c: Int)