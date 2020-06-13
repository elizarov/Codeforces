package archive.r649

import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

fun main() = System.`in`.bufferedReader().run {
    val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
    val gf = IntArray(n + 1) { -1 }
    val gn = IntArray(2 * m)
    val gv = IntArray(2 * m)
    fun add(i: Int, u: Int, v: Int) {
        gv[i] = v
        gn[i] = gf[u]
        gf[u] = i
    }
    repeat(m) { i ->
        val (u, v) = readLine()!!.split(" ").map { it.toInt()  }
        add(2 * i, u, v)
        add(2 * i + 1, v, u)
    }
    val dd = IntArray(n + 1)
    val ss = BooleanArray(n + 1)
    val st = IntArray(n + 1)
    data class DU(val d: Int, val u: Int)
    var loopShort: IntArray? = null
    var loopLong: IntArray? = null
    val dfs = DeepRecursiveFunction<DU, Unit> { (d, u) ->
        dd[u] = d
        ss[u] = true
        st[d] = u
        var i = gf[u]
        var c = 0
        while (i >= 0) {
            val v = gv[i]
            if (dd[v] == 0) {
                callRecursive(DU(d + 1, v))
            } else if (ss[v] && dd[v] <= d - 2) {
                c = maxOf(c, dd[v])
            }
            i = gn[i]
        }
        if (c > 0) {
            val len = d - c + 1
            if (len <= k) {
                if (loopShort == null) {
                    loopShort = st.copyOfRange(c, d + 1)
                }
            } else {
                if (loopLong == null || len < loopLong!!.size) {
                    loopLong = st.copyOfRange(c, d + 1)
                }
            }
        }
        ss[u] = false
    }
    dfs(DU(1, 1))
    if (loopShort != null) {
        println(2)
        println(loopShort!!.size)
        println(loopShort!!.joinToString(" "))
    } else if (loopLong != null) {
        val a = IntArray((k + 1) / 2) { i -> loopLong!![2 * i] }
        println(1)
        println(a.joinToString(" "))
    } else {
        val c1 = dd.count { it % 2 != 0 }
        val z = if (c1 >= n / 2) 1 else 0
        val a = dd.withIndex().filter { it.index > 0 && it.value % 2 == z }.take((k + 1) / 2)
        println(1)
        println(a.joinToString(" ") { it.index.toString() })
    }
}

public class DeepRecursiveFunction<T, R>(
    internal val block: suspend DeepRecursiveScope<T, R>.(T) -> R
)

public operator fun <T, R> DeepRecursiveFunction<T, R>.invoke(value: T): R =
    DeepRecursiveScopeImpl<T, R>(block, value).runCallLoop()

@RestrictsSuspension
public sealed class DeepRecursiveScope<T, R> {
    /**
     * Makes recursive call to this [DeepRecursiveFunction] function putting the call activation frame on the heap,
     * as opposed to the actual call stack that is used by a regular recursive call.
     */
    public abstract suspend fun callRecursive(value: T): R

    /**
     * Makes call to the specified [DeepRecursiveFunction] function putting the call activation frame on the heap,
     * as opposed to the actual call stack that is used by a regular call.
     */
    public abstract suspend fun <U, S> DeepRecursiveFunction<U, S>.callRecursive(value: U): S

    @Deprecated(
        level = DeprecationLevel.ERROR,
        message =
        "'archive.r649.invoke' should not be called from archive.r649.DeepRecursiveScope. " +
                "Use 'callRecursive' to do recursion in the heap instead of the call stack.",
        replaceWith = ReplaceWith("this.callRecursive(value)")
    )
    public operator fun DeepRecursiveFunction<*, *>.invoke(value: Any?): Nothing =
        throw UnsupportedOperationException("Should not be called from archive.r649.DeepRecursiveScope")
}

// ================== Implementation ==================

private typealias DeepRecursiveFunctionBlock = Function3<Any?, Any?, Continuation<Any?>?, Any?>

private val UNDEFINED_RESULT = Result.success(COROUTINE_SUSPENDED)

@Suppress("UNCHECKED_CAST")
private class DeepRecursiveScopeImpl<T, R>(
    block: suspend DeepRecursiveScope<T, R>.(T) -> R,
    value: T
) : DeepRecursiveScope<T, R>(), Continuation<R> {
    // Active function block
    private var function: DeepRecursiveFunctionBlock = block as DeepRecursiveFunctionBlock

    // Value to call function with
    private var value: Any? = value

    // Continuation of the current call
    private var cont: Continuation<Any?>? = this as Continuation<Any?>

    // Completion result (completion of the whole call stack)
    private var result: Result<Any?> = UNDEFINED_RESULT

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override fun resumeWith(result: Result<R>) {
        this.cont = null
        this.result = result
    }

    override suspend fun callRecursive(value: T): R = suspendCoroutineUninterceptedOrReturn { cont ->
        // calling the same function that is currently active
        this.cont = cont as Continuation<Any?>
        this.value = value
        COROUTINE_SUSPENDED
    }

    override suspend fun <U, S> DeepRecursiveFunction<U, S>.callRecursive(value: U): S = suspendCoroutineUninterceptedOrReturn { cont ->
        // calling another recursive function
        val function = block as DeepRecursiveFunctionBlock
        with(this@DeepRecursiveScopeImpl) {
            val currentFunction = this.function
            if (function !== currentFunction) {
                // calling a different function -- create a trampoline to restore function ref
                this.function = function
                this.cont = crossFunctionCompletion(currentFunction, cont as Continuation<Any?>)
            } else {
                // calling the same function -- direct
                this.cont = cont as Continuation<Any?>
            }
            this.value = value
        }
        COROUTINE_SUSPENDED
    }

    private fun crossFunctionCompletion(
        currentFunction: DeepRecursiveFunctionBlock,
        cont: Continuation<Any?>
    ): Continuation<Any?> = Continuation(EmptyCoroutineContext) {
        this.function = currentFunction
        // When going back from a trampoline we cannot just call cont.resume (stack usage!)
        // We delegate the cont.resumeWith(it) call to runCallLoop
        this.cont = cont
        this.result = it
    }

    @Suppress("UNCHECKED_CAST")
    fun runCallLoop(): R {
        while (true) {
            // Note: cont is set to null in archive.r649.DeepRecursiveScopeImpl.resumeWith when the whole computation completes
            val result = this.result
            val cont = this.cont
                ?: return (result as Result<R>).getOrThrow() // done -- final result
            // The order of comparison is important here for that case of rogue class with broken equals
            if (UNDEFINED_RESULT == result) {
                // call "function" with "value" using "cont" as completion
                val r = try {
                    // This is block.startCoroutine(this, value, cont)
                    function(this, value, cont)
                } catch (e: Throwable) {
                    cont.resumeWithException(e)
                    continue
                }
                // If the function returns without suspension -- calls its continuation immediately
                if (r !== COROUTINE_SUSPENDED)
                    cont.resume(r as R)
            } else {
                // we returned from a crossFunctionCompletion trampoline -- call resume here
                this.result = UNDEFINED_RESULT // reset result back
                cont.resumeWith(result)
            }
        }
    }
}
