#!/usr/bin/env bash

#!/usr/bin/env bash
declare -a composites primes
declare -i i n step

for ((i = 2; i <= $1; i++)); do
    if ((${composites[i]:-$i})); then
        primes[$i]=$i
        # Found a prime! Mark all it's multiples as composites.
        # See https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
        ((step = (i == 2 ? i : i << 1)))
        for ((n = i * i; n <= $1; n += step)); do
            composites[$n]=0
        done
    fi
done

echo "${primes[*]}"