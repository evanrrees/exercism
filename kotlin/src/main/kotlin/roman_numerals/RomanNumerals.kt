package roman_numerals

internal object RomanNumerals {
    private const val ones = "IXCM"
    private const val fives = "VLD"
    fun value(n: Int) = "$n".run {
        (lastIndex downTo 0).joinToString("") { i ->
            get(lastIndex - i).let {
                when {
                    it < '4'    -> "${ones[i]}".repeat(it - '0')
                    it == '4'   -> ones[i] + "${fives[i]}"
                    it < '9'    -> fives[i] + "${ones[i]}".repeat(it - '0' - 5)
                    else        -> ones.slice(i..i + 1)
                }
            }
        }
    }
}