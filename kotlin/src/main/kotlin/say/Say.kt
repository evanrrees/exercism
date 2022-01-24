package say

import java.util.concurrent.TimeUnit

internal class NumberSpeller {

    fun say(input: Long): String {
        require(input in 0 until 1_000_000_000_000) { "input $input is out of range".also(::say) }
        val result = if (input == 0L) "zero" else thousands(input).joinToString(" ")
        return result.also(::say)
    }

    private fun thousands(input: Long, n: Int = 0): List<String> =
        if (input == 0L || n > 3) emptyList()
        else thousands(input / 1000, n + 1) + buildList {
            units[input % 1000 / 100]?.let { add("$it hundred") }
            if (input % 100 >= 20) {
                tens[input % 100 / 10]?.let(::add)
                units[input % 10]?.let { add("${removeLast()}-$it") }
            } else
                units[input % 20]?.let(::add)
            if (isNotEmpty()) magnitudes[n]?.let(::add)
        }

    companion object {

        val units = mapOf(
            1L  to "one",     2L  to "two",       3L  to "three",    4L  to "four",     5L  to "five",
            6L  to "six",     7L  to "seven",     8L  to "eight",    9L  to "nine",     10L to "ten",
            11L to "eleven",  12L to "twelve",    13L to "thirteen", 14L to "fourteen", 15L to "fifteen",
            16L to "sixteen", 17L to "seventeen", 18L to "eighteen", 19L to "nineteen",
        )

        val tens = mapOf(
            2L to "twenty", 3L to "thirty",  4L to "forty",  5L to "fifty",
            6L to "sixty",  7L to "seventy", 8L to "eighty", 9L to "ninety"
        )

        val magnitudes = mapOf(1 to "thousand", 2 to "million", 3 to "billion")

        // call system speech synthesis utility
        fun say(string: String) {
            Runtime.getRuntime().exec(arrayOf("say", "-r", "300",string)).waitFor(5L, TimeUnit.SECONDS)
        }

    }

}
