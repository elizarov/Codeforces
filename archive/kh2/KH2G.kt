package archive.kh2

import java.io.*
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

fun main() = bufferOut {
    val t = readLine()!!.toInt()
    repeat(t) { case() }
}

private fun PrintWriter.case() {
    val n = readLine()!!.toInt()
    val c = readLine()!!.split(" ").map { it.toInt() }
    val d = readLine()!!.split(" ").map { it.toInt() }
    val gf = IntArray(n) { -1 }
    val gn = IntArray(2 * n)
    val gv = IntArray(2 * n)
    var vc = 0
    fun edge(x: Int, y: Int) {
        gv[vc] = y
        gn[vc] = gf[x]
        gf[x] = vc
        vc++
    }
    repeat(n - 1) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        edge(x, y)
        edge(y, x)
    }
    val parent = IntArray(n) { -1 }
    val dfs = recursiveFunction<Int, Node> { p ->
        var i = gf[p]
        val cur = Node(p, c[p] != d[p])
        while (i >= 0) {
            val q = gv[i]
            if (q != parent[p]) {
                parent[q] = p
                val next = rec(q)
                when (next.status) {
                    is Fail -> cur.status = Fail
                    is Done -> cur.status =
                        if (cur.status !is Ok) Fail else next.status
                }
                if (next.mustVisit) {
                    cur.mustVisit = true
                    cur.mvl += next
                    if (cur.mvl.size > 2) cur.status = Fail
                }
            }
            i = gn[i]
        }
        if (cur.status is Done && cur.mustVisit) cur.status =
            Fail
        if (cur.status is Ok && cur.mvl.size == 2) cur.status =
            Done(cur.pairPath())
        if (cur.status is Done) cur.mustVisit = false
        cur
    }
    val root = dfs(0)
    var path: MutableList<Int>? = when (val status = root.status) {
        is Fail -> null
        is Done -> status.path
        is Ok -> root.leafPath(dropHead = true)
    }
    if (path != null && !path.validPath(c, d)) {
        path.reverse()
        if (!path.validPath(c, d)) path = null
    }
    if (path == null) {
        println("No")
    } else {
        println("Yes")
        println(path.size)
        println(path.joinToString(" ") { (it + 1).toString() })
    }
}

private fun MutableList<Int>.validPath(c: List<Int>, d: List<Int>): Boolean {
    if (isEmpty()) return true
    if (c[first()] != d[last()]) return false
    for (i in 1 until size) {
        if (c[get(i)] != d[get(i - 1)]) return false
    }
    return true
}

private sealed class S
private object Ok : S()
private object Fail : S()
private class Done(val path: MutableList<Int>) : S()

private class Node(val idx: Int, val diff: Boolean) {
    var status: S = Ok
    val mvl = ArrayList<Node>(2)
    var mustVisit = diff

    fun pairPath(): MutableList<Int> {
        val a = mvl[0]
        val b = mvl[1]
        val path = a.leafPath()
        path.reverse()
        path.add(idx)
        path.addAll(b.leafPath())
        return path
    }

}

private tailrec fun Node.leafPath(dropHead: Boolean = false, to: MutableList<Int> = mutableListOf()): MutableList<Int> {
    val drop = dropHead && !diff
    if (!drop) to.add(idx)
    val next = mvl.firstOrNull()
    return if (next != null) next.leafPath(drop, to) else to
}

private fun bufferOut(block: PrintWriter.() -> Unit) = PrintWriter(System.out).use { block(it) }

fun <T, R> recursiveFunction(block: suspend RecursiveFunctionScope<T, R>.(T) -> R): (T) -> R =
    { value ->
        RecursiveFunctionImpl(block, value).call()
    }

@RestrictsSuspension
abstract class RecursiveFunctionScope<T, R> {
    /**
     * Makes recursive call to this [recursiveFunction] putting the call activation frame on the heap,
     * as opposed to the actual call stack that is used by a regular recursive call.
     */
    abstract suspend fun rec(value: T): R
}

private class RecursiveFunctionImpl<T, R>(
    private val block: suspend RecursiveFunctionScope<T, R>.(T) -> R,
    private var value: T? = null
) : RecursiveFunctionScope<T, R>(), Continuation<R> {
    private var result: Result<Any?> = Result.success(null)
    private var cont: Continuation<R>? = this

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override fun resumeWith(result: Result<R>) {
        this.cont = null
        this.result = result
    }

    override suspend fun rec(value: T): R = suspendCoroutineUninterceptedOrReturn { cont ->
        this.cont = cont
        this.value = value
        COROUTINE_SUSPENDED
    }

    @Suppress("UNCHECKED_CAST")
    fun call(): R {
        val f = block as Function3<Any?, T?, Continuation<R>?, Any?>
        while (true) {
            val cont = this.cont
                ?: return (result as Result<R>).getOrThrow()
            // This is block.startCoroutine(this, value, cont)
            val r = try {
                f(this, value, cont)
            }  catch (e: Throwable) {
                cont.resumeWithException(e)
                continue
            }
            if (r !== COROUTINE_SUSPENDED)
                cont.resume(r as R)
        }
    }
}