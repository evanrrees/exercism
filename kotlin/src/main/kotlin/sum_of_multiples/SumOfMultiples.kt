package sum_of_multiples

internal object SumOfMultiples {

    fun sum(factors: Set<Int>, limit: Int): Int {
        if (factors.isEmpty()) return 0
        val include = Array(limit) { false }
        for (f in factors) if (f in 1..limit) for (i in f until limit step f) {
            include[i] = true
        }
        return include.withIndex().sumOf { if (it.value) it.index else 0 }
    }

}
