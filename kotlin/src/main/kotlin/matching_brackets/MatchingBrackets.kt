package matching_brackets

object MatchingBrackets {

    fun isValid(string: String): Boolean {
        val match = regex.find(string) ?: return string.none { it in brackets }
        return isValid(string.replaceRange(match.range, ""))
    }

    private val regex = Regex("\\[[^{(\\[)}\\]]*]|\\{[^{(\\[)}\\]]*}|\\([^{(\\[)}\\]]*\\)")

    private val brackets = setOf('[', ']', '(', ')', '{', '}')

}
