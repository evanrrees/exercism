package say

internal class NumberSpeller {

    fun say(input: Long): String {
        require(input in 0 until 1_000_000_000_000) { "out of range" }
        return if (input == 0L) "zero" else thousands(input).joinToString(" ").trim()
    }

    // parse a number from 0..99
    fun tens(input: Long): String {
        val result = mutableListOf<String>()
        if (input >= 20) {
            tens[input / 10]?.let { result += it }
            ones[input % 10]?.let { result += it }
        } else
            ones[input]?.let { result += it }
        return result.joinToString("-")
    }

    fun hundreds(input: Long): List<String> {
        val result = mutableListOf<String>()
        ones[input / 100]?.let { result += "$it hundred" }
//        ones[input / 100]?.let { "$it hundred" }.orEmpty()
        return if (input % 100 > 0) result + tens(input % 100) else result
    }

    fun thousands(input: Long, n: Int = 0): List<String> =
        if (input > 0)
            thousands(input / 1000, n + 1) + hundreds(input % 1000) + orders[n]?.takeIf { input % 1000 > 0 }.orEmpty()
        else emptyList()

    companion object {

        val ones = mapOf(
            1L to "one", 2L to "two", 3L to "three", 4L to "four",
            5L to "five", 6L to "six", 7L to "seven", 8L to "eight", 9L to "nine",
            10L to "ten", 11L to "eleven", 12L to "twelve", 13L to "thirteen", 14L to "fourteen",
            15L to "fifteen", 16L to "sixteen", 17L to "seventeen", 18L to "eighteen", 19L to "nineteen",
        )

        val tens = mapOf(
            2L to "twenty", 3L to "thirty", 4L to "forty", 5L to "fifty", 6L to "sixty", 7L to "seventy",
            8L to "eighty", 9L to "ninety"
        )

        val orders = mapOf(
            1 to "thousand",
            2 to "million",
            3 to "billion",
            4 to "trillion",
            5 to "quadrillion"
        )

    }

}
