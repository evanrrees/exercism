package anagram

internal class Anagram(s: String) {

    private val lower = s.lowercase()
    private val target = lower.toList().sorted()

    fun match(words: Set<String>) =
        words.filter { it.lowercase().let { w -> w.toList().sorted() == target && w != lower } }.toSet()

}
