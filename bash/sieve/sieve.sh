#!/usr/bin/env bash

declare -a arr primes
declare i
(( $1 > 1 )) && primes+=( 2 )
for (( i=3; i<=$1; i+=2 )); do
  if (( ${arr[i]:-$i} )); then
    primes+=( "$i" )
    for (( j=i*i; j<=$1; j+=i+i )); do
      arr[$j]=0
    done
  fi
done
echo "${primes[@]}"