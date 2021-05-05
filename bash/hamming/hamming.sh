#!/usr/bin/env bash

declare -rA error_message=(
  [usage]="Usage: hamming.sh <string1> <string2>"
  [length]="left and right strands must be of equal length"
)

error_exit() {
  echo "${error_message[$1]:-$1}" >&2
  exit 1
}

hamming_distance() {
  local hamming
  for (( i=0; i<${#1}; i++ )); do
    [[ "${1:i:1}" == "${2:i:1}" ]] || ((hamming++))
  done
  echo "${hamming:-0}"
}

main() {
  (( $# == 2 )) || error_exit "usage"
  (( ${#1} == ${#2} )) || error_exit "length"
  hamming_distance "$@"
}

main "$@"
