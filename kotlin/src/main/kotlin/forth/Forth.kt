package forth


internal class Forth {

    private val customOperations = mutableMapOf<String, List<String>>()
    private val stack = mutableListOf<Int>()
    private val standardOperations = mapOf<String, () -> Any>(
        "+"    to { pop2().add().push() },
        "-"    to { pop2().sub().push() },
        "*"    to { pop2().mul().push() },
        "/"    to { pop2().div().push() },
        "dup"  to { pop().push().push() },
        "drop" to { pop() },
        "over" to { pop2().push().first.push() },
        "swap" to { pop2().swap().push() },
    )

    // remove top element of stack
    private fun pop() = also { require(stack.isNotEmpty()) { "empty stack" } }.run { stack.removeLast() }

    // remove top 2 elements from stack in order
    private fun pop2(): Pair<Int, Int> = also { require(stack.size != 1) { "only one value on the stack" } }
        .run { pop() to pop() }.swap()

    // add value to top of stack
    private fun Int.push(): Int = apply(stack::add)
    // add both elements to top of stack in same order
    private fun Pair<Int, Int>.push(): Pair<Int, Int> = apply { stack += first; stack += second }

    private fun Pair<Int, Int>.swap(): Pair<Int, Int> = second to first

    private fun Pair<Int, Int>.add(): Int = first + second
    private fun Pair<Int, Int>.sub(): Int = first - second
    private fun Pair<Int, Int>.mul(): Int = first * second
    private fun Pair<Int, Int>.div(): Int = also { require(second > 0) { "divide by zero" }  }.run { first / second }

    // replace custom definitions on a line or in a definition body
    private fun String.inlineCustomDefinitions(): List<String> = split(" ").flatMap { customOperations[it] ?: listOf(it) }

    // define custom operation and inline body
    private fun String.defineCustom(): List<String>? = Regex(": (\\S+) ?(.+)? ;").matchEntire(lowercase())
        ?.destructured
        ?.also { (name, _) -> require(name.toIntOrNull() == null) { "illegal operation $name" } }
        ?.let { (name, body) -> customOperations[name] = body.inlineCustomDefinitions() }
        ?.let { emptyList() }

    // extract and replace custom definitions in a line
    private fun String.parse(): List<String> = lowercase().let { it.defineCustom() ?: it.inlineCustomDefinitions() }

    // extract and replace custom definitions in all lines
    private fun List<String>.parse(): List<String> = flatMap { it.parse() }

    fun evaluate(vararg lines: String): List<Int> = evaluate(lines.asList())

    private fun evaluate(lines: List<String>): List<Int> {
        lines.parse().forEach {
            it.toIntOrNull()?.push() ?: standardOperations[it]?.invoke()
                ?: throw UnsupportedOperationException("undefined operation: $it")
        }
        return stack.toList()
    }

}