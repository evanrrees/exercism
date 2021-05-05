#!/usr/bin/env bash

usage() {
  echo "bash fizzbuzz.sh <number>"
  exit "$1"
}

fizzbuzz() {
  for (( i=1; i<="$1"; i++ )); do
    local res=''
    (( i % 3 )) || res+="Fizz"
    (( i % 5 )) || res+="Buzz"
    echo "${res:-$i}"
  done
}

main() {
  (( $# == 1 )) || usage 1
  [[ "$1" =~ ^[0-9]+$ ]] || usage 1
  fizzbuzz "$1"
}

main "$@"
