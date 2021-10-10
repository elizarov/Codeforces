package archive.r635

import java.io.*
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

fun main() {
    System.`in`.bufferedReader().solve()
}

private fun BufferedReader.solve() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val g = Array(n) { ArrayList<Int>(2) }
    repeat(n - 1) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() - 1 }
        g[u].add(v)
        g[v].add(u)
    }
    val f = BooleanArray(n)
    val d = IntArray(n)
    val cost = IntArray(n)
    val dfs = deepRecursive<Int, Int> { u ->
        f[u] = true
        var c = 0
        for (v in g[u]) {
            if (!f[v]) {
                d[v] = d[u] + 1
                c += callRecursive(v)
            }
        }
        cost[u] = c
        c + 1
    }
    dfs(0)
    val sum = (0 until n)
        .map { i -> d[i] - cost[i] }
        .sortedDescending()
        .take(k)
        .fold(0L, Long::plus)
    println(sum)
}

public fun <T, R> deepRecursive(block: suspend DeepRecursiveScope<T, R>.(T) -> R): DeepRecursiveFunction<T, R> =
    DeepRecursiveFunction(block)

public class DeepRecursiveFunction<T, R> internal constructor(
    internal val block: suspend DeepRecursiveScope<T, R>.(T) -> R
) {
    internal fun callImpl(value: T): R = DeepRecursiveScopeImpl<T, R>(block, value).runCallLoop()
}

public operator fun <T, R> DeepRecursiveFunction<T, R>.invoke(value: T): R = callImpl(value)

@RestrictsSuspension
public sealed class DeepRecursiveScope<T, R> {
    public abstract suspend fun callRecursive(value: T): R
    public abstract suspend operator fun <U, S> DeepRecursiveFunction<U, S>.invoke(value: U): S
}

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

    override suspend fun <U, S> DeepRecursiveFunction<U, S>.invoke(value: U): S = suspendCoroutineUninterceptedOrReturn { cont ->
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
            // Note: cont is set to null in DeepRecursiveScopeImpl.resumeWith when the whole computation completes
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
