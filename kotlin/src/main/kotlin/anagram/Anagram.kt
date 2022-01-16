package anagram

class Anagram(s: String) {

    private val lower = s.toLowerCase()
    private val target = lower.toList().sorted()

    fun match(words: Set<String>) =
        words.filter { it.toLowerCase().let { w -> w.toList().sorted() == target && w != lower } }.toSet()

}
