#!/usr/bin/env bash

main() {
  local plng=(Pling Plang Plong)
  for i in "${!plng[@]}"; do
    [[ $(( "$1" % $(( "$i" * 2 + 3 )) )) == 0 ]] && result+="${plng[$i]}"
  done
  printf "%s\n" "${result:-$1}"
}

main "$@"
