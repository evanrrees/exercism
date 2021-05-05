#!/usr/bin/env bash

declare -rA digits=(
  [" _ | ||_|"]=0
  ["     |  |"]=1
  [" _  _||_ "]=2
  [" _  _| _|"]=3
  ["   |_|  |"]=4
  [" _ |_  _|"]=5
  [" _ |_ |_|"]=6
  [" _   |  |"]=7
  [" _ |_||_|"]=8
  [" _ |_| _|"]=9
)

declare -A error_message=(
  [lines]="Number of input lines is not a multiple of four"
  [columns]="Number of input columns is not a multiple of three"
)

die() {
  echo "${error_message[$1]:-Something went wrong}" >&2
  exit "${2:-1}"
}

main() {
  local digit col i=0 j=0
  local -a output row_buffer
  while IFS="" read -r -t 0.1 -n 3 col; do
    if (( ${#col} % 3 )); then
      die columns
    elif (( ${#col} )); then
      row_buffer[j++]+="$col"
    else
      if ! (( ++i % 4 )); then
        for digit in "${row_buffer[@]}"; do
          output[i/4]+=${digits[${digit::9}]:-?}
        done
        row_buffer=()
      fi
      (( j=0 ))
    fi
  done
  (( i % 4 )) && die lines
  IFS=","; echo "${output[*]}"
}

main -