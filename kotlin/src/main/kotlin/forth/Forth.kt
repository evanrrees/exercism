package forth

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ConcurrentMap

typealias Stack = MutableList<Int>

open class ForthException(message: String, cause: Throwable? = null): Exception(message, cause)
class UnknownException(cause: Throwable? = null): ForthException("unknown", cause)
class EmptyStackException(cause: Throwable? = null): ForthException("empty stack", cause)
class SingletonStackException(cause: Throwable? = null): ForthException("only one value on the stack", cause)
class DivideByZeroException(cause: Throwable? = null): ForthException("divide by zero", cause)
class IllegalOperationException(cause: Throwable? = null, word: String = ""): ForthException("illegal operation: $word", cause)
class UndefinedOperationException(cause: Throwable? = null, word: String = ""): ForthException("undefined operation: $word", cause)


internal class Forth {
    private val stack = ConcurrentLinkedDeque<Int>()
    private val callStack = ConcurrentLinkedQueue<() ->  Unit>()

    fun getOperands(n: Int) = List(n) {
        try { stack.removeLast() }
        catch (e: NoSuchElementException) {
            throw when (it) {
                0 -> EmptyStackException(e)
                1 -> SingletonStackException(e)
                else -> UnknownException(e)
            }
        } catch (e: Exception) { throw UnknownException(e) }
    }

    private val operations = mutableMapOf<String, () -> Unit>(
            "+"    to { stack += getOperands(2).sum() },
            "-"    to { stack += getOperands(2).let { (a, b) -> b - a } },
            "/"    to { stack += getOperands(2).let { (a, b) -> b / a } },
            "*"    to { stack += getOperands(2).reduce(Int::times) },
            "swap" to { stack += getOperands(2) },
            "drop" to { getOperands(1) },
            "dup"  to { stack += getOperands(1).let { it + it } },
            "over" to { stack += getOperands(2).let { it.takeLast(1) + it } },
        )

    private val customOperations = mutableMapOf<String, () -> Unit>()

    fun defineCustomOp(ops: List<String>) {
        val name = ops.first()
        if (name.toIntOrNull() != null) throw IllegalOperationException(word=name)
        customOperations[name] = { evaluateLine(ops.drop(1)).invoke() }
        if (name in operations) {
            operations[name] = customOperations.getValue(name)
        }
//        operations[name] = {
//            for (op in ops.drop(1)) {
//                if (op in operations) operations.getValue(op).invoke()
//                else if (op.toIntOrNull() != null) {
//                    stack += op.toInt()
//                } else {
//                    throw UndefinedOperationException(word=op)
//                }
//            }
//        }
//        return operations.getValue(name)
    }


    fun evaluateLine(line: List<String>): () -> Unit = {
        val iterator = line.iterator()
        while (iterator.hasNext()) {
            var op = iterator.next().lowercase()
            when {
                op in operations -> operations.getValue(op).invoke()
                op in customOperations -> customOperations.getValue(op).invoke()
                op == ":" -> {
                    val ops = mutableListOf<String>()
                    op = iterator.next()
                    while (op != ";") {
                        ops += op
                        op = iterator.next()
                    }
                    defineCustomOp(ops)
                }
                op.toIntOrNull() != null -> stack += op.toInt()
                else -> throw UndefinedOperationException(word=op)
            }
        }
    }

    fun evaluate(vararg lines: String): List<Int> {
        if (lines.isEmpty()) throw EmptyStackException()
        try {
            for (line in lines) evaluateLine(line.split(" ")).invoke()
//            for (expr in callStack) expr.invoke()
        } catch (e: Exception) {
            throw when (e) {
                is ForthException -> e
                is ArithmeticException -> DivideByZeroException(e)
                else -> UnknownException(e)
            }
        }
        return stack.toList()
    }
}

internal class Forth3 {

    private val stack = mutableListOf<Int>()

    fun evaluate(vararg lines: String): List<Int> {
        if (lines.isEmpty()) throw EmptyStackException()
        for (line in lines) {
            for (item in line.split(" ")) {
                val int = item.toIntOrNull()
                if (int != null) Operator.PUSH(this, int)
                else {
                    Operator.ofWord(item)(this)
                }
            }
        }
        return stack
    }

    enum class Operator(val word: String, val arity: Int, val operation: Forth3.(List<Int>) -> List<Int>) {
        ADD("+", 2, { (a, b) -> listOf(a + b) }),
        SUB("-", 2, { (a, b) -> SWAP(this); listOf(a - b) }),
        MUL("*", 2, { (a, b) -> listOf(a * b) }),
        DIV("/", 2, { (a, b) -> SWAP(this); listOf(a / b) }),
        SWAP("swap", 2, { it }),
        OVER("over", 2, { it.takeLast(1) + it }),
        DUP("dup", 1, { it + it }),
//        DROP("drop", 1, { emptyList() }),
        DROP("drop", 1, { listOf(stack.removeLast()) }),
        PUSH("push", 0, { stack.addAll(it); emptyList() })
        ;

        operator fun invoke(forthStack: Forth3, ints: List<Int>) = forthStack.apply { operation(this, ints) }
        operator fun invoke(forthStack: Forth3, int: Int) = forthStack.apply { operation(this, listOf(int)) }
        operator fun invoke(forthStack: Forth3) = forthStack.apply {
            val inputs = List(arity) {
                try {
                    DROP.operation(this, emptyList()).single()
                } catch (e: NoSuchElementException) {
                    throw when (it) {
                        0 -> EmptyStackException(e)
                        1 -> SingletonStackException(e)
                        else -> UnknownException(e)
                    }
                } catch (e: Exception) { throw UnknownException(e) }
            }

            try {
                if (this@Operator != DROP) {
                    val results = operation(this, inputs)
                    PUSH.operation(this, results)
                }
            } catch (e: Exception) {
                throw when (e) {
                    is ForthException -> e
                    is ArithmeticException -> DivideByZeroException(e)
                    else -> UnknownException(e)
                }
            }
        }

        companion object {
            fun ofWord(word: String): Operator {
                return values().firstOrNull { it.word == word } ?: throw IllegalOperationException()
            }
        }
    }

}

internal class ForthStack {

    enum class Operator(val operation: ((Int, Int) -> Int), val word: String) {
        ADD(Int::plus, "+"),    // binary -> take 2 values from stack and push result   Push(Drop() + Drop())
        SUB(Int::minus, "-"),   // binary -> take 2 values from stack and push result   Swap(); Push(Drop() - Drop())
        MUL(Int::times, "*"),   // binary -> take 2 values from stack and push result   Push(Drop() * Drop())
        DIV(Int::div, "/"),     // binary -> take 2 values from stack and push result   Swap(); Push(Drop() / Drop())
        DUP(Int::mod, "DUP"),   // stack manipulation -> take 1 value from stack and push result twice          a = Drop(); Push(a); Push(a);
        SWAP(Int::mod, "SWAP"), // stack manipulation -> take 2 values from stack and push in opposite order    b = Drop(); a = Drop(); Push(b); Push(a)
        OVER(Int::mod, "OVER"), // stack manipulation -> take 2 values from stack and return in same order plus first element again     b = Drop(); a = Drop(); Push(a); Push(b); Push(a)
        DROP(Int::mod, "DROP"), // stack manipulation -> take 1 value from stack        (Stack) -> Int
        PUSH(Int::mod, "PUSH"); // stack manipulation -> add 1 value to stack           (Int) -> Stack
        operator fun invoke(stack: Stack): Stack {
            try {
                val b: Int = try { stack.removeLast() }
                    catch (e: NoSuchElementException) { throw EmptyStackException() }
                val a: Int = try { stack.removeLast() }
                    catch (e: NoSuchElementException) { throw SingletonStackException() }
                val result = try { operation(a, b) }
                    catch (e: ArithmeticException) { throw DivideByZeroException() }
                stack.add(result)
                return stack
            } catch (e: Exception) {
                throw if (e is ForthException) e else UnknownException(e)
            }
        }

        companion object {
            fun ofWord(word: String): Operator = try { values().first { it.word == word } }
                catch (e: NoSuchElementException) { throw IllegalOperationException(e) }
        }
    }
    fun evaluate(vararg lines: String): List<Int> {
        if (lines.isEmpty()) throw EmptyStackException()
        val stack: Stack = mutableListOf()
        for (line in lines) {
            for (item in line.split(" ")) {
                val int = item.toIntOrNull()
                if (int != null) stack.add(int)
                else {
                    Operator.ofWord(item)(stack)
                }
            }
        }
        return stack
    }
}
