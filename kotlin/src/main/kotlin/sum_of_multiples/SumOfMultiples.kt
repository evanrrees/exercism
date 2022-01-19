package sum_of_multiples

object SumOfMultiples {

    tailrec fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    fun lcm(a: Int, b: Int) = a * b / gcd(a, b)

    fun sum(factors: Set<Int>, limit: Int): Int {
        val realFactors = factors.filter { it in 1..limit }.toMutableList()
        realFactors.retainAll { f -> realFactors.none { it != f && it % f == 0 } }
        var total = 0
        for ((i, factor) in realFactors.withIndex()) {
            var n = (limit - 1) / factor
            total += factor * n * (n + 1) / 2
            for (j in i + 1 .. realFactors.lastIndex) {
                if (j > realFactors.lastIndex) continue
                val unfactor = lcm(factor, realFactors[j])
                n = (limit - 1) / unfactor
                total -= unfactor * n * (n + 1) / 2
            }
        }
        return total
    }

}
