package sieve

internal object Sieve {

    fun primesUpTo(upperBound: Int): List<Int> {
        if (upperBound < 3) return (2..upperBound).toList()
        val sieve = Array(upperBound + 1) { it == 2 || it % 2 == 1 }
        for (i in 3..sieve.lastIndex step 2)
            if (sieve[i])
                for (j in i + i .. sieve.lastIndex step i) sieve[j] = false
        return sieve.mapIndexedNotNull { index, b -> if (b && index > 1) index else null }
    }

}
