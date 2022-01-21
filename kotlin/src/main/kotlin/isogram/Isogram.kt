package isogram

internal object Isogram {
    fun isIsogram(input: String) =
        input.filter(Char::isLetter).groupingBy(Char::lowercase).eachCount().all { it.value == 1 }
}
