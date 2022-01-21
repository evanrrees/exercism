package prime_factors

object PrimeFactorCalculator {

    fun primeFactors(int: Int) = primeFactors(int.toLong()).map(Long::toInt)

    tailrec fun primeFactors(long: Long, factor: Long = 2, factors: MutableList<Long> = mutableListOf()): List<Long> =
        when {
            long < 2 -> factors
            long % factor == 0L -> primeFactors(long / factor, factor, factors.also { it += factor })
            else -> primeFactors(long, factor + 1 + (factor % 2), factors)
        }
}
