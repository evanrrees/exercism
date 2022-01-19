package sieve

object Sieve {

    fun primesUpTo(upperBound: Int): List<Int> {
        if (upperBound == 1) return emptyList()
        if (upperBound == 2) return listOf(2)
        val sieve = Array(upperBound + 1) { it % 2 == 1 }
        val primes = mutableListOf(2)
        sieve[2] = true
        for (i in 3..sieve.lastIndex step 2) {
            if (!sieve[i]) continue
            primes += i
            for (j in i + i .. sieve.lastIndex step i) sieve[j] = false
        }
        return primes
    }
}
