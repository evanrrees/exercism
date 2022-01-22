package matching_brackets

object MatchingBrackets {

    fun isValid(input: String): Boolean {
        val stack = mutableListOf<Char>()
        for (c in input)
            if (c in brackets.values) stack += c
            else if (c in brackets)
                if (stack.lastOrNull() == brackets[c]) stack.removeLast()
                else return false
        return stack.isEmpty()
    }

    private val brackets = mapOf(')' to '(', ']' to '[', '}' to '{')

}
