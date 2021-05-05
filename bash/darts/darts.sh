#!/usr/bin/env bash

exit_error() {
  echo "Usage: $0 <x> <y>"; exit 1
}

all_valid_numbers() {
  for i in "$@"; do
    [[ "$i" =~ ^-?[0-9]{1,2}\.?[0-9]?$ ]] || return 1
  done
}

parse_float() {
  # return float * 100 as integer
  # works on floats with exactly one digit right of the decimal
  local n="$1" len=$(( ${#1} - 2 ))
  if [[ $1 =~ \. ]]; then
    n=${n::len}${n:len+1}
  else
    (( n *= 10 ))
  fi
  if [[ ${n:0:1} == "-" ]]; then    # strip leading zeros to avoid octal interpretation
    n=$(( -10 * 10#${n:1} ))
  else
    n=$(( 10 * 10#$n ))
  fi
  echo "$n"
}

sqrt() {
  local n="$1" i=0
  while (( ++i * i < n )); do continue; done
  echo $(( (i * i == n) ? i : i - 1 ))
}

points() {
  if    (( $1 > 1000  )); then  echo 0
  elif  (( $1 > 500   )); then  echo 1
  elif  (( $1 > 100   )); then  echo 5
  else                          echo 10
  fi
}

main() {
  ( (( $# == 2 )) && all_valid_numbers "$@" ) || exit_error
  local a b dist
  a=$(parse_float "$1")
  b=$(parse_float "$2")
  dist=$(sqrt $(( a * a + b * b )))
  points "$dist"
}

main "$@"