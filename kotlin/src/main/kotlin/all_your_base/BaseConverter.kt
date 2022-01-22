package all_your_base

class BaseConverter(val base: Int, val digits: IntArray) {

    init {
        require(base > 1) { "Bases must be at least 2." }
        require(digits.isNotEmpty()) { "You must supply at least one digit." }
        require(digits.first() != 0 || digits.size == 1) { "Digits may not contain leading zeros." }
        require(digits.all { it < base }) { "All digits must be strictly less than the base." }
        require(digits.all { it >= 0 }) { "Digits may not be negative." }
    }

    val base10 = digits.reduce { acc, i -> acc * base + i }

    fun convertToBase(newBase: Int): IntArray {
        require(newBase > 1) { "Bases must be at least 2." }
        if (base10 == 0) return intArrayOf(0)
        val digits = mutableListOf<Int>()
        var num = base10
        while (num > 0) {
            digits.add(0, num % newBase)
            num /= newBase
        }
        return digits.toIntArray()
    }
}
