package matching_brackets

object MatchingBrackets {

    tailrec fun isValid(string: String): Boolean {
        return isValid(string.replace(regex, "").takeIf { it != string } ?: return string.none { it in brackets })
    }

    private val regex = Regex("\\[[^{(\\[)}\\]]*]|\\{[^{(\\[)}\\]]*}|\\([^{(\\[)}\\]]*\\)")

    private val brackets = setOf('[', ']', '(', ')', '{', '}')

}
