package nth_prime

internal object Prime {

    private const val SIEVE_SIZE = 46_300

    private val sieve = Array(SIEVE_SIZE) { it % 2 == 1 }

    init {
        sieve[1] = false
        sieve[2] = true
        for (i in 3..sieve.lastIndex step 2) if (sieve[i])
            for (j in i * i .. sieve.lastIndex step i)
                sieve[j] = false
    }

    private val primes = sieve.withIndex().mapNotNull { if (it.value) it.index else null }

    fun nth(n: Int): Int {
        require(n > 0) { "There is no zeroth prime." }
        return primes[n - 1]
    }

}
