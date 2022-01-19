package sum_of_multiples

object SumOfMultiples {

    tailrec fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    fun lcm(a: Int, b: Int) = a * b / gcd(a, b)

    tailrec fun sum(factors: Set<Int>, limit: Int, sum: Int = 0, coef: Int = 1): Int {
        val realFactors = factors.filter { it in 1..limit }
        val newFactors = mutableSetOf<Int>()
        var total = 0
        for ((i, factor) in realFactors.withIndex()) {
            val n = (limit - 1) / factor
            total += factor * n * (n + 1) / 2
            for (j in i + 1 .. realFactors.lastIndex) {
                if (j > realFactors.lastIndex) continue
                newFactors += lcm(factor, realFactors[j])
            }
        }
        return if (newFactors.isEmpty())
            sum + coef * total
        else
            sum(newFactors.toSortedSet(), limit, sum + coef * total, coef * -1)
    }

}
