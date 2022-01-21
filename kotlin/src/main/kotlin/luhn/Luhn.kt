package luhn

internal object Luhn {

    fun isValid(candidate: String) =
        candidate.filterNot(Char::isWhitespace).takeIf { it.length > 1 && it.all(Char::isDigit) }
            ?.reversed()
            ?.withIndex()
            ?.sumOf { (i, c) -> "$c".toInt().times(i % 2 + 1).let { it - if (it > 9) 9 else 0 } }
            ?.let { it % 10 == 0 }
            ?: false
}
