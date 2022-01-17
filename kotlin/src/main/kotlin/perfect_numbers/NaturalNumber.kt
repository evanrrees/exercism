package perfect_numbers


enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun classify(naturalNumber: Int): Classification {
    if (naturalNumber < 1) throw RuntimeException("$naturalNumber is not a natural number.")
    val aSum = aliquotSum(naturalNumber)
    return when {
        aSum == naturalNumber -> Classification.PERFECT
        aSum > naturalNumber -> Classification.ABUNDANT
        else -> Classification.DEFICIENT
    }
}

tailrec fun aliquotSum(number: Int, factor: Int = 1, sum: Int = 0): Int =
    if (factor > number / 2) sum
    else aliquotSum(number, factor + 1, sum + if (number % factor == 0) factor else 0)