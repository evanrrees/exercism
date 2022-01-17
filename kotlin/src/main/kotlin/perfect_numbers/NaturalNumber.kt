package perfect_numbers


enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

tailrec fun classify(number: Int, factor: Int = 1, sum: Int = 0): Classification =
    when {
        number < 1 -> throw RuntimeException("$number is not a natural number.")
        factor <= number / 2 -> classify(number, factor + 1, sum + if (number % factor == 0) factor else 0)
        sum == number -> Classification.PERFECT
        sum > number -> Classification.ABUNDANT
        else -> Classification.DEFICIENT
    }
