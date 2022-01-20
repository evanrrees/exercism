package forth


typealias IntPair = Pair<Int, Int>

internal class Forth {

    private val customOperations = mutableMapOf<String, List<String>>()
    private val stack = mutableListOf<Int>()
    private val standardOperations = mapOf<String, () -> Any>(
        "+" to { pop2().add().push() },
        "-" to { pop2().swap().sub().push() },
        "*" to { pop2().mul().push() },
        "/" to { pop2().swap().div().push() },
        "dup" to { pop().push().push() },
        "drop" to { pop() },
        "over" to { pop2().swap().push().first.push() },
        "swap" to { pop2().push() },
    )

    // basic operations
    private fun IntPair.add() = first + second
    private fun IntPair.sub() = first - second
    private fun IntPair.mul() = first * second
    private fun IntPair.div() = also { require(second > 0) { "divide by zero" } }.run { first / second }
    private fun IntPair.swap() = second to first

    // remove top element of stack
    private fun pop() = also { require(stack.isNotEmpty()) { "empty stack" } }.run { stack.removeLast() }

    // remove top 2 elements from stack in order
    private fun pop2() = also { require(stack.size != 1) { "only one value on the stack" } }.run { pop() to pop() }

    // add value to top of stack
    private fun Int.push() = apply(stack::add)

    // add both elements to top of stack in same order
    private fun IntPair.push() = apply { stack += first; stack += second }

    // replace custom definitions on a line or in a definition body
    private fun String.inlineCustomDefinitions() =
        split(" ").flatMap { customOperations[it] ?: listOf(it) }

    // define custom operation and inline body
    private fun String.defineCustom() = Regex(": (\\S+) ?(.+)? ;").matchEntire(lowercase())?.destructured
        ?.also { (name, _) -> require(name.toIntOrNull() == null) { "illegal operation $name" } }
        ?.let { (name, body) -> customOperations[name] = body.inlineCustomDefinitions() }
        ?.let { emptyList<String>() }

    // extract and replace custom definitions in a line
    private fun String.parse() = lowercase().let { it.defineCustom() ?: it.inlineCustomDefinitions() }

    // extract and replace custom definitions in all lines
    private fun Array<out String>.parse() = flatMap { it.parse() }

    fun evaluate(vararg lines: String): List<Int> {
        lines.parse().forEach {
            it.toIntOrNull()?.push()
                ?: standardOperations[it]?.invoke()
                ?: throw UnsupportedOperationException("undefined operation: $it")
        }
        return stack.toList()
    }

}