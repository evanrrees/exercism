#!/usr/bin/env bash

declare -rA error_message=([usage]="Usage: $0 <number>")

error_exit() {
  echo "${error_message[$1]:-$1}" >&2; exit 1
}

main() {
  (( $# == 1 && ${1:-32} < 32 && ${1:--1} >= 0 )) || error_exit "usage"
  local -ra events=([1]="wink" [2]="double blink" [4]="close your eyes" [8]="jump" )
  local b=16 rem="$1" handshake
  while (( rem )); do
    while (( rem >= b )); do
        (( rem-=b ))
        (( b == 16 )) && continue
        (( handshake = ($1 >= 16 ? "$handshake,${events[b]}" : "${events[b]},$handshake" )))
    done
    (( b /= 2 ))
  done
  echo "${handshake%,}"
}

main "$@"