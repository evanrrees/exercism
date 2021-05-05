object Bob {
    fun hey(input: String): String {
        val isYell = input.all { it.isUpperCase() || !it.isLetter() } && input.any { it.isLetter() }
        val isQuestion = input.trimEnd().endsWith('?')
        return when {
            isYell && isQuestion -> "Calm down, I know what I'm doing!"
            isYell -> "Whoa, chill out!"
            isQuestion -> "Sure."
            input.isBlank() -> "Fine. Be that way!"
            else -> "Whatever."
        }
    }
}
