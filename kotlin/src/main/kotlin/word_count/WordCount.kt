package word_count

object WordCount {
    fun phrase(phrase: String) = Regex("\\p{Alnum}+('\\p{Alnum}+)?")
        .findAll(phrase)
        .groupingBy { it.value.lowercase() }
        .eachCount()
}
